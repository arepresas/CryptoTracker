package stream.arepresas.cryptotracker.features.cryptos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CryptoCoinPriceRepository
    extends JpaRepository<CryptoCoinPrice, Long>, QuerydslPredicateExecutor<CryptoCoinPrice> {

  List<CryptoCoinPrice> findByCoinInfo_IdIsIn(Collection<Long> cryptoIds);
}
