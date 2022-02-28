package stream.arepresas.cryptotracker.external.coinMarket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import stream.arepresas.cryptotracker.external.ExternalUtils;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketApiResponse;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketInfoApiResponse;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketPriceApiResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    return ExternalUtils.getResponse(
        coinMarketRestTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            createHttpEntity(),
            CoinMarketInfoApiResponse.class,
            params));
  }

  public CoinMarketApiResponse getCryptoLastPrices() {
    String url = mainUrl.concat("/v1/cryptocurrency/listings/latest");

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("start", "{start}")
            .queryParam("limit", "{limit}")
            .queryParam("convert", "{convert}")
            .encode()
            .toUriString();

    Map<String, ?> params = Map.of("start", 1, "limit", 5, "convert", "USD");

    return ExternalUtils.getResponse(
        coinMarketRestTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            createHttpEntity(),
            CoinMarketPriceApiResponse.class,
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
