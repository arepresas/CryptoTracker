package stream.arepresas.cryptotracker.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketCryptoInfo;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketCryptoPrice;
import stream.arepresas.cryptotracker.external.coinMarket.dto.CoinMarketCryptoPriceQuote;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoin;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinPrice;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinPriceQuote;
import stream.arepresas.cryptotracker.features.cryptos.Currency;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static stream.arepresas.cryptotracker.utils.DataUtils.stringListToString;

@UtilityClass
public class CoinMarketUtils {
  public static CryptoCoin cryptoCoinFromCoinMarketCryptoInfo(
      @NonNull CoinMarketCryptoInfo coinMarketCryptoInfo) {
    return CryptoCoin.builder()
        .id(coinMarketCryptoInfo.getId())
        .symbol(coinMarketCryptoInfo.getSymbol())
        .name(coinMarketCryptoInfo.getName())
        .category(coinMarketCryptoInfo.getCategory())
        .description(coinMarketCryptoInfo.getDescription())
        .slug(coinMarketCryptoInfo.getSlug())
        .logo(coinMarketCryptoInfo.getLogo())
        .subreddit(coinMarketCryptoInfo.getSubreddit())
        .tags(stringListToString(coinMarketCryptoInfo.getTags()))
        .tagNames(stringListToString(coinMarketCryptoInfo.getTagNames()))
        .tagGroups(stringListToString(coinMarketCryptoInfo.getTagGroups()))
        .coinPrice(new ArrayList<>())
        .build();
  }

  public static CryptoCoinPrice cryptoCoinPriceFromCoinMarketCryptoPrice(
      @NotNull CoinMarketCryptoPrice coinMarketCryptoPrice, @NotNull CryptoCoin cryptoCoin) {
    CryptoCoinPrice cryptoCoinPrice =
        CryptoCoinPrice.builder()
            .cmc_rank(coinMarketCryptoPrice.getCmc_rank())
            .num_market_pairs(coinMarketCryptoPrice.getNum_market_pairs())
            .circulating_supply(coinMarketCryptoPrice.getCirculating_supply())
            .total_supply(coinMarketCryptoPrice.getTotal_supply())
            .max_supply(coinMarketCryptoPrice.getMax_supply())
            .last_updated(coinMarketCryptoPrice.getLast_updated())
            .date_added(coinMarketCryptoPrice.getDate_added())
            .platformId(
                Objects.isNull(coinMarketCryptoPrice.getPlatform())
                    ? null
                    : coinMarketCryptoPrice.getPlatform().getId())
            .coinPriceQuotes(new ArrayList<>())
            .coinInfo(cryptoCoin)
            .build();
    cryptoCoinPrice
        .getCoinPriceQuotes()
        .addAll(
            cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes(
                coinMarketCryptoPrice.getQuote(), cryptoCoinPrice));

    return cryptoCoinPrice;
  }

  public static List<CryptoCoinPriceQuote> cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes(
      @NotNull Map<String, CoinMarketCryptoPriceQuote> quote,
      @NotNull CryptoCoinPrice cryptoCoinPrice) {
    return quote.entrySet().stream()
        .map(
            s ->
                CryptoCoinPriceQuote.builder()
                    .currency(Currency.valueOf(s.getKey()))
                    .price(s.getValue().getPrice())
                    .volume_24h(s.getValue().getVolume_24h())
                    .volume_change_24h(s.getValue().getVolume_change_24h())
                    .percent_change_1h(s.getValue().getPercent_change_1h())
                    .percent_change_24h(s.getValue().getPercent_change_24h())
                    .percent_change_7d(s.getValue().getPercent_change_7d())
                    .percent_change_30d(s.getValue().getPercent_change_30d())
                    .percent_change_60d(s.getValue().getPercent_change_60d())
                    .percent_change_90d(s.getValue().getPercent_change_90d())
                    .market_cap(s.getValue().getMarket_cap())
                    .market_cap_dominance(s.getValue().getMarket_cap_dominance())
                    .fully_diluted_market_cap(s.getValue().getFully_diluted_market_cap())
                    .last_updated(s.getValue().getLast_updated())
                    .coinPrice(cryptoCoinPrice)
                    .build())
        .collect(Collectors.toList());
  }
}
