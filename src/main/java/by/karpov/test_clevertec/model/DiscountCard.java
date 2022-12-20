package by.karpov.test_clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "discount_card")
public class DiscountCard extends BaseEntity {


    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "discount", nullable = false, precision = 10)
    private BigDecimal discount;



}