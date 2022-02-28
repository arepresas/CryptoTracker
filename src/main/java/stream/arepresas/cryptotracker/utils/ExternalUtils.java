package stream.arepresas.cryptotracker.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketApiResponse;

@Slf4j
@UtilityClass
public final class ExternalUtils {
  public static CoinMarketApiResponse getResponse(ResponseEntity response) {
    if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
      return (CoinMarketApiResponse) response.getBody();
    } else {
      log.error("ERROR in API Call {}", response);
      return null;
    }
  }
}
