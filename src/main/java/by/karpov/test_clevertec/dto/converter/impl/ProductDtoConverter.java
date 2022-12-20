package by.karpov.test_clevertec.dto.converter.impl;

import by.karpov.test_clevertec.dto.ProductDto;
import by.karpov.test_clevertec.dto.converter.DtoConverter;
import by.karpov.test_clevertec.model.Product;
import org.springframework.stereotype.Component;

@Component("productDtoConverter")
public class ProductDtoConverter implements DtoConverter<Product, ProductDto> {
    @Override
    public Product convertToEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .id(dto.getId())
                .build();
    }

    @Override
    public ProductDto convertToDto(Product entity) {
        return ProductDto.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .id(entity.getId())
                .build();
    }
}
