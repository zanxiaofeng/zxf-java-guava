package zxf.java.snakeyaml;

import com.google.common.collect.Iterables;
import lombok.Cleanup;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import zxf.java.snakeyaml.model.Customer;
import zxf.java.snakeyaml.model.Order;
import zxf.java.snakeyaml.model.OrderLine;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class SnakeyamlTests {
    public static void main(String[] args) throws IOException {
        testReadYaml();
        testWriteYaml();
    }

    private static void testReadYaml() throws IOException {
        @Cleanup InputStream inputStream1 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-1.yml");
        Map<String, Object> map = new Yaml().load(inputStream1);
        System.out.println("Basic: " + map);

        Yaml yaml = new Yaml(new Constructor(Order.class));
        @Cleanup InputStream inputStream2 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-1.yml");
        Order order1 = yaml.load(inputStream2);
        System.out.println("Customer without Type: " + order1);

        @Cleanup InputStream inputStream3 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-2.yml");
        Order order2 = new Yaml().load(inputStream3);
        System.out.println("Customer with Type: " + order2);

        @Cleanup InputStream inputStream4 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-3.yml");
        Iterator<Object> orders = new Yaml().loadAll(inputStream4).iterator();
        System.out.println("Load all customer with Type: ");
        while (orders.hasNext()) {
            System.out.println(orders.next());
        }
    }

    private static void testWriteYaml() throws IOException {
        Order order = new Order();
        order.setOrderNo("A001");
        order.setDate("2019-04-17");
        order.setCustomer(new Customer());
        order.getCustomer().setId("id-1");
        order.getCustomer().setName("Davis, ZAN");
        order.setOrderLines(Collections.singletonList(new OrderLine()));
        order.getOrderLines().get(0).setItem("Item 1");
        order.getOrderLines().get(0).setUnitPrice(BigDecimal.valueOf(23.34));
        order.getOrderLines().get(0).setQuantity(100);

        System.out.println("Write-1: \n" + new Yaml().dump(order));
        System.out.println("Write-2: \n" + new Yaml().dumpAs(order, Tag.MAP, null));
    }
}
