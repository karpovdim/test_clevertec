package by.karpov.test_clevertec.repositories;

import by.karpov.test_clevertec.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}