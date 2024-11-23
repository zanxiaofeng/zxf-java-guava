package zxf.java.snakeyaml.model;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String orderNo;
    private String date;
    private Customer customer;
    private List<OrderLine> orderLines;
}