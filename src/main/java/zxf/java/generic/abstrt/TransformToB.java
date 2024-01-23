package zxf.java.generic.abstrt;

import zxf.java.generic.beans.SourceBean;
import zxf.java.generic.beans.TargetBeanB;
import zxf.java.generic.itrface.ITransform;

public class TransformToB extends ATransform<SourceBean, TargetBeanB> {
    @Override
    public void transformInternal(SourceBean source, TargetBeanB target) {
        target.setValue(source.getValue() * source.getValue());
    }
}
