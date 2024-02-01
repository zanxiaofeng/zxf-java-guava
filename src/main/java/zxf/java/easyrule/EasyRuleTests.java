package zxf.java.easyrule;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import zxf.java.easyrule.rule.MyRule1;

public class EasyRuleTests {
    public static void main(String[] args) {
        Rules rules = new Rules();
        rules.register(new MyRule1());

        Rule myRule2Fluent = new RuleBuilder()
                .name("My rule 2")
                .description("My rule 2")
                .when(facts -> {
                    System.out.println("MyRule2::when " + facts);
                    return facts.get("name").equals("Davis");
                }).then(facts -> {
                    System.out.println("MyRule2::then " + facts);
                }).build();
        rules.register(myRule2Fluent);

        Rule mvelRule = new MVELRule()
                .name("My rule 3")
                .description("My rule 3")
                .when("age > 30")
                .then("System.out.println(\"MyRule3::then \")");
        rules.register(mvelRule);

        Facts facts = new Facts();
        facts.put("name", "Davis");
        facts.put("age", 40);

        RulesEngineParameters parameters = new RulesEngineParameters()
                .priorityThreshold(10).skipOnFirstAppliedRule(true)
                .skipOnFirstFailedRule(true)
                .skipOnFirstNonTriggeredRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
