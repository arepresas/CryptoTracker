package stream.arepresas.cryptotracker.features.cryptos.tasks;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import stream.arepresas.cryptotracker.external.coinmarket.CoinMarketClient;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPrice;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketInfoApiResponse;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketLastListingApiResponse;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketLastQuoteApiResponse;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoin;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinService;
import stream.arepresas.cryptotracker.features.cryptos.Currency;
import stream.arepresas.cryptotracker.utils.CoinMarketUtils;

import java.util.ArrayList;
import java.util.List;

import static stream.arepresas.cryptotracker.utils.CoinMarketUtils.cryptoCoinPriceFromCoinMarketCryptoPrice;
import static stream.arepresas.cryptotracker.utils.DataUtils.isNullOrEmptyList;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCryptosTask implements Runnable {

  private final CoinMarketClient coinMarketClient;
  private final CryptoCoinService cryptoCoinService;
  protected boolean running = false;

  @Value("${coinMarket.saved-cryptos.start}")
  private Integer lastPricesStart;

  @Value("${coinMarket.saved-cryptos.end}")
  private Integer lastPricesEnd;

  @SneakyThrows
  @Override
  public synchronized void run() {
    if (!running) {
      running = true;
      try {
        try {

          // Get LastPrices
          List<CoinMarketCryptoPrice> lastPrices =
              ((CoinMarketLastListingApiResponse)
                      coinMarketClient.getCryptoLastPrices(
                          lastPricesStart, lastPricesEnd, Currency.USD))
                  .getData();

          // Saved CryptoCoins
          List<CryptoCoin> cryptoCoins =
              cryptoCoinService.getCryptoInfo(
                  lastPrices.stream().map(CoinMarketCryptoPrice::getId).toList());

          List<Long> savedCryptoIds = cryptoCoins.stream().map(CryptoCoin::getId).toList();

          // Add notSavedCryptoCoins
          List<Long> notSavedCryptoIds =
              lastPrices.stream()
                  .filter(lastPrice -> !savedCryptoIds.contains(lastPrice.getId()))
                  .map(CoinMarketCryptoPrice::getId)
                  .toList();

          cryptoCoins.addAll(
              isNullOrEmptyList(notSavedCryptoIds)
                  ? new ArrayList<>()
                  : ((CoinMarketInfoApiResponse) coinMarketClient.getCryptoInfo(notSavedCryptoIds))
                      .getData().values().stream()
                          .map(CoinMarketUtils::cryptoCoinFromCoinMarketCryptoInfo)
                          .toList());

          // Price for Cryptos not in LastPrices
          List<Long> noPriceCryptoIds =
              savedCryptoIds.stream()
                  .filter(
                      savedCryptoId ->
                          !lastPrices.stream()
                              .map(CoinMarketCryptoPrice::getId)
                              .toList()
                              .contains(savedCryptoId))
                  .toList();

          List<CoinMarketCryptoPrice> coinMarketCryptoPrices =
              isNullOrEmptyList(noPriceCryptoIds)
                  ? new ArrayList<>()
                  : ((CoinMarketLastQuoteApiResponse)
                          coinMarketClient.getCryptoPrices(noPriceCryptoIds, Currency.USD))
                      .getData().values().stream().toList();

          // Add Cryptos in LastPrices
          coinMarketCryptoPrices.addAll(lastPrices);

          // Add prices to cryptos
          cryptoCoins.stream()
              .forEach(
                  cryptoCoin ->
                      cryptoCoin
                          .getCoinPrice()
                          .add(
                              cryptoCoinPriceFromCoinMarketCryptoPrice(
                                  coinMarketCryptoPrices.stream()
                                      .filter(
                                          coinMarketCryptoPrice ->
                                              coinMarketCryptoPrice
                                                  .getId()
                                                  .equals(cryptoCoin.getId()))
                                      .findFirst()
                                      .orElse(null),
                                  cryptoCoin)));

          // Save data
          cryptoCoinService.saveCryptoCoins(cryptoCoins);

        } catch (Exception exception) {
          log.error(exception.getMessage());
        }
      } finally {
        running = false;
      }
    }
  }
}
