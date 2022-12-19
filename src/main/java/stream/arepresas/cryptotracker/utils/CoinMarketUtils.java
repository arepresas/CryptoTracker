package stream.arepresas.cryptotracker.utils;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoInfo;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPrice;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPriceQuote;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoin;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinPrice;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinQuote;
import stream.arepresas.cryptotracker.features.cryptos.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static stream.arepresas.cryptotracker.utils.DataUtils.listToString;

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
        .tags(listToString(coinMarketCryptoInfo.getTags()))
        .tagNames(listToString(coinMarketCryptoInfo.getTagNames()))
        .tagGroups(listToString(coinMarketCryptoInfo.getTagGroups()))
        .coinPrice(null)
        .build();
  }

  public static CryptoCoinPrice cryptoCoinPriceFromCoinMarketCryptoPrice(
      @NotNull CoinMarketCryptoPrice coinMarketCryptoPrice, @NotNull CryptoCoin cryptoCoin) {
    CryptoCoinPrice cryptoCoinPrice =
        CryptoCoinPrice.builder()
            .cmcRank(coinMarketCryptoPrice.getCmcRank())
            .numMarketPairs(coinMarketCryptoPrice.getNumMarketPairs())
            .circulatingSupply(coinMarketCryptoPrice.getCirculatingSupply())
            .totalSupply(coinMarketCryptoPrice.getTotalSupply())
            .maxSupply(coinMarketCryptoPrice.getMaxSupply())
            .dateAdded(coinMarketCryptoPrice.getDateAdded())
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

  public static List<CryptoCoinQuote> cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes(
      @NotNull Map<String, CoinMarketCryptoPriceQuote> quote,
      @NotNull CryptoCoinPrice cryptoCoinPrice) {
    return quote.entrySet().stream()
        .map(
            quoteEntry ->
                CryptoCoinQuote.builder()
                    .currency(Currency.valueOf(quoteEntry.getKey()))
                    .price(quoteEntry.getValue().getPrice())
                    .volume24h(quoteEntry.getValue().getVolume24H())
                    .volumeChange24h(quoteEntry.getValue().getVolumeChange24H())
                    .percentChange1h(quoteEntry.getValue().getPercentChange1H())
                    .percentChange24h(quoteEntry.getValue().getPercentChange24H())
                    .percentChange7d(quoteEntry.getValue().getPercentChange7D())
                    .percentChange30d(quoteEntry.getValue().getPercentChange30D())
                    .percentChange60d(quoteEntry.getValue().getPercentChange60D())
                    .percentChange90d(quoteEntry.getValue().getPercentChange90D())
                    .marketCap(quoteEntry.getValue().getMarketCap())
                    .marketCapDominance(quoteEntry.getValue().getMarketCapDominance())
                    .fullyDilutedMarketCap(quoteEntry.getValue().getFullyDilutedMarketCap())
                    .lastUpdated(quoteEntry.getValue().getLastUpdated())
                    .coinPrice(cryptoCoinPrice)
                    .build())
        .toList();
  }
}
