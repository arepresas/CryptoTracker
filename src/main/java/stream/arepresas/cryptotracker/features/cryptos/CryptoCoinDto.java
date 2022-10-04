package stream.arepresas.cryptotracker.features.cryptos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCoinDto {
  private Long id; // CoinMarketId

  @NotNull private String symbol;
  @NotNull private String name;
  @NotNull private String category;
  @NotNull private String description;
  private String slug;
  private String logo;
  private String subreddit;
  private String tags; // Tag comma separated
  private String tagNames; // TagNames comma separated
  private String tagGroups; // TagGroups comma separated
  private String tokenAddress;
  private CryptoCoinPriceDto coinPrice;

  public EntityModel<CryptoCoinDto> toModel() {
    final EntityModel<CryptoCoinDto> resource = EntityModel.of(this);
    resource.add(linkTo(methodOn(CryptoController.class).getCrypto(this.id)).withSelfRel());
    return resource;
  }
}
