package zxf.java.commons.lang3;

import org.apache.commons.lang3.RandomStringUtils;

public class Tests {
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.random(12, true, false));
        System.out.println(RandomStringUtils.random(12, true, true));
    }
}
