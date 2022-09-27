package stream.arepresas.cryptotracker.features.cryptos;

import lombok.*;
import org.springframework.hateoas.EntityModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCoinQuoteDto {
    private Long id;
    @NotNull
    private Currency currency;
    @NotNull
    private Double price;
    @NotNull
    private Double volume24h;
    @NotNull
    private Double volumeChange24h;
    @NotNull
    private Double percentChange1h;
    @NotNull
    private Double percentChange24h;
    @NotNull
    private Double percentChange7d;
    @NotNull
    private Double percentChange30d;
    @NotNull
    private Double percentChange60d;
    @NotNull
    private Double percentChange90d;
    @NotNull
    private Double marketCap;
    @NotNull
    private Double marketCapDominance;
    @NotNull
    private Double fullyDilutedMarketCap;
    @NotNull
    private Date lastUpdated;

    public EntityModel<CryptoCoinQuoteDto> toModel() {
        final EntityModel<CryptoCoinQuoteDto> resource = EntityModel.of(this);
        resource.add(linkTo(methodOn(CryptoController.class).getCryptoQuote(this.id)).withSelfRel());
        return resource;
    }
}
