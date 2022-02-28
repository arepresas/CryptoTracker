package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketApiStatus {
  private Date timestamp;
  private int error_code;
  private String error_message;
  private int elapsed;
  private int credit_count;
}
