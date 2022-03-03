package stream.arepresas.cryptotracker.external.coinmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CoinMarketCryptoInfo {
  private Long id;
  private String name;
  private String symbol;
  private String category;
  private String description;
  private String slug;
  private String logo;
  private String subreddit;
  private ArrayList<String> tags;

  @JsonProperty("tag-names")
  private ArrayList<String> tagNames;

  @JsonProperty("tag-groups")
  private ArrayList<String> tagGroups;

  @JsonProperty("date_added")
  private Date dateAdded;

  @JsonProperty("twitter_username")
  private String twitterUsername;

  @JsonProperty("is_hidden")
  private int isHidden;

  private Map<String, List<String>> urls;
}
