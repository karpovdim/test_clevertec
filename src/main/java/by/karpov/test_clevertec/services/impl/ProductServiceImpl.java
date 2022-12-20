package by.karpov.test_clevertec.services.impl;

import by.karpov.test_clevertec.dto.ProductDto;
import by.karpov.test_clevertec.dto.converter.impl.ProductDtoConverter;
import by.karpov.test_clevertec.model.Product;
import by.karpov.test_clevertec.repositories.ProductRepository;
import by.karpov.test_clevertec.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProductServiceImpl implements ProductService {
    @Qualifier("productRepository")
    private final ProductRepository productRepository;
    @Qualifier("productDtoConverter")
    private final ProductDtoConverter productDtoConverter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductDtoConverter productDtoConverter) {
        this.productRepository = productRepository;
        this.productDtoConverter = productDtoConverter;
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).get();
        return productDtoConverter.convertToDto(product);
    }




}
