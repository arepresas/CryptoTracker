package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketApiStatus {
  private Date timestamp;

  @JsonProperty("error_code")
  private Long errorCode;

  @JsonProperty("error_message")
  private String errorMessage;

  private Long elapsed;

  @JsonProperty("credit_count")
  private Long creditCount;
}
