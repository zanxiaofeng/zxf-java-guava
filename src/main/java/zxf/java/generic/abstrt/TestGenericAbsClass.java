package zxf.java.generic.abstrt;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;
import zxf.java.generic.beans.TargetBeanB;

import java.lang.reflect.Type;

import static zxf.java.generic.GenericUtils.getSuperclassTypeParameters;

public class TestGenericAbsClass {
    public static void main(String[] args) throws Exception {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setValue(100);

        TransformToA transformToA = new TransformToA();
        Type[] typesOfTransformToA = getSuperclassTypeParameters(transformToA.getClass());
        System.out.println("Type Parameter for TransformToA: " + typesOfTransformToA[0] + ", " + typesOfTransformToA[1]);
        TargetBeanA targetBeanA = transformToA.transform(sourceBean);
        System.out.println(targetBeanA.getValue());

        TransformToB transformToB = new TransformToB();
        Type[] typesOfTransformToB = getSuperclassTypeParameters(transformToB.getClass());
        System.out.println("Type Parameter for TransformToB: " + typesOfTransformToB[0] + ", " + typesOfTransformToB[1]);
        TargetBeanB targetBeanB = transformToB.transform(sourceBean);
        System.out.println(targetBeanB.getValue());
    }


}
