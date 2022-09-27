package stream.arepresas.cryptotracker.features.cryptos;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stream.arepresas.cryptotracker.utils.DataUtils;

import java.util.List;

import static stream.arepresas.cryptotracker.utils.DataUtils.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CryptoCoinService {
  private final CryptoCoinRepository cryptoCoinRepository;
  private final CryptoCoinPriceRepository cryptoCoinPriceRepository;
  private final CryptoCoinQuoteRepository cryptoCoinQuoteRepository;

  // GET

  @Transactional(readOnly = true)
  public List<Long> getCryptoCoinIds() {
    log.info("CryptoCoinService - getCryptoCoinIds");
    return cryptoCoinRepository.findCryptoCoinIds();
  }

  @Transactional(readOnly = true)
  public CryptoCoin getCryptoCoin(final Long cryptoCoinId) {
    if (cryptoCoinId != null) {
      return this.cryptoCoinRepository.getById(cryptoCoinId);
    }
    return null;
  }

  public Page<CryptoCoin> searchCryptoCoins(final CryptoCoinCriteria cryptoCoinCriteria) {
    log.info("CryptoCoinService - searchCryptos");
    logCriteria(cryptoCoinCriteria);

    final var predicate = getSearchCryptoCoinPredicate(cryptoCoinCriteria);

    return cryptoCoinRepository.findAll(predicate, cryptoCoinCriteria.generatePageRequest());
  }

  private Predicate getSearchCryptoCoinPredicate(@NonNull final CryptoCoinCriteria cryptoCoinCriteria) {
    final QCryptoCoin qCryptoCoinPrice = QCryptoCoin.cryptoCoin;
    final BooleanBuilder predicate = new BooleanBuilder();

    predicate.and(qCryptoCoinPrice.id.isNotNull());

    if (cryptoCoinCriteria.getIds() != null && !cryptoCoinCriteria.getIds().isEmpty())
      predicate.and(qCryptoCoinPrice.id.in(cryptoCoinCriteria.getIds()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getSymbol()))
      predicate.and(qCryptoCoinPrice.symbol.likeIgnoreCase(cryptoCoinCriteria.getSymbol()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getName()))
      predicate.and(qCryptoCoinPrice.name.likeIgnoreCase(cryptoCoinCriteria.getName()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getCategory()))
      predicate.and(qCryptoCoinPrice.category.likeIgnoreCase(cryptoCoinCriteria.getCategory()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getSlug()))
      predicate.and(qCryptoCoinPrice.slug.likeIgnoreCase(cryptoCoinCriteria.getSlug()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getLogo()))
      predicate.and(qCryptoCoinPrice.logo.likeIgnoreCase(cryptoCoinCriteria.getLogo()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getSubreddit()))
      predicate.and(qCryptoCoinPrice.subreddit.likeIgnoreCase(cryptoCoinCriteria.getSubreddit()));

    if (!isNullOrEmpty(cryptoCoinCriteria.getTags())) {
      List<String> tags = DataUtils.stringToList(cryptoCoinCriteria.getTags());
      predicate.and(qCryptoCoinPrice.tags.in(tags));
    }

    if (!isNullOrEmpty(cryptoCoinCriteria.getTagNames())) {
      List<String> tagNames = DataUtils.stringToList(cryptoCoinCriteria.getTagNames());
      predicate.and(qCryptoCoinPrice.tagNames.in(tagNames));
    }

    if (!isNullOrEmpty(cryptoCoinCriteria.getTagGroups())) {
      List<String> tagGroups = DataUtils.stringToList(cryptoCoinCriteria.getTagGroups());
      predicate.and(qCryptoCoinPrice.tagGroups.in(tagGroups));
    }

    if (cryptoCoinCriteria.getTokenAddress() != null)
      predicate.and(qCryptoCoinPrice.tokenAddress.likeIgnoreCase(cryptoCoinCriteria.getTokenAddress()));

    return predicate;
  }

  @Transactional(readOnly = true)
  public CryptoCoinPrice getCryptoCoinPrice(final Long cryptoCoinPriceId) {
    return cryptoCoinPriceId != null ? this.cryptoCoinPriceRepository.getById(cryptoCoinPriceId) : null;
  }

  public Page<CryptoCoinPrice> searchCryptoCoinPrices(final CryptoCoinPriceCriteria cryptoCoinPriceCriteria) {
    log.info("CryptoCoinService - searchCryptoPrices");
    logCriteria(cryptoCoinPriceCriteria);

    final var predicate = getSearchCryptoCoinPricePredicate(cryptoCoinPriceCriteria);

    return cryptoCoinPriceRepository.findAll(predicate, cryptoCoinPriceCriteria.generatePageRequest());
  }

  private Predicate getSearchCryptoCoinPricePredicate(@NonNull CryptoCoinPriceCriteria cryptoCoinPriceCriteria) {
    final QCryptoCoinPrice qCryptoCoinPrice = QCryptoCoinPrice.cryptoCoinPrice;
    final BooleanBuilder predicate = new BooleanBuilder();

    predicate.and(qCryptoCoinPrice.id.isNotNull());

    if (!isNullOrEmpty(cryptoCoinPriceCriteria.getIds()))
      predicate.and(qCryptoCoinPrice.id.in(cryptoCoinPriceCriteria.getIds()));

    if (cryptoCoinPriceCriteria.getCmcRank() != null)
      predicate.and(qCryptoCoinPrice.cmcRank.eq(cryptoCoinPriceCriteria.getCmcRank()));

    if (cryptoCoinPriceCriteria.getNumMarketPairs() != null)
      predicate.and(qCryptoCoinPrice.numMarketPairs.eq(cryptoCoinPriceCriteria.getNumMarketPairs()));

    if (!isNullOrEmpty(cryptoCoinPriceCriteria.getCryptoCoinIds()))
      predicate.and(qCryptoCoinPrice.coinInfo.id.in(cryptoCoinPriceCriteria.getCryptoCoinIds()));

    if (!isNullOrEmpty(cryptoCoinPriceCriteria.getCryptoCoinSymbols()))
      predicate.and(qCryptoCoinPrice.coinInfo.symbol.in(cryptoCoinPriceCriteria.getCryptoCoinSymbols()));

    return predicate;
  }

  public CryptoCoinQuote getCryptoCoinQuote(Long cryptoCoinQuoteId) {
    return cryptoCoinQuoteId != null ? this.cryptoCoinQuoteRepository.getById(cryptoCoinQuoteId) : null;
  }

  public Page<CryptoCoinQuote> searchCryptoCoinQuotes(CryptoCoinQuoteCriteria cryptoCoinQuoteCriteria) {
    log.info("CryptoCoinService - searchCryptoQuotes");
    logCriteria(cryptoCoinQuoteCriteria);

    final var predicate = getSearchCryptoCoinQuotePredicate(cryptoCoinQuoteCriteria);

    return cryptoCoinQuoteRepository.findAll(predicate, cryptoCoinQuoteCriteria.generatePageRequest());
  }

  private Predicate getSearchCryptoCoinQuotePredicate(@NonNull CryptoCoinQuoteCriteria cryptoCoinQuoteCriteria) {
    final QCryptoCoinQuote qCryptoCoinQuote = QCryptoCoinQuote.cryptoCoinQuote;
    final BooleanBuilder predicate = new BooleanBuilder();

    predicate.and(qCryptoCoinQuote.id.isNotNull());

    if (!isNullOrEmpty(cryptoCoinQuoteCriteria.getIds()))
      predicate.and(qCryptoCoinQuote.id.in(cryptoCoinQuoteCriteria.getIds()));

    if (!isNullOrEmpty(cryptoCoinQuoteCriteria.getCurrency()))
      predicate.and(qCryptoCoinQuote.currency.stringValue().eq(cryptoCoinQuoteCriteria.getCurrency()));

    if (cryptoCoinQuoteCriteria.getLastUpdatedBefore() != null)
      predicate.and(qCryptoCoinQuote.lastUpdated.before(cryptoCoinQuoteCriteria.getLastUpdatedBefore()));

    if (cryptoCoinQuoteCriteria.getLastUpdatedAfter() != null)
      predicate.and(qCryptoCoinQuote.lastUpdated.after(cryptoCoinQuoteCriteria.getLastUpdatedAfter()));

    return predicate;
  }

  // SAVE

  public List<CryptoCoin> saveCryptoCoins(@NonNull List<CryptoCoin> cryptoCoins) {
    log.info(
            "CryptoCoinService - saveCryptoCoins {}",
            stringListToString(
                    cryptoCoins.stream().map(CryptoCoin::getId).map(String::valueOf).toList()));

    List<CryptoCoin> savedCryptoCoins = cryptoCoinRepository.saveAll(cryptoCoins);

    if (savedCryptoCoins.isEmpty()) {
      log.info("No new cryptos to save");
    } else {
      log.info(
              "Saved {} cryptoCoins with Ids {}",
              savedCryptoCoins.size(),
              stringListToString(
                      savedCryptoCoins.stream().map(cryptoCoin -> cryptoCoin.getId().toString()).toList()));
    }

    return savedCryptoCoins;
  }

  public CryptoCoinPrice saveCryptoCoinPrice(@NonNull CryptoCoinPrice cryptoCoinPrice) {
    log.info("CryptoCoinService - saveCryptoCoinPrice {}", cryptoCoinPrice);
    return cryptoCoinPriceRepository.save(cryptoCoinPrice);
  }

  public List<CryptoCoinPrice> saveCryptoCoinPrices(
          @NonNull List<CryptoCoinPrice> cryptoCoinPrices) {
    log.info(
            "CryptoCoinService - saveCryptoCoinPrices {}",
            stringListToString(
                    cryptoCoinPrices.stream().map(CryptoCoinPrice::getId).map(String::valueOf).toList()));

    List<CryptoCoinPrice> savedCryptoCoinPrices =
            cryptoCoinPriceRepository.saveAll(cryptoCoinPrices);

    if (savedCryptoCoinPrices.isEmpty()) {
      log.info("No new cryptoCoinPrices to save");
    } else {
      log.info(
              "Saved {} cryptoCoinPrices with Ids {}",
              savedCryptoCoinPrices.size(),
              stringListToString(
                      savedCryptoCoinPrices.stream()
                              .map(cryptoCoin -> cryptoCoin.getCoinInfo().getId().toString())
                              .toList()));
    }

    return savedCryptoCoinPrices;
  }

  public List<CryptoCoinQuote> saveCryptoCoinPriceQuotes(
          @NonNull List<CryptoCoinQuote> cryptoCoinQuotes) {
    log.info(
            "CryptoCoinService - saveCryptoCoinPriceQuotes for cryptoCoins {}",
            stringListToString(
                    cryptoCoinQuotes.stream()
                            .map(
                                    cryptoCoinPriceQuote ->
                                            cryptoCoinPriceQuote.getCoinPrice().getCoinInfo().getSymbol())
                            .toList()));

    List<CryptoCoinQuote> savedCryptoCoinQuotes =
            cryptoCoinQuoteRepository.saveAll(cryptoCoinQuotes);

    if (savedCryptoCoinQuotes.isEmpty()) {
      log.info("No new cryptoCoinPriceQuotes to save");
    } else {
      log.info(
              "Saved {} cryptoCoinPriceQuotes with Ids {}",
              savedCryptoCoinQuotes.size(),
              stringListToString(
                      savedCryptoCoinQuotes.stream()
                              .map(cryptoCoin -> cryptoCoin.getCoinPrice().getCoinInfo().getId().toString())
                              .toList()));
    }

    return savedCryptoCoinQuotes;
  }
}
