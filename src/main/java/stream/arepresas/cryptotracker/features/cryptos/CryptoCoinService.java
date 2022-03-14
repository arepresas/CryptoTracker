package stream.arepresas.cryptotracker.features.cryptos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static stream.arepresas.cryptotracker.utils.DataUtils.stringListToString;

@RequiredArgsConstructor
@Service
@Slf4j
public class CryptoCoinService {
  private final CryptoCoinRepository cryptoCoinRepository;
  private final CryptoCoinPriceRepository cryptoCoinPriceRepository;
  private final CryptoCoinPriceQuoteRepository cryptoCoinPriceQuoteRepository;

  // GET

  public List<CryptoCoin> getAllCryptoInfo() {
    log.info("CryptoCoinService - getAllCryptoInfo");
    return cryptoCoinRepository.findAll();
  }

  public List<CryptoCoin> getCryptoInfoByCryptoIds(List<Long> cryptoIds) {
    log.info("CryptoCoinService - getCryptoInfo {}", cryptoIds);
    return cryptoCoinRepository.findAllById(cryptoIds);
  }

  public List<CryptoCoinPrice> getCryptoPriceByCryptoIds(List<Long> cryptoIds) {
    log.info("CryptoCoinService - getCryptoPrice {}", cryptoIds);
    return cryptoCoinPriceRepository.findByCoinInfoIdIsIn(cryptoIds);
  }

  public List<CryptoCoinPriceQuote> getCryptoPriceQuotesByCryptoPriceId(Long cryptoPriceId) {
    log.info("CryptoCoinService - getCryptoPriceQuotes {}", cryptoPriceId);
    return cryptoCoinPriceQuoteRepository.findByCoinPriceId(cryptoPriceId);
  }

  public CryptoCoin saveCryptoCoin(@NonNull CryptoCoin cryptoCoin) {
    log.info("CryptoCoinService - saveCryptoCoin {}", cryptoCoin);
    return cryptoCoinRepository.save(cryptoCoin);
  }

  // SAVE

  public List<CryptoCoin> saveCryptoCoins(@NonNull List<CryptoCoin> cryptoCoins) {
    log.info(
        "CryptoCoinService - saveCryptoCoins {}",
        stringListToString(
            cryptoCoins.stream().map(CryptoCoin::getId).map(String::valueOf).toList()));

    List<CryptoCoin> savedCryptoCoins = cryptoCoinRepository.saveAll(cryptoCoins);

    if (savedCryptoCoins.isEmpty()) {
      log.info("No new cryptos to save");
    } else {
      log.info(
          "Saved {} cryptoCoins with Ids {}",
          savedCryptoCoins.size(),
          stringListToString(
              savedCryptoCoins.stream().map(cryptoCoin -> cryptoCoin.getId().toString()).toList()));
    }

    return savedCryptoCoins;
  }

  public CryptoCoinPrice saveCryptoCoinPrice(@NonNull CryptoCoinPrice cryptoCoinPrice) {
    log.info("CryptoCoinService - saveCryptoCoinPrice {}", cryptoCoinPrice);
    return cryptoCoinPriceRepository.save(cryptoCoinPrice);
  }

  public List<CryptoCoinPrice> saveCryptoCoinPrices(
      @NonNull List<CryptoCoinPrice> cryptoCoinPrices) {
    log.info(
        "CryptoCoinService - saveCryptoCoinPrices {}",
        stringListToString(
            cryptoCoinPrices.stream().map(CryptoCoinPrice::getId).map(String::valueOf).toList()));

    List<CryptoCoinPrice> savedCryptoCoinPrices =
        cryptoCoinPriceRepository.saveAll(cryptoCoinPrices);

    if (savedCryptoCoinPrices.isEmpty()) {
      log.info("No new cryptoCoinPrices to save");
    } else {
      log.info(
          "Saved {} cryptoCoinPrices with Ids {}",
          savedCryptoCoinPrices.size(),
          stringListToString(
              savedCryptoCoinPrices.stream()
                  .map(cryptoCoin -> cryptoCoin.getCoinInfo().getId().toString())
                  .toList()));
    }

    return savedCryptoCoinPrices;
  }

  public List<CryptoCoinPriceQuote> saveCryptoCoinPriceQuotes(
      @NonNull List<CryptoCoinPriceQuote> cryptoCoinPriceQuotes) {
    log.info(
        "CryptoCoinService - saveCryptoCoinPriceQuotes for cryptoCoins {}",
        stringListToString(
            cryptoCoinPriceQuotes.stream()
                .map(
                    cryptoCoinPriceQuote ->
                        cryptoCoinPriceQuote.getCoinPrice().getCoinInfo().getSymbol())
                .toList()));

    List<CryptoCoinPriceQuote> savedCryptoCoinPriceQuotes =
        cryptoCoinPriceQuoteRepository.saveAll(cryptoCoinPriceQuotes);

    if (savedCryptoCoinPriceQuotes.isEmpty()) {
      log.info("No new cryptoCoinPriceQuotes to save");
    } else {
      log.info(
          "Saved {} cryptoCoinPriceQuotes with Ids {}",
          savedCryptoCoinPriceQuotes.size(),
          stringListToString(
              savedCryptoCoinPriceQuotes.stream()
                  .map(cryptoCoin -> cryptoCoin.getCoinPrice().getCoinInfo().getId().toString())
                  .toList()));
    }

    return savedCryptoCoinPriceQuotes;
  }
}
