package stream.arepresas.cryptotracker.features.cryptos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CryptoCoinPriceRepository
    extends JpaRepository<CryptoCoinPrice, Long>, QuerydslPredicateExecutor<CryptoCoinPrice> {

  @Query("select c from CryptoCoinPrice c where c.coinInfo.id in ?1")
  List<CryptoCoinPrice> findByCoinInfoIdIsIn(Collection<Long> cryptoIds);
}
