package com.jonpereiradev.jfile.reader.rule.configurator;

import com.jonpereiradev.jfile.reader.rule.RuleConfiguratorContext;
import com.jonpereiradev.jfile.reader.rule.column.MaxDoubleRule;
import com.jonpereiradev.jfile.reader.rule.column.MinDoubleRule;

final class DoubleTypeConfiguratorImpl extends AbstractRuleConfigurator<DoubleTypeConfigurator> implements DoubleTypeConfigurator {

    DoubleTypeConfiguratorImpl(int position, RuleConfiguratorContext context) {
        super(position, context);
    }

    @Override
    public DoubleTypeConfigurator min(double min) {
        return rule(position -> new MinDoubleRule(position, min));
    }

    @Override
    public DoubleTypeConfigurator max(double max) {
        return rule(position -> new MaxDoubleRule(position, max));
    }
}