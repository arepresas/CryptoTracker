package stream.arepresas.cryptotracker.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoInfo;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPrice;
import stream.arepresas.cryptotracker.external.coinmarket.dto.CoinMarketCryptoPriceQuote;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoin;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinPrice;
import stream.arepresas.cryptotracker.features.cryptos.CryptoCoinPriceQuote;
import stream.arepresas.cryptotracker.features.cryptos.Currency;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            .cmcRank(coinMarketCryptoPrice.getCmcRank())
            .numMarketPairs(coinMarketCryptoPrice.getNumMarketPairs())
            .circulatingSupply(coinMarketCryptoPrice.getCirculatingSupply())
            .totalSupply(coinMarketCryptoPrice.getTotalSupply())
            .maxSupply(coinMarketCryptoPrice.getMaxSupply())
            .lastUpdated(coinMarketCryptoPrice.getLastUpdated())
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

  public static List<CryptoCoinPriceQuote> cryptoCoinPriceQuotesFromCoinMarketCryptoPriceQuotes(
      @NotNull Map<String, CoinMarketCryptoPriceQuote> quote,
      @NotNull CryptoCoinPrice cryptoCoinPrice) {
    return quote.entrySet().stream()
        .map(
            s ->
                CryptoCoinPriceQuote.builder()
                    .currency(Currency.valueOf(s.getKey()))
                    .price(s.getValue().getPrice())
                    .volume24h(s.getValue().getVolume24H())
                    .volumeChange24h(s.getValue().getVolumeChange24H())
                    .percentChange1h(s.getValue().getPercentChange1H())
                    .percentChange24h(s.getValue().getPercentChange24H())
                    .percentChange7d(s.getValue().getPercentChange7D())
                    .percentChange30d(s.getValue().getPercentChange30D())
                    .percentChange60d(s.getValue().getPercentChange60D())
                    .percentChange90d(s.getValue().getPercentChange90D())
                    .marketCap(s.getValue().getMarketCap())
                    .marketCapDominance(s.getValue().getMarketCapDominance())
                    .fullyDilutedMarketCap(s.getValue().getFullyDilutedMarketCap())
                    .lastUpdated(s.getValue().getLastUpdated())
                    .coinPrice(cryptoCoinPrice)
                    .build())
        .toList();
  }
}
