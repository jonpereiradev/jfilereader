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
package com.jonpereiradev.jfile.reader.validator.rule.column;


import com.jonpereiradev.jfile.reader.validator.rule.RuleViolation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateRuleTest extends AbstractColumnRuleTest {

    @Test
    public void mustViolateTypeRule() throws IOException {
        Path path = createFileWithContent("a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateTypeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateNotNullRule() throws IOException {
        Path path = createFileWithContent("a||c");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(2).dateType(dateFormat).notNull();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(NotNullRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).future();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateFutureOrPresentRule() throws IOException {
        Path path = createFileWithContent("19/12/1991");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).futureOrPresent();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateFutureOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).past();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolatePastOrPresentRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).pastOrPresent();
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DatePastOrPresentRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(-1);
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).after(new Date());
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateAfterOtherColumnRule() throws IOException {
        String dataInicio = getDateWithDayCalculated(1);
        String dataFim = getDateWithDayCalculated(-1);
        Path path = createFileWithContent(dataInicio + "|" + dataFim);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(2).dateType(dateFormat).after(1);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateAfterRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeRule() throws IOException {
        String dataAtual = getDateWithDayCalculated(1);
        Path path = createFileWithContent(dataAtual);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).before(new Date());
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateBeforeRule.class.getName(), violations.get(0).getRule());
    }

    @Test
    public void mustViolateBeforeOtherColumnRule() throws IOException {
        String dataInicio = getDateWithDayCalculated(1);
        String dataFim = getDateWithDayCalculated(-1);
        Path path = createFileWithContent(dataInicio + "|" + dataFim);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        getRuleConfigurator().columns().column(1).dateType(dateFormat).before(2);
        List<RuleViolation> violations = validate(path);

        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(DateBeforeRule.class.getName(), violations.get(0).getRule());
    }

    private String getDateWithDayCalculated(int amount) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, amount);
        Locale locale = new Locale("pt", "BR");
        return new SimpleDateFormat("dd/MM/yyyy", locale).format(instance.getTime());
    }

}
