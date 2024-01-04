package zxf.java.guava;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class ListsTests {
    public static void main(String[] args) {
        List<String> fruits = Lists.newArrayList("Apple", "Banana", "Orange", "Cherry");
        Iterable<String> filteredFruits = Iterables.filter(fruits, fruit -> fruit.startsWith("A"));
        System.out.println("Filtered Fruits: " + Lists.newArrayList(filteredFruits));
    }
}
