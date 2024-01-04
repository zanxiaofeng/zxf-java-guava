package zxf.java.guava;

import com.google.common.collect.ImmutableList;

public class ImmutableListTests {
    public static void main(String[] args) {
        ImmutableList<String> list = ImmutableList.of("1", "2");
        System.out.println("ImmutableList: " + list);
    }
}
