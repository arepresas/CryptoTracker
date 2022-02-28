package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketCryptoData {
  private Long id;
  private Long rank;
  private String name;
  private String symbol;
  private String slug;
  private Long is_active;
  private Date first_historical_data;
  private Date last_historical_data;
  private CoinMarketCryptoPlatform platform;
}
