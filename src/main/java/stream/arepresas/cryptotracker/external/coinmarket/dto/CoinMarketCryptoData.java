package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketCryptoData {
  private Long id;
  private Long rank;
  private String name;
  private String symbol;
  private String slug;

  @JsonProperty("is_active")
  private Long isActive;

  @JsonProperty("first_historical_data")
  private Date firstHistoricalData;

  @JsonProperty("last_historical_data")
  private Date lastHistoricalData;

  private CoinMarketCryptoPlatform platform;
}
