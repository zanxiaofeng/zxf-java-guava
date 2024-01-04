package zxf.java.reflections;

import com.google.common.io.ByteSource;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

public class ReflectionsTests {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com");
        for (Class<? extends ByteSource> aClass : reflections.getSubTypesOf(ByteSource .class)) {
            System.out.println(aClass.getName());
        }
    }
}
