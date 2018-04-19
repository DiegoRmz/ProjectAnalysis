/**
 * ProjectData
 */
package model;

public class CashFlow {
    public String projectName;
    public String procedure;        //NPV or the other one

    //Cashflow data
    public int period;
    public double inflow;
    public double outflow;
    public double accruedForMoment;  
    
    public double interest;
    public double taxRate;
    public double salvageValue;

    public CashFlow(){
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    public void setInflow(double inflow) {
        this.inflow = inflow;
    }
    public void setOutflow(double outflow) {
        this.outflow = outflow;
    }
    public void setAccruedForMoment(double accruedForMoment) {
        this.accruedForMoment = accruedForMoment;
    }

    public void setInterest(double interes){
        this.interest = interes/100;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate/100;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public int getPeriod() {
        return this.period;
    }
    public double getInflow() {
        return this.inflow;
    }
    public double getOutflow() {
        return this.outflow;
    }
    public double getAccruedForMoment() {
        return this.accruedForMoment;
    }

    public double getInterest(){
        return this.interest;
    }

    public double getTaxRate() {
        return this.taxRate;
    }

    public double getSalvageValue() {
        return this.salvageValue;
    }
    
}