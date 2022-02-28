package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CoinMarketPriceApiResponse extends CoinMarketApiResponse {
  private CoinMarketApiStatus status;
  private ArrayList<CoinMarketCryptoPrice> data;
}
