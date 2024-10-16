package com.sk.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "t_order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my-generator")
    @SequenceGenerator(name="my-generator", sequenceName = "test_order_line_item_seq")
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "id=" + id +
                ", skuCode='" + skuCode + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}