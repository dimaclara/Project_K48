package teck.marie.productInventory.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import teck.marie.productInventory.model.Product;
import teck.marie.productInventory.model.dto.ProductRequestDto;
import teck.marie.productInventory.model.dto.ProductResponseDto;


@Mapper(componentModel = "Spring")
public interface ProductMapper {


    Product toEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toResponseDto(Product product);

    ProductRequestDto toDto(Product product);

    //void copy(ProductRequestDto productRequestDto, @MappingTarget  ProductResponseDto productResponseDto);

}
