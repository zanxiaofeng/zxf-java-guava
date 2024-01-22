package zxf.java.generic;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanB;

public class TransformToB implements ITransform<SourceBean, TargetBeanB> {
    @Override
    public void transformInternal(SourceBean source, TargetBeanB target) {
        target.setValue(source.getValue() * source.getValue());
    }
}
