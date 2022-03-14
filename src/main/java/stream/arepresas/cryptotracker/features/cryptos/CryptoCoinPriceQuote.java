package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin_quote")
public class CryptoCoinPriceQuote implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull private Currency currency;
  @NotNull private Double price;
  @NotNull private Double volume24h;
  @NotNull private Double volumeChange24h;
  @NotNull private Double percentChange1h;
  @NotNull private Double percentChange24h;
  @NotNull private Double percentChange7d;
  @NotNull private Double percentChange30d;
  @NotNull private Double percentChange60d;
  @NotNull private Double percentChange90d;
  @NotNull private Double marketCap;
  @NotNull private Double marketCapDominance;
  @NotNull private Double fullyDilutedMarketCap;
  @NotNull private Date lastUpdated;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coin_price_id", referencedColumnName = "id", nullable = false)
  private CryptoCoinPrice coinPrice;
}
