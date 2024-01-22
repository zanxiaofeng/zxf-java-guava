package zxf.java.generic;

import com.google.common.reflect.TypeToken;

public interface ITransform<S, T> {
    void transformInternal(S source, T target);

    default T transform(S source) throws Exception {
        T target = createTarget();
        transformInternal(source, target);
        return target;
    }

    default T createTarget() throws Exception {
        TypeToken<S> sourceType = new TypeToken<S>(getClass()){};
        TypeToken<T> targetType = new TypeToken<T>(getClass()) {};
        System.out.println("Source: " + sourceType.getType());
        System.out.println("Target: " + targetType.getType());
        return (T)targetType.getRawType().newInstance();
    }
}
