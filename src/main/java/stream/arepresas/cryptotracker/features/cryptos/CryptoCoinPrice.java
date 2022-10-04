package stream.arepresas.cryptotracker.features.cryptos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_coin_price")
public class CryptoCoinPrice implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private Long cmcRank;
  private Long numMarketPairs;
  private Double circulatingSupply;
  private Double totalSupply;
  private Double maxSupply;
  private Date dateAdded;
  private Long platformId;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "coin_info_id", referencedColumnName = "id")
  private CryptoCoin coinInfo;

  @JsonIgnore
  @OneToMany(
      mappedBy = "coinPrice",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private List<CryptoCoinQuote> coinPriceQuotes;
}
