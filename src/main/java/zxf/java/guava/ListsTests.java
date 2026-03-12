package zxf.java.guava;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ListsTests {
    public static void main(String[] args) {
        // 修复废弃 API：Lists.newArrayList 已废弃，使用 JDK 21 的 List.of() 或 new ArrayList<>()
        List<String> fruits = new ArrayList<>(List.of("Apple", "Banana", "Orange", "Cherry"));
        Iterable<String> filteredFruits = Iterables.filter(fruits, fruit -> fruit.startsWith("A"));
        // 使用 Stream 将 Iterable 转换为 List
        List<String> filteredList = StreamSupport.stream(filteredFruits.spliterator(), false).toList();
        System.out.println("Filtered Fruits: " + filteredList);
    }
}
