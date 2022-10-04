package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin")
public class CryptoCoin implements Serializable {
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
  @OneToOne(
      mappedBy = "coinInfo",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private CryptoCoinPrice coinPrice;
}
