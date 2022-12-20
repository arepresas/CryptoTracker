package stream.arepresas.cryptotracker.features.cryptos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

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

  public EntityModel<CryptoCoinDto> toModel() {
    final EntityModel<CryptoCoinDto> resource = EntityModel.of(this);
    resource.add(linkTo(methodOn(CryptoController.class).getCrypto(this.id)).withSelfRel());
    resource.add(
        WebMvcLinkBuilder.linkTo(
                methodOn(CryptoController.class)
                    .getCryptoPrices(
                        CryptoCoinPriceCriteria.builder().cryptoCoinIds(List.of(this.id)).build()))
            .withRel("coinPrice"));
    return resource;
  }
}
