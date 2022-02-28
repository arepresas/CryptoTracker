package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class CoinMarketCryptoPrice {
  private Long id;
  private String name;
  private String symbol;
  private String slug;
  private Long cmc_rank;
  private Long num_market_pairs;
  private Double circulating_supply;
  private Double total_supply;
  private Double max_supply;
  private Date last_updated;
  private Date date_added;
  private CoinMarketCryptoPlatform platform;
  private Map<String, CoinMarketCryptoPriceQuote> quote;
}
