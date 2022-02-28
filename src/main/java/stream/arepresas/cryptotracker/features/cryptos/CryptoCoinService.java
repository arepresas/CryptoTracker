package stream.arepresas.cryptotracker.features.cryptos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static stream.arepresas.cryptotracker.utils.DataUtils.stringListToString;

@RequiredArgsConstructor
@Service
@Slf4j
public class CryptoCoinService {
  private final CryptoCoinRepository cryptoCoinRepository;
  private final CryptoCoinPriceRepository cryptoCoinPriceRepository;

  public List<CryptoCoin> getCryptoInfo(List<Long> cryptoIds) {
    return cryptoCoinRepository.findAllById(cryptoIds);
  }

  public List<CryptoCoinPrice> getCryptoPrice(List<Long> cryptoIds) {
    return cryptoCoinPriceRepository.findAllById(cryptoIds);
  }

  public CryptoCoin saveCryptoCoin(@NonNull CryptoCoin cryptoCoin) {
    return cryptoCoinRepository.save(cryptoCoin);
  }

  public List<CryptoCoin> saveCryptoCoins(@NonNull List<CryptoCoin> cryptoCoins) {

    List<CryptoCoin> savedCryptoCoins = cryptoCoinRepository.saveAll(cryptoCoins);

    if (savedCryptoCoins.isEmpty()) {
      log.info("No new cryptos to save");
    } else {
      log.info(
          "Saved {} cryptoCoins with Ids {}",
          savedCryptoCoins.size(),
          stringListToString(
              savedCryptoCoins.stream()
                  .map(cryptoCoin -> cryptoCoin.getId().toString())
                  .collect(Collectors.toList())));
    }

    return savedCryptoCoins;
  }

  public CryptoCoinPrice saveCryptoCoinPrice(@NonNull CryptoCoinPrice cryptoCoinPrice) {
    return cryptoCoinPriceRepository.save(cryptoCoinPrice);
  }

  public List<CryptoCoinPrice> saveCryptoCoinPrices(
      @NonNull List<CryptoCoinPrice> cryptoCoinPrices) {

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
                  .collect(Collectors.toList())));
    }

    return savedCryptoCoinPrices;
  }
}
