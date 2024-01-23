package zxf.java.generic;

import java.lang.reflect.Type;
import java.util.*;

import static zxf.java.generic.GenericUtils.getSuperclassTypeParameters;

public class GenericTests {
    public static void main(String[] args) throws Exception {
        Map myMap = new HashMap<String, String>(){};
        Type[] typesOfMyMap = getSuperclassTypeParameters(myMap.getClass());
        System.out.println("Type Parameter for MyMap: " + typesOfMyMap[0] + ", " + typesOfMyMap[1]);
    }
}
