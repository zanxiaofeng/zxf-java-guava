# Should I use rule engine
- You can build a simple rules engine yourself. All you need is to create a bunch of objects with conditions and actions, store them in a collection, and run through them to evaluate the conditions and execute the actions.

# Core classes
- org.jeasy.rules.api.Facts;
- org.jeasy.rules.api.Rules;
- org.jeasy.rules.api.RulesEngine;
- org.jeasy.rules.api.RulesEngineParameters;
- org.jeasy.rules.api.Rule
- org.jeasy.rules.api.Fact<T>
- org.jeasy.rules.core.DefaultRulesEngine;
- org.jeasy.rules.core.RuleBuilder;
- org.jeasy.rules.annotation.Rule;
- org.jeasy.rules.annotation.Condition;
- org.jeasy.rules.annotation.Fact;
- org.jeasy.rules.annotation.Action;
- org.jeasy.rules.mvel.MVELRule;