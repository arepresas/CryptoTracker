package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Data
public class CoinMarketCryptoPrice {
  private int id;
  private String name;
  private String symbol;
  private String slug;
  private int cmc_rank;
  private int num_market_pairs;
  private double circulating_supply;
  private double total_supply;
  private double max_supply;
  private Date last_updated;
  private Date date_added;
  private ArrayList<String> tags;
  private CoinMarketCryptoPlatform platform;
  private Map<String, CoinMarketCryptoPriceQuote> quote;
}
