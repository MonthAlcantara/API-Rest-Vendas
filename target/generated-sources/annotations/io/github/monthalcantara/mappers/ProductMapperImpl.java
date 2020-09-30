package io.github.monthalcantara.mappers;

import io.github.monthalcantara.dto.request.ProductDTO;
import io.github.monthalcantara.dto.response.ProductResponseDTO;
import io.github.monthalcantara.model.Product;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T18:36:47-0300",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 11.0.8 (JetBrains s.r.o.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );

        return productDTO;
    }

    @Override
    public ProductResponseDTO toProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );

        return productResponseDTO;
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );

        return product;
    }

    @Override
    public Product productResponseDTOToProduct(ProductResponseDTO productResponseDTO) {
        if ( productResponseDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setDescription( productResponseDTO.getDescription() );
        product.setPrice( productResponseDTO.getPrice() );

        return product;
    }
}
