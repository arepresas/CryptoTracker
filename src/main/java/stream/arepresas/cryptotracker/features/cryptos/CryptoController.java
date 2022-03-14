package stream.arepresas.cryptotracker.features.cryptos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static stream.arepresas.cryptotracker.utils.ExternalUtils.MAX_AGE_60;
import static stream.arepresas.cryptotracker.utils.ExternalUtils.X_CACHE_CONTROL;

@RestController
@RequiredArgsConstructor
@Tag(name = "CryptoCoin API")
@RequestMapping(value = CryptoApiEndpoints.CRYPTO_BASE_URL)
public class CryptoController {
  private final CryptoCoinService cryptoCoinService;

  @Operation(description = "Get all cryptos info")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_INFO_ALL)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<CryptoCoin>> getAllCryptoInfo() {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(cryptoCoinService.getAllCryptoInfo());
  }

  @Operation(description = "Get crypto(s) info")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_INFO)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<CryptoCoin>> getCryptoInfoByCryptoId(
      @PathVariable List<Long> cryptoIds) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(cryptoCoinService.getCryptoInfoByCryptoIds(cryptoIds));
  }

  @Operation(description = "Get crypto(s) price")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_PRICE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<CryptoCoinPrice>> getCryptoPriceByCryptoId(
      @PathVariable List<Long> cryptoIds) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(cryptoCoinService.getCryptoPriceByCryptoIds(cryptoIds));
  }

  @Operation(description = "Get crypto(s) price")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_QUOTE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<CryptoCoinPriceQuote>> getCryptoPriceQuotesByCryptoPriceId(
      @PathVariable Long cryptoPriceId) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(cryptoCoinService.getCryptoPriceQuotesByCryptoPriceId(cryptoPriceId));
  }
}
