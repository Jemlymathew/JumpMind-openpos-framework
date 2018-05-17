package org.jumpmind.pos.tax.model;

import java.math.BigDecimal;

import org.jumpmind.pos.persist.Column;
import org.jumpmind.pos.persist.Entity;
import org.jumpmind.pos.persist.Table;

/**
 * A rule denoting what percentage or dollar amount of tax is applied to a
 * particular taxable total in a RetailTransaction.
 * 
 * @author elong
 * 
 */
@Table(
        description = "A rule denoting what percentage or dollar amount of tax is applied to a particular taxable total in a RetailTransaction.")
abstract public class TaxRateRule extends Entity {

    @Column(primaryKey = true)
    private String id;

    @Column()
    private Integer taxRateRuleSequenceNumber;

    @Column()
    private BigDecimal minTaxableAmount;

    @Column()
    private BigDecimal maxTaxableAmount;

    private TaxGroupRule taxGroupRule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + taxRateRuleSequenceNumber + "] " + minTaxableAmount.setScale(2, BigDecimal.ROUND_HALF_UP)
                + " to " + maxTaxableAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getMaxTaxableAmount() {
        return maxTaxableAmount;
    }

    public void setMaxTaxableAmount(BigDecimal maxTaxableAmount) {
        this.maxTaxableAmount = maxTaxableAmount;
    }

    public BigDecimal getMinTaxableAmount() {
        return minTaxableAmount;
    }

    public void setMinTaxableAmount(BigDecimal minTaxableAmount) {
        this.minTaxableAmount = minTaxableAmount;
    }

    public TaxGroupRule getTaxGroupRule() {
        return taxGroupRule;
    }

    public void setTaxGroupRule(TaxGroupRule taxGroupRule) {
        this.taxGroupRule = taxGroupRule;
    }

    public Integer getTaxRateRuleSequenceNumber() {
        return taxRateRuleSequenceNumber;
    }

    public void setTaxRateRuleSequenceNumber(Integer taxRateRuleSequenceNumber) {
        this.taxRateRuleSequenceNumber = taxRateRuleSequenceNumber;
    }

}
