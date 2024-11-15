package zxf.java.snakeyaml;

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
import java.util.LinkedHashMap;
import java.util.Map;

public class SnakeyamlTests {
    public static void main(String[] args) throws IOException {
        testReadYaml();
        testWriteYaml();
    }

    private static void testReadYaml() throws IOException {
        @Cleanup InputStream inputStream1 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-1.yml");
        Map<String, Object> map = new Yaml().load(inputStream1);
        System.out.println("Read Map: " + map);

        Yaml yaml = new Yaml(new Constructor(Order.class));
        @Cleanup InputStream inputStream2 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-1.yml");
        Order order1 = yaml.load(inputStream2);
        System.out.println("Read Customer Type - without type: " + order1);

        @Cleanup InputStream inputStream3 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-2.yml");
        Order order2 = new Yaml().load(inputStream3);
        System.out.println("Read Customer Type - with type: " + order2);

        @Cleanup InputStream inputStream4 = SnakeyamlTests.class.getResourceAsStream("/yaml/example-3.yml");
        Iterator<Object> orders = new Yaml().loadAll(inputStream4).iterator();
        System.out.println("Read Customer Type - multiple: ");
        while (orders.hasNext()) {
            System.out.println(orders.next());
        }
    }

    private static void testWriteYaml() throws IOException {
        Map<String, Object> data = createMap();
        System.out.println("\nWrite Map: \n" + new Yaml().dump(data));

        Order order = createOrder();
        System.out.println("Write Customer Type: \n" + new Yaml().dump(order));
        System.out.println("Write Customer Type: as map \n" + new Yaml().dumpAs(order, Tag.MAP, null));
    }


    private static Map<String, Object> createMap() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[]{"ONE_HAND", "ONE_EYE"});
        return data;
    }

    private static Order createOrder() {
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
        return order;
    }
}
