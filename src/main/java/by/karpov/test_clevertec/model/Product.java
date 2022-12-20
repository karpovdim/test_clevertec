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
@Table(name = "product")
public class Product extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "price", nullable = false, precision = 10)
    private BigDecimal price;

    @Column(name = "stock", nullable = false, precision = 10)
    private Boolean stock;

}