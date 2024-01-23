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
}
