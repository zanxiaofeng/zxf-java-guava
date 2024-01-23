package zxf.java.generic.abstrt;

import com.google.common.reflect.TypeToken;

public abstract class ATransform<S, T> {
    public abstract void transformInternal(S source, T target);

    public T transform(S source) throws Exception {
        T target = createTarget();
        transformInternal(source, target);
        return target;
    }

    public T createTarget() throws Exception {
        TypeToken<S> sourceType = new TypeToken<S>(getClass()) {
        };
        TypeToken<T> targetType = new TypeToken<T>(getClass()) {
        };
        System.out.println("Source: " + sourceType.getType());
        System.out.println("Target: " + targetType.getType());
        return (T) targetType.getRawType().newInstance();
    }
}
