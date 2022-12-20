package by.karpov.test_clevertec.repositories;

import by.karpov.test_clevertec.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}