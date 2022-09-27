package stream.arepresas.cryptotracker.features.cryptos;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import stream.arepresas.cryptotracker.shared.criteria.PaginationCriteria;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCoinPriceCriteria extends PaginationCriteria {
    private List<Long> ids;
    private Long cmcRank;
    private Long numMarketPairs;
    private Double circulatingSupply;
    private Double totalSupply;
    private Double maxSupply;
    private Date dateAdded;
    private Long platformId;
    private List<Long> cryptoCoinIds;
    private List<String> cryptoCoinSymbols;

    public PageRequest generatePageRequest() {
        var initSort = Sort.by(Sort.Order.asc("id"));
        return super.generatePageRequest(initSort);
    }
}
