package zxf.java.generic.itrface;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;
import zxf.java.generic.beans.TargetBeanB;

import java.lang.reflect.Type;
import java.util.Map;

import static zxf.java.generic.GenericUtils.getSuperinterfaceTypeParameters;

public class TestGenericInterface {
    public static void main(String[] args) throws Exception {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setValue(100);

        TransformToA transformToA = new TransformToA();
        Type[] typesOfTransformToA = getSuperinterfaceTypeParameters(transformToA.getClass(), ITransform.class);
        System.out.println("Type Parameter for TransformToA: " + typesOfTransformToA[0] + ", " + typesOfTransformToA[1]);
        TargetBeanA targetBeanA = transformToA.transform(sourceBean);
        System.out.println(targetBeanA.getValue());

        TransformToB transformToB = new TransformToB();
        Type[] typesOfTransformToB = getSuperinterfaceTypeParameters(transformToB.getClass(), ITransform.class);
        System.out.println("Type Parameter for TransformToB: " + typesOfTransformToB[0] + ", " + typesOfTransformToB[1]);
        TargetBeanB targetBeanB = transformToB.transform(sourceBean);
        System.out.println(targetBeanB.getValue());

        ITransform myTransform = new ITransform<SourceBean, Map<String, String>>() {
            @Override
            public void transformInternal(SourceBean source, Map<String, String> target) {

            }
        };
        Type[] typesOfMyTransform = getSuperinterfaceTypeParameters(myTransform.getClass(), ITransform.class);
        System.out.println("Type Parameter for MyTransform: " + typesOfMyTransform[0] + ", " + typesOfMyTransform[1]);
    }
}
