package zxf.java.generic;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;
import zxf.java.generic.beans.TargetBeanB;

public class GenericTests {
    public static void main(String[] args) throws Exception {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setValue(100);

        TransformToA transformToA = new TransformToA();
        TargetBeanA targetBeanA = transformToA.transform(sourceBean);
        System.out.println(targetBeanA.getValue());

        TransformToB transformToB = new TransformToB();
        TargetBeanB targetBeanB = transformToB.transform(sourceBean);
        System.out.println(targetBeanB.getValue());
    }
}
