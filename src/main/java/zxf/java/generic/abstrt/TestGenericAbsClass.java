package zxf.java.generic.abstrt;

import org.apache.commons.lang3.reflect.TypeUtils;
import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;
import zxf.java.generic.beans.TargetBeanB;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import static zxf.java.generic.GenericUtils.getSuperclassTypeParameters;
import static zxf.java.generic.GenericUtils.printSuperInfo;

public class TestGenericAbsClass {
    public static void main(String[] args) throws Exception {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setValue(100);

        TransformToA transformToA = new TransformToA();
        Type[] typesOfTransformToA = getSuperclassTypeParameters(transformToA.getClass());
        System.out.println("Type Parameter for TransformToA: " + typesOfTransformToA[0] + ", " + typesOfTransformToA[1]);
        printSuperInfo(transformToA.getClass());
        TargetBeanA targetBeanA = transformToA.transform(sourceBean);
        System.out.println(targetBeanA.getValue());

        TransformToB transformToB = new TransformToB();
        Type[] typesOfTransformToB = getSuperclassTypeParameters(transformToB.getClass());
        System.out.println("Type Parameter for TransformToB: " + typesOfTransformToB[0] + ", " + typesOfTransformToB[1]);
        printSuperInfo(transformToB.getClass());
        TargetBeanB targetBeanB = transformToB.transform(sourceBean);
        System.out.println(targetBeanB.getValue());

        ATransform myTransform = new ATransform<SourceBean, Map<String, String>>() {
            @Override
            public void transformInternal(SourceBean source, Map<String, String> target) {

            }
        };
        Type[] typesOfMyTransform = getSuperclassTypeParameters(myTransform.getClass());
        System.out.println("Type Parameter for MyTransform: " + typesOfMyTransform[0] + ", " + typesOfMyTransform[1]);
        printSuperInfo(myTransform.getClass());

        Map<TypeVariable<?>, Type> types = TypeUtils.getTypeArguments((ParameterizedType) myTransform.getClass().getGenericSuperclass());
        for (TypeVariable variable : types.keySet()) {
            System.out.println("Key: " + variable + ", Value: " + types.get(variable));
        }
    }
}
