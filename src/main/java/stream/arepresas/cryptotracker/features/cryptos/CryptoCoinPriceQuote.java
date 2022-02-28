package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin_quote")
public class CryptoCoinPriceQuote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull private Currency currency;
  @NotNull private Double price;
  @NotNull private Double volume_24h;
  @NotNull private Double volume_change_24h;
  @NotNull private Double percent_change_1h;
  @NotNull private Double percent_change_24h;
  @NotNull private Double percent_change_7d;
  @NotNull private Double percent_change_30d;
  @NotNull private Double percent_change_60d;
  @NotNull private Double percent_change_90d;
  @NotNull private Double market_cap;
  @NotNull private Double market_cap_dominance;
  @NotNull private Double fully_diluted_market_cap;
  @NotNull private Date last_updated;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coin_price_id", referencedColumnName = "id", nullable = false)
  private CryptoCoinPrice coinPrice;
}
