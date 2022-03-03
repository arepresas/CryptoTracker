package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class CoinMarketCryptoPrice {
  private Long id;
  private String name;
  private String symbol;
  private String slug;

  @JsonProperty("cmc_rank")
  private Long cmcRank;

  @JsonProperty("num_market_pairs")
  private Long numMarketPairs;

  @JsonProperty("circulating_supply")
  private Double circulatingSupply;

  @JsonProperty("total_supply")
  private Double totalSupply;

  @JsonProperty("max_supply")
  private Double maxSupply;

  @JsonProperty("last_updated")
  private Date lastUpdated;

  @JsonProperty("date_added")
  private Date dateAdded;

  private CoinMarketCryptoPlatform platform;
  private Map<String, CoinMarketCryptoPriceQuote> quote;
}
