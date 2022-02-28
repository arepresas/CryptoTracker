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
@Tag(name = "Crypto")
@RequestMapping(value = "/v1/crypto")
public class CryptoController {
  private final CoinMarketClient coinMarketClient;

  public CryptoController(CoinMarketClient coinMarketClient) {
    this.coinMarketClient = coinMarketClient;
  }

  @Operation(description = "Get crypto(s) info")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_INFO)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCrypto(@PathVariable List<Long> cryptoIds) {
    return ResponseEntity.status(HttpStatus.OK)
        .header("X-Cache-Control", "max-age=60")
        .body(coinMarketClient.getCryptoInfo(cryptoIds));
  }

  @Operation(description = "Get crypto(s) price(s)")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_LAST_PRICES)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CoinMarketApiResponse> getCryptoLastPrices() {
    return ResponseEntity.status(HttpStatus.OK)
        .header("X-Cache-Control", "max-age=60")
        .body(coinMarketClient.getCryptoLastPrices());
  }
}
