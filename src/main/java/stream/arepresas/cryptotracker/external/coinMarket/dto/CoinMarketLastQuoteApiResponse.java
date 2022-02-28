package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CoinMarketLastQuoteApiResponse extends CoinMarketApiResponse {
  private CoinMarketApiStatus status;
  private Map<String, CoinMarketCryptoPrice> data;
}
