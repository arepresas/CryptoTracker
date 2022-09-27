package stream.arepresas.cryptotracker.features.cryptos;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoCoinPriceMapper {

    CryptoCoin toEntity(CryptoCoinPriceDto dto);

    List<CryptoCoin> toEntities(List<CryptoCoinPriceDto> dtos);

    CryptoCoinPriceDto toDto(CryptoCoinPrice cryptoCoinPrice);

    List<CryptoCoinPriceDto> toDtos(List<CryptoCoinPrice> cryptoCoinPrices);

}
