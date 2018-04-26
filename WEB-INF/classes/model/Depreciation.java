/**
 * Depreciation
 */
package model;

public class Depreciation {
    public int    period;

    public double macrsRate;
    public double depreciationPeriod;
    public double depreciationAccrued;
    public double valueInLedgers;
    public double taxPerYear;
    public double taxRate;
    public double principal;
    public int category;
    public double salvageValue;
    
        
    public Depreciation(){
    }

    /**
     * @return the category
     */

    /**
     * @return the principal
     */
    public double getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(double principal) {
        this.principal = principal;
    }
    
    public int getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(int category) {
        this.category = category;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }
    public void setMacrsRate(double rate){
        this.macrsRate = rate/100;
    }
    
    public void setTaxRate(double tax){
        this.taxRate = tax/100;
    }
    
    public void setDepreciationPeriod(double inflow) {
        this.depreciationPeriod = inflow;
    }
    
    public void setDepreciationAccrued(double outflow) {
        this.depreciationAccrued = outflow;
    }
    
    public void setValueInLedgers(double salvageValue) {
        this.valueInLedgers = salvageValue;
    }
    
    public void setTaxPerYear(double salvageValue) {
        this.taxPerYear = salvageValue;
    }
    
    public int getPeriod() {
        return this.period;
    }
    
    public double getMacrsRate() {
        return this.macrsRate;
    }
    
    public double getTaxRate() {
        return this.taxRate;
    }
    
    public double getDepreciationPeriod() {
        return this.depreciationPeriod;
    }
    
    public double getDepreciationAccrued() {
        return this.depreciationAccrued;
    }
    
    public double getValueInLedgers() {
        return this.valueInLedgers;
    }
    
    public double getTaxPerYear() {
        return this.taxPerYear;
    }
    
     public double getSalvageValue() {
        return this.salvageValue;
    }
    
}