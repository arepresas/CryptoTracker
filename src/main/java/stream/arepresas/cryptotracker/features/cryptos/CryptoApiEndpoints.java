package stream.arepresas.cryptotracker.features.cryptos;

public final class CryptoApiEndpoints {

  public static final String CRYPTO_TASK_BASE_URL = "/v1/cryptoTask";
  public static final String CRYPTO_TASK_LAST_PRICES = "/lastPrices";

  public static final String CRYPTO_BASE_URL = "/v1/crypto";
  public static final String CRYPTO_LAST_LISTINGS = "/lastListing";
  public static final String CRYPTO_INFO = "/info/{cryptoIds}";
  public static final String CRYPTO_QUOTE = "/quote/{cryptoIds}/{currency}";
}
