/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader.validator.rule.configurator;

import com.jonpereiradev.jfile.reader.validator.JFileValidatorConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleNode;
import com.jonpereiradev.jfile.reader.validator.rule.column.ColumnRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MaxFloatRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.MinFloatRule;

final class FloatTypeConfiguratorImpl extends AbstractRuleConfigurator<FloatTypeConfigurator> implements FloatTypeConfigurator {

    FloatTypeConfiguratorImpl(
        int columnNumber,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode) {
        super(columnNumber, configuration, ruleNode);
    }

    @Override
    public FloatTypeConfigurator min(float min) {
        return rule(columnNumber -> new MinFloatRule(columnNumber, min));
    }

    @Override
    public FloatTypeConfigurator max(float max) {
        return rule(columnNumber -> new MaxFloatRule(columnNumber, max));
    }

}
