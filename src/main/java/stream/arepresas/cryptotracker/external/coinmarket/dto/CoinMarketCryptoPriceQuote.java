package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketCryptoPriceQuote {
  private Double price;

  @JsonProperty("volume_24h")
  private Double volume24H;

  @JsonProperty("volume_change_24h")
  private Double volumeChange24H;

  @JsonProperty("percent_change_1h")
  private Double percentChange1H;

  @JsonProperty("percent_change_24h")
  private Double percentChange24H;

  @JsonProperty("percent_change_7d")
  private Double percentChange7D;

  @JsonProperty("percent_change_30d")
  private Double percentChange30D;

  @JsonProperty("percent_change_60d")
  private Double percentChange60D;

  @JsonProperty("percent_change_90d")
  private Double percentChange90D;

  @JsonProperty("market_cap")
  private Double marketCap;

  @JsonProperty("market_cap_dominance")
  private Double marketCapDominance;

  @JsonProperty("fully_diluted_market_cap")
  private Double fullyDilutedMarketCap;

  @JsonProperty("last_updated")
  private Date lastUpdated;
}
