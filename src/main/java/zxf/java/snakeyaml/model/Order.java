package zxf.java.snakeyaml.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Order {
    private String orderNo;
    private String date;
    private Customer customer;
    private List<OrderLine> orderLines;
}