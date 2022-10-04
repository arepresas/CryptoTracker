package stream.arepresas.cryptotracker.features.cryptos.tasks;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stream.arepresas.cryptotracker.external.coinmarket.CoinMarketClient;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPrice;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketInfoApiResponse;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketLastListingApiResponse;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketLastQuoteApiResponse;
import stream.arepresas.cryptotracker.features.cryptos.*;
import stream.arepresas.cryptotracker.utils.CoinMarketUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static stream.arepresas.cryptotracker.utils.CoinMarketUtils.cryptoCoinPriceFromCoinMarketCryptoPrice;
import static stream.arepresas.cryptotracker.utils.CoinMarketUtils.cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes;
import static stream.arepresas.cryptotracker.utils.DataUtils.isNullOrEmpty;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCryptosTask implements Runnable {

  private final CoinMarketClient coinMarketClient;
  private final CryptoCoinService cryptoCoinService;

  @Value("${coinMarket.saved-cryptos.start}")
  private Integer lastPricesStart;

  @Value("${coinMarket.saved-cryptos.end}")
  private Integer lastPricesEnd;

  @SneakyThrows
  @Transactional
  @Override
  @Scheduled(fixedRate = 1000000)
  public synchronized void run() {
    log.info("START --- Running UpdateCryptosTask");
    try {
      // Get LastPrices
      List<CoinMarketCryptoPrice> lastPrices =
          ((CoinMarketLastListingApiResponse)
                  coinMarketClient.getCryptoLastPrices(
                      lastPricesStart, lastPricesEnd, Currency.USD))
              .getData();

      List<Long> savedCryptoIds = cryptoCoinService.getCryptoCoinIds();

      List<Long> notSavedCryptoIds =
          lastPrices.stream()
              .filter(lastPrice -> !savedCryptoIds.contains(lastPrice.getId()))
              .map(CoinMarketCryptoPrice::getId)
              .toList();

      List<Long> noPriceCryptoIds =
          savedCryptoIds.stream()
              .filter(
                  savedCryptoId ->
                      !lastPrices.stream()
                          .map(CoinMarketCryptoPrice::getId)
                          .toList()
                          .contains(savedCryptoId))
              .toList();

      // Add prices for Cryptos not in LastPrices
      lastPrices.addAll(
          isNullOrEmpty(noPriceCryptoIds)
              ? new ArrayList<>()
              : ((CoinMarketLastQuoteApiResponse)
                      coinMarketClient.getCryptoPrices(noPriceCryptoIds, Currency.USD))
                  .getData().values().stream().toList());

      // CryptoCoins not in DB with price and Save
      List<CryptoCoin> newCryptoCoins =
          isNullOrEmpty(notSavedCryptoIds)
              ? new ArrayList<>()
              : ((CoinMarketInfoApiResponse) coinMarketClient.getCryptoInfo(notSavedCryptoIds))
                  .getData().values().stream()
                      .map(CoinMarketUtils::cryptoCoinFromCoinMarketCryptoInfo)
                      .map(
                          cryptoCoin -> {
                            cryptoCoin.setCoinPrice(
                                cryptoCoinPriceFromCoinMarketCryptoPrice(
                                    lastPrices.stream()
                                        .filter(price -> price.getId().equals(cryptoCoin.getId()))
                                        .findFirst()
                                        .orElse(null),
                                    cryptoCoin));
                            return cryptoCoin;
                          })
                      .toList();

      cryptoCoinService.saveCryptoCoins(newCryptoCoins);

      // Save quotes for Cryptos in DB
      List<CryptoCoinPrice> savedCryptoCoinPrices =
          cryptoCoinService
              .searchCryptoCoinPrices(CryptoCoinPriceCriteria.builder().ids(savedCryptoIds).build())
              .stream()
              .toList();

      List<CryptoCoinQuote> cryptoCoinQuotes = new ArrayList<>();

      savedCryptoCoinPrices.stream()
          .forEach(
              cryptoCoinPrice ->
                  cryptoCoinQuotes.addAll(
                      cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes(
                          lastPrices.stream()
                              .filter(
                                  price ->
                                      price.getId().equals(cryptoCoinPrice.getCoinInfo().getId()))
                              .findFirst()
                              .orElse(null)
                              .getQuote(),
                          cryptoCoinPrice)));

      cryptoCoinService.saveCryptoCoinPriceQuotes(cryptoCoinQuotes);
    } catch (Exception exception) {
      log.error(exception.getMessage());
    } finally {
      log.info("END --- Running UpdateCryptosTask");
    }
  }
}
