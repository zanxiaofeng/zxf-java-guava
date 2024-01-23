package zxf.java.generic.itrface;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanA;

public class TransformToA implements ITransform<SourceBean, TargetBeanA> {
    @Override
    public void transformInternal(SourceBean source, TargetBeanA target) {
        target.setValue(String.valueOf(source.getValue() + source.getValue()));
    }
}
