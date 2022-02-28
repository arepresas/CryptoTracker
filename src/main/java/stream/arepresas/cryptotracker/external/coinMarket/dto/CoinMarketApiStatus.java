package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CoinMarketApiStatus {
  private Date timestamp;
  private Long error_code;
  private String error_message;
  private Long elapsed;
  private Long credit_count;
}
