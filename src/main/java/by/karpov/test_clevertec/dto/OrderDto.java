package by.karpov.test_clevertec.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
@Data

@Builder
@Validated
public class OrderDto {
    Long id;
    @Size(min = 5, max = 7, message = "message.card.invalid")
    String discountCardNumber;
    @NotNull(message = "message.ShoppingCart.data.missing")
    HashMap<Long, Integer> ShoppingCart;
    private LocalDateTime dateOfPurchase;
}
