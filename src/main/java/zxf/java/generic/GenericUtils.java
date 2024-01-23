package zxf.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {
    public static Type[] getSuperclassTypeParameters(Class<?> subclass) {
        // for use extends
        Type superType = subclass.getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superType;
            return parameterizedType.getActualTypeArguments();
        }
        throw new RuntimeException("The class has not a generic parent class");
    }

    public static Type[] getSuperinterfaceTypeParameters(Class<?> subclass, Class interfaceClass) {
        // for use implements
        Type[] superInterfaces = subclass.getGenericInterfaces();
        for (Type interfaceType : superInterfaces) {
            ParameterizedType parameterizedType = (ParameterizedType) interfaceType;
            if (parameterizedType.getRawType() == interfaceClass) {
                return parameterizedType.getActualTypeArguments();
            }
        }
        return new Type[]{};
    }


    public static void printSuperInfo(Class<?> subclass) {
        System.out.println("Super Info of " + subclass);
        Type superType = subclass.getGenericSuperclass();
        System.out.println("Super class " + superType + ", type: " + superType.getClass());
        Type[] superInterfaces = subclass.getGenericInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
            System.out.println("Super interface[" + i + "] " + superInterfaces[i] + ", type: " + superInterfaces[i].getClass());
        }
    }
}
