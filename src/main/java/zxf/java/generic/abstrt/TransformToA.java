package zxf.java.generic.abstrt;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;

public class TransformToA extends ATransform<SourceBean, TargetBeanA> {
    @Override
    public void transformInternal(SourceBean source, TargetBeanA target) {
        target.setValue(String.valueOf(source.getValue() + source.getValue()));
    }
}
