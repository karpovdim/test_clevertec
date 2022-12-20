package by.karpov.test_clevertec.repositories;

import by.karpov.test_clevertec.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {
   Optional< DiscountCard> findByCardNumber(String cardNumber);
}