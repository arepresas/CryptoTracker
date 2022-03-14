package stream.arepresas.cryptotracker.features.cryptos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoCoinPriceQuoteRepository
    extends JpaRepository<CryptoCoinPriceQuote, Long>,
        QuerydslPredicateExecutor<CryptoCoinPriceQuote> {
  @Query("select c from CryptoCoinPriceQuote c where c.coinPrice.id = ?1")
  List<CryptoCoinPriceQuote> findByCoinPriceId(Long id);
}
