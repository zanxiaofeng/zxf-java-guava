package zxf.java.easyrule.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule(name = "My rule 1", description = "My rule 1", priority = 1)
public class MyRule1 {
    @Condition
    public boolean when(@Fact("age") Integer age) {
        System.out.println("MyRule1::when, age=" + age);
        return age > 20;
    }

    @Action(order = 1)
    public void execute1(Facts facts) {
        System.out.println("MyRule1::execute1, " + facts);
    }

    @Action(order = 2)
    public void execute2(Facts facts) {
        System.out.println("MyRule1::execute2, " + facts);
    }
}
