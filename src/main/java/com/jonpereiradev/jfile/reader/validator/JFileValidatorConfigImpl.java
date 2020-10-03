package com.jonpereiradev.jfile.reader.validator;

import com.jonpereiradev.jfile.reader.JFilePatternConfig;
import com.jonpereiradev.jfile.reader.validator.rule.RuleRoot;
import com.jonpereiradev.jfile.reader.validator.rule.RuleRootImpl;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.ColumnRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.FileRuleConfigurator;
import com.jonpereiradev.jfile.reader.validator.rule.configurator.LineRuleConfigurator;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

final class JFileValidatorConfigImpl implements JFileValidatorConfig {

    private final RuleRoot ruleRoot;
    private final JFileRuleConfig ruleConfig;

    private DateFormat dateFormat;
    private DateTimeFormatter localDateFormatter;
    private DateTimeFormatter localDateTimeFormatter;
    private DecimalFormat bigDecimalFormatter;
    private int maxViolationSize = -1;

    JFileValidatorConfigImpl() {
        this.dateFormat = DateFormat.getInstance();
        this.localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.bigDecimalFormatter = new DecimalFormat();
        this.bigDecimalFormatter.setParseBigDecimal(true);
        this.ruleRoot = new RuleRootImpl();
        this.ruleConfig = new JFileRuleConfigImpl(this);
    }

    JFileValidatorConfigImpl(JFilePatternConfig filePatternConfig) {
        this.dateFormat = filePatternConfig.getDateFormat();
        this.localDateFormatter = filePatternConfig.getLocalDateFormatter();
        this.localDateTimeFormatter = filePatternConfig.getLocalDateTimeFormatter();
        this.bigDecimalFormatter = filePatternConfig.getBigDecimalFormatter();
        this.ruleRoot = new RuleRootImpl();
        this.ruleConfig = new JFileRuleConfigImpl(this);
    }

    @Override
    public JFileValidatorConfig maxViolationSize(int maxViolationSize) {
        this.maxViolationSize = maxViolationSize;
        return this;
    }

    @Override
    public int getMaxViolationSize() {
        return maxViolationSize;
    }

    @Override
    public RuleRoot getRuleRoot() {
        return ruleRoot;
    }

    @Override
    public FileRuleConfigurator files() {
        return ruleConfig.files();
    }

    @Override
    public LineRuleConfigurator lines() {
        return ruleConfig.lines();
    }

    @Override
    public ColumnRuleConfigurator columns() {
        return ruleConfig.columns();
    }

    @Override
    public JFileValidatorConfig dateFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @Override
    public JFileValidatorConfig localDateFormatter(DateTimeFormatter localDateFormatter) {
        this.localDateFormatter = localDateFormatter;
        return this;
    }

    @Override
    public DateTimeFormatter getLocalDateFormatter() {
        return localDateFormatter;
    }

    @Override
    public JFileValidatorConfig localDateTimeFormatter(DateTimeFormatter localDateTimeFormatter) {
        this.localDateTimeFormatter = localDateTimeFormatter;
        return this;
    }

    @Override
    public DateTimeFormatter getLocalDateTimeFormatter() {
        return localDateTimeFormatter;
    }

    @Override
    public JFileValidatorConfig bigDecimalFormat(DecimalFormat decimalFormat) {
        this.bigDecimalFormatter = decimalFormat;
        return this;
    }

    @Override
    public DecimalFormat getBigDecimalFormatter() {
        return bigDecimalFormatter;
    }

}
