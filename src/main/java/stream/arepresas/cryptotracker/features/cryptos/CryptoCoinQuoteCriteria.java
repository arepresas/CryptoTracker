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
public class CryptoCoinQuoteCriteria extends PaginationCriteria {

    private List<Long> ids;
    private String currency;
    private Date lastUpdatedBefore;
    private Date lastUpdatedAfter;

    public PageRequest generatePageRequest() {
        var initSort = Sort.by(Sort.Order.asc("id"));
        return super.generatePageRequest(initSort);
    }
}
