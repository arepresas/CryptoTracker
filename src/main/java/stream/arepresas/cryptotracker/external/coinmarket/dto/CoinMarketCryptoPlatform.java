package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoinMarketCryptoPlatform {
  private Long id;
  private String name;
  private String symbol;
  private String slug;

  @JsonProperty("token_address")
  private String tokenAddress;
}
