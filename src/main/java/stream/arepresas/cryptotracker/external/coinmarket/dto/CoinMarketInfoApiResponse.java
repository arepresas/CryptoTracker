package stream.arepresas.cryptotracker.external.coinmarket.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CoinMarketInfoApiResponse implements CoinMarketApiResponse {
  private CoinMarketApiStatus status;
  private Map<String, CoinMarketCryptoInfo> data;
}
