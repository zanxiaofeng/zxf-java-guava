package zxf.java.snakeyaml.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLine {
    private String item;
    private int quantity;
    private BigDecimal unitPrice;
}