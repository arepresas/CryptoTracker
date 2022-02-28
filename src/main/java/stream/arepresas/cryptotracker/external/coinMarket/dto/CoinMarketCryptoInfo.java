package stream.arepresas.cryptotracker.external.coinMarket.dto;

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

  private Date date_added;
  private String twitter_username;
  private int is_hidden;

  private Map<String, List<String>> urls;
}
