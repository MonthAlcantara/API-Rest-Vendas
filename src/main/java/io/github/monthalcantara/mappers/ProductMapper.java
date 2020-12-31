package io.github.monthalcantara.mappers;


import io.github.monthalcantara.dto.request.ProductDTO;
import io.github.monthalcantara.dto.response.ProductResponseDTO;
import io.github.monthalcantara.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    ProductResponseDTO toProductResponseDTO(Product product);

    Product toProduct(ProductDTO productDTO);

    Product productResponseDTOToProduct(ProductResponseDTO productResponseDTO);

}
