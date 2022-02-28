package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

@Data
public class CoinMarketCryptoPlatform {
  private int id;
  private String name;
  private String symbol;
  private String slug;
  private String token_address;
}
