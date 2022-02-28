package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketCryptoPriceQuote {
  private double price;
  private double volume_24h;
  private double volume_change_24h;
  private double percent_change_1h;
  private double percent_change_24h;
  private double percent_change_7d;
  private double percent_change_30d;
  private double percent_change_60d;
  private double percent_change_90d;
  private double market_cap;
  private double market_cap_dominance;
  private double fully_diluted_market_cap;
  private Date last_updated;
}
