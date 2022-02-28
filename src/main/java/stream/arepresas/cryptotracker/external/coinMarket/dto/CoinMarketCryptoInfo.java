package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CoinMarketCryptoInfo {
  private int id;
  private String name;
  private String symbol;
  private String category;
  private String description;
  private String slug;
  private String logo;
  private String subreddit;
  private ArrayList<String> tags;

  //  @JsonProperty("tag-names")
  private ArrayList<String> tagNames;

  //  @JsonProperty("tag-groups")
  private ArrayList<String> tagGroups;

  private CoinMarketCryptoInfoUrls urls;
  private Date date_added;
  private String twitter_username;
  private int is_hidden;
}
