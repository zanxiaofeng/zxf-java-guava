package zxf.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static zxf.java.generic.GenericUtils.getSuperclassTypeParameters;

public class GenericTests {
    public static void main(String[] args) throws Exception {
        Map myMap = new HashMap<String, String>(){};
        Type[] typesOfMyMap = getSuperclassTypeParameters(myMap.getClass());
        System.out.println("Type Parameter for MyMap: " + typesOfMyMap[0] + ", " + typesOfMyMap[1]);

        Set mySet = new Set() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] objects) {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection collection) {
                return false;
            }

            @Override
            public void clear() {

            }
        }


    }
}
