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
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateAfterRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateBeforeRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateFutureOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDateFutureRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDatePastOrPresentRule;
import com.jonpereiradev.jfile.reader.validator.rule.column.LocalDatePastRule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


final class LocalDateTypeConfiguratorImpl
    extends AbstractRuleConfigurator<LocalDateTypeConfigurator> implements LocalDateTypeConfigurator {

    private final DateTimeFormatter dateTimeFormatter;

    LocalDateTypeConfiguratorImpl(
        int columnNumber,
        DateTimeFormatter dateTimeFormatter,
        JFileValidatorConfig configuration,
        RuleNode<ColumnRule> ruleNode
    ) {
        super(columnNumber, configuration, ruleNode);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public LocalDateTypeConfigurator future() {
        return rule(columnNumber -> new LocalDateFutureRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator futureOrPresent() {
        return rule(columnNumber -> new LocalDateFutureOrPresentRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator past() {
        return rule(columnNumber -> new LocalDatePastRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator pastOrPresent() {
        return rule(columnNumber -> new LocalDatePastOrPresentRule(columnNumber, dateTimeFormatter));
    }

    @Override
    public LocalDateTypeConfigurator after(LocalDate min) {
        return rule(columnNumber -> new LocalDateAfterRule(columnNumber, dateTimeFormatter, min));
    }

    @Override
    public LocalDateTypeConfigurator after(int columnNumber) {
        return rule(number -> new LocalDateAfterRule(number, dateTimeFormatter, columnNumber));
    }

    @Override
    public LocalDateTypeConfigurator before(LocalDate max) {
        return rule(columnNumber -> new LocalDateBeforeRule(columnNumber, dateTimeFormatter, max));
    }

    @Override
    public LocalDateTypeConfigurator before(int columnNumber) {
        return rule(number -> new LocalDateBeforeRule(number, dateTimeFormatter, columnNumber));
    }


}
