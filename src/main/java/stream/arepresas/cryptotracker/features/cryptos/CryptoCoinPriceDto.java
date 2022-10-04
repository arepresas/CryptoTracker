package stream.arepresas.cryptotracker.features.cryptos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCoinPriceDto {
  private Long id;
  private Long cmcRank;
  private Long numMarketPairs;
  private Double circulatingSupply;
  private Double totalSupply;
  private Double maxSupply;
  private Date dateAdded;
  private Long platformId;
  private List<CryptoCoinQuoteDto> coinPriceQuotes;

  public EntityModel<CryptoCoinPriceDto> toModel() {
    final EntityModel<CryptoCoinPriceDto> resource = EntityModel.of(this);
    resource.add(linkTo(methodOn(CryptoController.class).getCrypto(this.id)).withSelfRel());
    return resource;
  }
}
