package zxf.java.easyrule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import zxf.java.easyrule.rule.MyRule1;

public class EasyRuleTests {
    public static void main(String[] args) {
        Rules rules = new Rules();
        rules.register(new MyRule1());

        Facts facts = new Facts();
        facts.put("name", "Davis");
        facts.put("age", 40);

        RulesEngineParameters parameters = new RulesEngineParameters()
                .priorityThreshold(10)
                .skipOnFirstAppliedRule(true)
                .skipOnFirstFailedRule(true)
                .skipOnFirstNonTriggeredRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        rulesEngine.fire(rules, facts);
    }
}
