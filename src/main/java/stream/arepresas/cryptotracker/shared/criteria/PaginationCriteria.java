package stream.arepresas.cryptotracker.shared.criteria;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class PaginationCriteria {
  private Integer pageNumber;

  private Integer resultsPerPage;

  private String sortBy;

  private Direction sortDirection;

  protected PageRequest generatePageRequest(Sort initSort) {
    Sort sort = initSort != null ? initSort : Sort.unsorted();
    if (sortBy != null && sortDirection != null) {
      sort = Sort.by(new Order(sortDirection, sortBy));
    }
    PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE, sort);
    if (pageNumber != null && resultsPerPage != null) {
      pageRequest = PageRequest.of(pageNumber, resultsPerPage, sort);
    }
    return pageRequest;
  }
}
