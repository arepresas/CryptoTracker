package stream.arepresas.cryptotracker.features.cryptos;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoCoinMapper {

  CryptoCoin toEntity(CryptoCoinDto dto);

  List<CryptoCoin> toEntities(List<CryptoCoinDto> dtos);

  CryptoCoinDto toDto(CryptoCoin cryptoCoin);

  List<CryptoCoinDto> toDtos(List<CryptoCoin> cryptoCoins);
}
