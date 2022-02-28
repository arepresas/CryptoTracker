package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin_price")
public class CryptoCoinPrice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private Long cmc_rank;
  private Long num_market_pairs;
  private Double circulating_supply;
  private Double total_supply;
  private Double max_supply;
  private Date last_updated;
  private Date date_added;
  private Long platformId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coin_info_id", referencedColumnName = "id", nullable = false)
  private CryptoCoin coinInfo;

  @JsonIgnore
  @OneToMany(
      mappedBy = "coinPrice",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  private List<CryptoCoinPriceQuote> coinPriceQuotes;
}
