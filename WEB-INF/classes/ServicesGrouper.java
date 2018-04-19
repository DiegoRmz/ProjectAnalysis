/**
 * ServicesGrouper
 */

import java.util.Vector;

import javax.naming.spi.ResolveResult;

import java.lang.Double;
import java.lang.Integer;
import java.math.*;

import org.json.JSONObject;
import org.json.JSONArray;

import model.*;

public class ServicesGrouper {
    public ServicesGrouper(){

    }

    //https://stackoverflow.com/questions/32522147/how-to-parse-json-structured-json-array-object-in-java?rq=1
    private Vector<CashFlow> getJSONCashFlows(String jsonParseable){
        JSONArray JSarray = new JSONArray(jsonParseable);

        Vector<CashFlow> vect = new Vector<CashFlow>(JSarray.length());

        for (int i = 0; i < JSarray.length(); i++) {
            JSONObject curr = (JSONObject)JSarray.get(i);

            CashFlow cFlow = new CashFlow();

            cFlow.setPeriod(curr.getInt("period"));
            cFlow.setInflow(Double.parseDouble(curr.getString("inflow")));
            cFlow.setOutflow(Double.parseDouble(curr.getString("outflow")));
            cFlow.setAccruedForMoment(0);
            cFlow.setInterest(Double.parseDouble(curr.getString("interest")));
            cFlow.setTaxRate(Double.parseDouble(curr.getString("taxrate")));
            cFlow.setSalvageValue(Double.parseDouble(curr.getString("salvageval")));

            vect.add(i, cFlow);
        }

        return vect;
    }

    public double npv(CashFlow cashFlow){
        double cashFlowB4Taxes = (cashFlow.getInflow() - cashFlow.getOutflow());
        double upper           = cashFlowB4Taxes*(1-cashFlow.getTaxRate());
        double lower           = Math.pow((1+cashFlow.getInterest()), cashFlow.getPeriod()); // a a la b

        System.out.println(cashFlow.getTaxRate());
        System.out.println(cashFlow.getInterest());
        System.out.println(cashFlow.getInflow());
        System.out.println(cashFlow.getOutflow());
        System.out.println(upper/lower);

        return (upper/lower);
    }

    //This method accepts a string json formatted
    public String calculateNPV(String jsonParseable){
        Vector<CashFlow> cashFlows = getJSONCashFlows(jsonParseable);
        CashFlow atZero     = cashFlows.get(0);
        double   ncfAfter   = atZero.getOutflow()*(1+atZero.getTaxRate());
        String   result     = "";

        //Set previous
        cashFlows.get(0).setAccruedForMoment(ncfAfter);

        result+=("period="+cashFlows.get(0).getPeriod()+"&accrued="+ncfAfter+"|");

        for(int i = 1; i < cashFlows.size(); i++){
            if((i+1) == cashFlows.size())
                cashFlows.get(i).setAccruedForMoment(npv(cashFlows.get(i))+cashFlows.get(i).getSalvageValue());
            else
                cashFlows.get(i).setAccruedForMoment(npv(cashFlows.get(i)));
            
            result+=("period="+cashFlows.get(i).getPeriod()+"&accrued="+cashFlows.get(i).getAccruedForMoment()+"|");
        }

        return result;
    }
}