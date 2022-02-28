package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin")
public class CryptoCoin {
  @Id
  @Column(name = "id", nullable = false)
  private Long id; // CoinMarketId

  @NotNull private String symbol;
  @NotNull private String name;

  private String category;
  private String description;
  private String slug;
  private String logo;
  private String subreddit;
  private String tags; // Tag comma separated
  private String tagNames; // TagNames comma separated
  private String tagGroups; // TagGroups comma separated
  private String tokenAddress;

  @JsonIgnore
  @OneToMany(
      mappedBy = "coinInfo",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  private List<CryptoCoinPrice> coinPrice;
}
