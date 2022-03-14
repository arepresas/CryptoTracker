package stream.arepresas.cryptotracker.external.coinmarket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketApiResponse;
import stream.arepresas.cryptotracker.features.cryptos.CryptoApiEndpoints;
import stream.arepresas.cryptotracker.features.cryptos.Currency;

import java.util.List;

import static stream.arepresas.cryptotracker.utils.ExternalUtils.MAX_AGE_60;
import static stream.arepresas.cryptotracker.utils.ExternalUtils.X_CACHE_CONTROL;

@RestController
@RequiredArgsConstructor
@Tag(name = "CoinMarket API")
@RequestMapping(value = CryptoApiEndpoints.COIN_MARKET_CRYPTO_BASE_URL)
public class CoinMarketController {
  private final CoinMarketClient coinMarketClient;


  @Operation(description = "Get crypto(s) info")
  @GetMapping(value = CryptoApiEndpoints.COIN_MARKET_CRYPTO_INFO)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoInfo(@PathVariable List<Long> cryptoIds) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(coinMarketClient.getCryptoInfo(cryptoIds));
  }

  @Operation(description = "Get crypto(s) last listing(s)")
  @GetMapping(value = CryptoApiEndpoints.COIN_MARKET_CRYPTO_LAST_LISTINGS)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoLastListing(
          Integer start, Integer limit, Currency currency) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(coinMarketClient.getCryptoLastPrices(start, limit, currency));
  }

  @Operation(description = "Get crypto(s) quote(s)")
  @GetMapping(value = CryptoApiEndpoints.COIN_MARKET_CRYPTO_QUOTE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoQuote(
          @PathVariable List<Long> cryptoIds, @PathVariable Currency currency) {
    return ResponseEntity.status(HttpStatus.OK)
        .header(X_CACHE_CONTROL, MAX_AGE_60)
        .body(coinMarketClient.getCryptoPrices(cryptoIds, currency));
  }
}
