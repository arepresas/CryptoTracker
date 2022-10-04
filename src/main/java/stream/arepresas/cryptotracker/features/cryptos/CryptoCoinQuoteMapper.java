package stream.arepresas.cryptotracker.features.cryptos;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoCoinQuoteMapper {

  CryptoCoin toEntity(CryptoCoinQuoteDto dto);

  List<CryptoCoin> toEntities(List<CryptoCoinQuoteDto> dtos);

  CryptoCoinQuoteDto toDto(CryptoCoinQuote cryptoCoinQuote);

  List<CryptoCoinQuoteDto> toDtos(List<CryptoCoinQuote> cryptoCoinQuotes);
}
