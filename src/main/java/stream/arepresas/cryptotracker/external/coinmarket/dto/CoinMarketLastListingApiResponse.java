package stream.arepresas.cryptotracker.external.coinmarket.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CoinMarketLastListingApiResponse implements CoinMarketApiResponse {
  private CoinMarketApiStatus status;
  private ArrayList<CoinMarketCryptoPrice> data;
}
