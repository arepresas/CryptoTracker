package stream.arepresas.cryptotracker.external.coinMarket.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CoinMarketCryptoInfoUrls {
  private ArrayList<String> website;
  private ArrayList<String> twitter;
  private ArrayList<String> chat;
  private ArrayList<String> explorer;
  private ArrayList<String> reddit;
  private ArrayList<String> technical_doc;
  private ArrayList<String> source_code;
  private ArrayList<String> announcement;
}
