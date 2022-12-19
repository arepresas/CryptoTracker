package stream.arepresas.cryptotracker.features.cryptos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Tag(name = "CryptoCoin API")
@RequestMapping(value = CryptoApiEndpoints.CRYPTO_BASE_URL)
public class CryptoController {
  private final CryptoCoinService cryptoCoinService;
  private final CryptoCoinMapper cryptoCoinMapper;
  private final CryptoCoinPriceMapper cryptoCoinPriceMapper;
  private final CryptoCoinQuoteMapper cryptoCoinQuoteMapper;

  @Operation(description = "Get crypto")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_COIN)
  @ResponseStatus(HttpStatus.OK)
  public EntityModel<CryptoCoinDto> getCrypto(@PathVariable Long cryptoId) {
    return cryptoCoinMapper.toDto(cryptoCoinService.getCryptoCoin(cryptoId)).toModel();
  }

  @Operation(description = "Get cryptos")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_COINS)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PagedModel<EntityModel<CryptoCoinDto>>> getCryptos(
      @Valid CryptoCoinCriteria cryptoCoinCriteria) {
    final var cryptoCoinDtos =
        cryptoCoinService.searchCryptoCoins(cryptoCoinCriteria).map(cryptoCoinMapper::toDto);
    final var resources = cryptoCoinDtos.map(CryptoCoinDto::toModel);
    final var link = linkTo(methodOn(this.getClass()).getCryptos(cryptoCoinCriteria)).withSelfRel();
    final var pageMetadata =
        new PagedModel.PageMetadata(
            resources.getSize(), resources.getNumber(), resources.getTotalElements());
    final var cryptoCoinResources = PagedModel.of(resources.getContent(), pageMetadata, link);

    return new ResponseEntity<>(cryptoCoinResources, HttpStatus.OK);
  }

  @Operation(description = "Get crypto price")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_PRICE)
  @ResponseStatus(HttpStatus.OK)
  public EntityModel<CryptoCoinPriceDto> getCryptoPrice(@PathVariable Long cryptoPriceId) {
    return cryptoCoinPriceMapper
        .toDto(cryptoCoinService.getCryptoCoinPrice(cryptoPriceId))
        .toModel();
  }

  @Operation(description = "Get crypto prices")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_PRICES)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PagedModel<EntityModel<CryptoCoinPriceDto>>> getCryptoPrices(
      @Valid CryptoCoinPriceCriteria cryptoCoinPriceCriteria) {
    final var cryptoCoinPriceDtos =
        cryptoCoinService
            .searchCryptoCoinPrices(cryptoCoinPriceCriteria)
            .map(cryptoCoinPriceMapper::toDto);
    final var resources = cryptoCoinPriceDtos.map(CryptoCoinPriceDto::toModel);
    final var link =
        linkTo(methodOn(this.getClass()).getCryptoPrices(cryptoCoinPriceCriteria)).withSelfRel();
    final var pageMetadata =
        new PagedModel.PageMetadata(
            resources.getSize(), resources.getNumber(), resources.getTotalElements());
    final var cryptoCoinPriceResources = PagedModel.of(resources.getContent(), pageMetadata, link);

    return new ResponseEntity<>(cryptoCoinPriceResources, HttpStatus.OK);
  }

  @Operation(description = "Get crypto quote")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_QUOTE)
  @ResponseStatus(HttpStatus.OK)
  public EntityModel<CryptoCoinQuoteDto> getCryptoQuote(@PathVariable Long cryptoQuoteId) {
    return cryptoCoinQuoteMapper
        .toDto(cryptoCoinService.getCryptoCoinQuote(cryptoQuoteId))
        .toModel();
  }

  @Operation(description = "Get crypto quotes")
  @GetMapping(value = CryptoApiEndpoints.CRYPTO_QUOTES)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PagedModel<EntityModel<CryptoCoinQuoteDto>>> getCryptoQuotes(
      @Valid CryptoCoinQuoteCriteria cryptoCoinQuoteCriteria) {
    final var cryptoCoinPriceDtos =
        cryptoCoinService
            .searchCryptoCoinQuotes(cryptoCoinQuoteCriteria)
            .map(cryptoCoinQuoteMapper::toDto);
    final var resources = cryptoCoinPriceDtos.map(CryptoCoinQuoteDto::toModel);
    final var link =
        linkTo(methodOn(this.getClass()).getCryptoQuotes(cryptoCoinQuoteCriteria)).withSelfRel();
    final var pageMetadata =
        new PagedModel.PageMetadata(
            resources.getSize(), resources.getNumber(), resources.getTotalElements());
    final var cryptoCoinPriceResources = PagedModel.of(resources.getContent(), pageMetadata, link);

    return new ResponseEntity<>(cryptoCoinPriceResources, HttpStatus.OK);
  }
}
