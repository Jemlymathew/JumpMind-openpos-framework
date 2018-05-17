package org.jumpmind.pos.tax.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.jumpmind.pos.persist.Column;
import org.jumpmind.pos.persist.Entity;
import org.jumpmind.pos.persist.Table;

/**
 * A rule that prescribes how a particular tax is to be applied to a group
 * Items.
 * 
 * @author elong
 * 
 */
@Table(description = "A rule that prescribes how a particular tax is to be applied to a group Items.")
public class TaxGroupRule extends Entity implements Comparable<TaxGroupRule> {

    @Column(primaryKey = true)
    private String id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private Integer compoundSequenceNumber;

    @Column()
    private Boolean taxOnGrossAmountFlag;

    @Column()
    private String calculationMethodCode;

    @Column()
    private String taxRateRuleUsageCode;

    @Column()
    private BigDecimal cycleAmount;

    private TaxAuthority taxAuthority;

    private TaxableGroup taxableGroup;

    private Collection<TaxRateRule> taxRateRules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "TaxGroupRule " + taxAuthority.getId() + "-" + taxableGroup.getId() + "-" + compoundSequenceNumber;
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof TaxGroupRule) {
            TaxGroupRule taxGroupRule = (TaxGroupRule) o;
            return taxGroupRule.getTaxAuthority().equals(taxAuthority) && taxGroupRule.getTaxableGroup().equals(taxableGroup);
        }
        return false;
    }

    public int compareTo(TaxGroupRule o) {
        int compare = -1;
        if (o != null && o instanceof TaxGroupRule) {
            TaxGroupRule taxGroupRule = (TaxGroupRule) o;
            compare = taxGroupRule.getTaxAuthority().compareTo(taxAuthority);
            if (compare == 0) {
                compare = taxGroupRule.getTaxableGroup().compareTo(taxableGroup);
            }
        }
        return compare;
    }

    public void addTaxRateRule(TaxRateRule taxRateRule) {
        if (taxRateRules == null) {
            taxRateRules = new ArrayList<TaxRateRule>();
        }
        taxRateRules.add(taxRateRule);
    }

    public TaxRateRule getFirstTaxRateRule() {
        if (taxRateRules != null && taxRateRules.size() > 0) {
            return taxRateRules.iterator().next();
        }
        return null;
    }

    public TaxRateRule getLastTaxRateRule() {
        TaxRateRule rateRule = null;
        if (taxRateRules != null) {
            Iterator<TaxRateRule> iter = taxRateRules.iterator();
            while (iter.hasNext()) {
                rateRule = iter.next();
            }
        }
        return rateRule;
    }

    public BigDecimal getTaxPercent() {
        if (taxRateRules != null && taxRateRules.size() == 1) {
            TaxRateRule rateRule = taxRateRules.iterator().next();
            if (rateRule instanceof CalculateTaxRateRule) {
                CalculateTaxRateRule calcRateRule = (CalculateTaxRateRule) rateRule;
                return calcRateRule.getPercent();
            }
        }
        return null;
    }

    public boolean usesCompounding() {
        return compoundSequenceNumber != null && compoundSequenceNumber.intValue() > 0;
    }

    public String getCalculationMethodCode() {
        return calculationMethodCode;
    }

    public void setCalculationMethodCode(String calculationMethodCode) {
        this.calculationMethodCode = calculationMethodCode;
    }

    public Integer getCompoundSequenceNumber() {
        return compoundSequenceNumber;
    }

    public void setCompoundSequenceNumber(Integer compoundSequenceNumber) {
        this.compoundSequenceNumber = compoundSequenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaxableGroup getTaxableGroup() {
        return taxableGroup;
    }

    public void setTaxableGroup(TaxableGroup taxableGroup) {
        this.taxableGroup = taxableGroup;
    }

    public TaxAuthority getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(TaxAuthority taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public Boolean getTaxOnGrossAmountFlag() {
        return taxOnGrossAmountFlag;
    }

    public void setTaxOnGrossAmountFlag(Boolean taxOnGrossAmountFlag) {
        this.taxOnGrossAmountFlag = taxOnGrossAmountFlag;
    }

    public Collection<TaxRateRule> getTaxRateRules() {
        return taxRateRules;
    }

    public void setTaxRateRules(Collection<TaxRateRule> taxRateRules) {
        this.taxRateRules = taxRateRules;
    }

    public String getTaxRateRuleUsageCode() {
        return taxRateRuleUsageCode;
    }

    public void setTaxRateRuleUsageCode(String taxRateRuleUsageCode) {
        this.taxRateRuleUsageCode = taxRateRuleUsageCode;
    }

    public BigDecimal getCycleAmount() {
        return cycleAmount;
    }

    public void setCycleAmount(BigDecimal centsPerCycle) {
        this.cycleAmount = centsPerCycle;
    }

}
