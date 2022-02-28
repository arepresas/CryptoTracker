package stream.arepresas.cryptotracker.external.coinMarket;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketApiResponse;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketInfoApiResponse;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketLastListingApiResponse;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketLastQuoteApiResponse;
import stream.arepresas.cryptotracker.features.cryptos.Currency;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static stream.arepresas.cryptotracker.utils.ExternalUtils.getResponse;

@Service
@Slf4j
public class CoinMarketClient {

  private final RestTemplate coinMarketRestTemplate;

  @Value("${coinMarket.api.key}")
  private String apiKey;

  @Value("${coinMarket.api.url}")
  private String mainUrl;

  public CoinMarketClient(RestTemplate coinMarketRestTemplate) {
    this.coinMarketRestTemplate = coinMarketRestTemplate;
  }

  public CoinMarketApiResponse getCryptoInfo(List<Long> cryptoIds) {
    String url = mainUrl.concat("/v2/cryptocurrency/info");

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url).queryParam("id", "{id}").encode().toUriString();

    Map<String, ?> params =
        Map.of("id", cryptoIds.stream().map(String::valueOf).collect(Collectors.joining(",")));

    return getResponse(
        coinMarketRestTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            createHttpEntity(),
            CoinMarketInfoApiResponse.class,
            params));
  }

  public CoinMarketApiResponse getCryptoLastPrices(
      Integer start, Integer limit, Currency currency) {
    String url = mainUrl.concat("/v1/cryptocurrency/listings/latest");

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("start", "{start}")
            .queryParam("limit", "{limit}")
            .queryParam("convert", "{convert}")
            .encode()
            .toUriString();

    Map<String, ?> params = Map.of("start", start, "limit", limit, "convert", currency);

    return getResponse(
        coinMarketRestTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            createHttpEntity(),
            CoinMarketLastListingApiResponse.class,
            params));
  }

  public CoinMarketApiResponse getCryptoPrices(
      @NonNull List<Long> cryptoIds, @NonNull Currency currency) {
    String url = mainUrl.concat("/v2/cryptocurrency/quotes/latest");

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("id", "{id}")
            .queryParam("convert", "{convert}")
            .encode()
            .toUriString();

    Map<String, ?> params =
        Map.of(
            "id",
            cryptoIds.stream().map(String::valueOf).collect(Collectors.joining(",")),
            "convert",
            currency);

    return getResponse(
        coinMarketRestTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            createHttpEntity(),
            CoinMarketLastQuoteApiResponse.class,
            params));
  }

  //  TODO
  // Price Conversion v2

  private HttpEntity createHttpEntity() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
    httpHeaders.add("X-CMC_PRO_API_KEY", apiKey);

    return new HttpEntity(httpHeaders);
  }
}
