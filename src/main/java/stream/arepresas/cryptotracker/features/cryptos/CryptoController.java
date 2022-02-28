package stream.arepresas.cryptotracker.features.cryptos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stream.arepresas.cryptotracker.external.coinMarket.CoinMarketClient;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketApiResponse;

import java.util.List;

@RestController
@Tag(name = "Coin Market API")
@RequestMapping(value = CryptoApiEndpoints.CRYPTO_BASE_URL)
public class CryptoController {
  private final CoinMarketClient coinMarketClient;

  public CryptoController(CoinMarketClient coinMarketClient) {
    this.coinMarketClient = coinMarketClient;
  }

  @Operation(description = "Get crypto(s) info")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_INFO)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoInfo(@PathVariable List<Long> cryptoIds) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("X-Cache-Control", "max-age=60")
        .body(coinMarketClient.getCryptoInfo(cryptoIds));
  }

  @Operation(description = "Get crypto(s) last listing(s)")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_LAST_LISTINGS)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoLastListing(
      Integer start, Integer limit, Currency currency) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("X-Cache-Control", "max-age=60")
        .body(coinMarketClient.getCryptoLastPrices(start, limit, currency));
  }

  @Operation(description = "Get crypto(s) quote(s)")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_QUOTE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoQuote(
      @PathVariable List<Long> cryptoIds, @PathVariable Currency currency) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("X-Cache-Control", "max-age=60")
        .body(coinMarketClient.getCryptoPrices(cryptoIds, currency));
  }
}
