package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketCryptoPriceQuote {
  private Double price;
  private Double volume_24h;
  private Double volume_change_24h;
  private Double percent_change_1h;
  private Double percent_change_24h;
  private Double percent_change_7d;
  private Double percent_change_30d;
  private Double percent_change_60d;
  private Double percent_change_90d;
  private Double market_cap;
  private Double market_cap_dominance;
  private Double fully_diluted_market_cap;
  private Date last_updated;
}
