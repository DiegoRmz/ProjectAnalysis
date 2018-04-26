/**
 * ProjectScreening
 */

import java.util.Vector;

import javax.naming.spi.ResolveResult;

import java.lang.Double;
import java.lang.Integer;
import java.math.*;

import org.json.JSONObject;
import org.json.JSONArray;

import model.*;

public class ProjectScreening {
    public ProjectScreening(){

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

    public double npv(CashFlow cashFlow, boolean last){
        double inflow = cashFlow.getInflow();
        if(last){
            inflow+=cashFlow.getSalvageValue();
        }

        double cashFlowB4Taxes = (inflow - cashFlow.getOutflow());
        double upper           = cashFlowB4Taxes*(1-cashFlow.getTaxRate());
        double lower           = Math.pow((1+cashFlow.getInterest()), cashFlow.getPeriod()); // a a la b

        return (upper/lower);
    }

    public double pbp(CashFlow cashFlow){
        System.out.println(cashFlow.getAccruedForMoment());
        double finalResult = cashFlow.getInflow()-cashFlow.getOutflow();

        return (cashFlow.getAccruedForMoment() + finalResult);
    }

    //This method accepts a string json formatted
    public String calculateNPV(String jsonParseable){
        Vector<CashFlow> cashFlows = getJSONCashFlows(jsonParseable);
        CashFlow atZero     = cashFlows.get(0);
        double   ncfAfter   = -atZero.getOutflow()*(1+atZero.getTaxRate());
        String   result     = "";

        //Set previous
        cashFlows.get(0).setAccruedForMoment(ncfAfter);

        result+=("period="+cashFlows.get(0).getPeriod()+"&accrued="+ncfAfter+"|");

        for(int i = 1; i < cashFlows.size(); i++){
            if((i+1) == cashFlows.size()){
                cashFlows.get(i).setAccruedForMoment(npv(cashFlows.get(i),true));
            }
            else{
                cashFlows.get(i).setAccruedForMoment(npv(cashFlows.get(i),false));
            }
                
            
            result+=("period="+cashFlows.get(i).getPeriod()+"&accrued="+cashFlows.get(i).getAccruedForMoment()+"|");
        }

        return result;
    }

    public String calculatePaybackPeriod(String jsonParseable){
        Vector<CashFlow> cashFlows = getJSONCashFlows(jsonParseable);
        CashFlow atZero         = cashFlows.get(0);
        double   initialOutflow = -atZero.getOutflow();
        String   result = "";

        result+=("period="+cashFlows.get(0).getPeriod()+"&accrued="+initialOutflow+"|");

        for(int i = 1; i < cashFlows.size(); i++){
            cashFlows.get(i).setAccruedForMoment(initialOutflow*(1+cashFlows.get(i).getInterest())); //For the calculation
            
            if((i+1) == cashFlows.size()){
                cashFlows.get(i).setAccruedForMoment(pbp(cashFlows.get(i))+cashFlows.get(i).getSalvageValue());
            }
            else{
                cashFlows.get(i).setAccruedForMoment(pbp(cashFlows.get(i)));
                System.out.println(cashFlows.get(i).getAccruedForMoment());
            }

            initialOutflow  = cashFlows.get(i).getAccruedForMoment();
            
            result+=("period="+cashFlows.get(i).getPeriod()+"&accrued="+cashFlows.get(i).getAccruedForMoment()+"|");
        }


        return result;
    }

        //Depreciation
        private Vector<Depreciation> getJSONDepreciation(String jsonParseable){
            JSONArray JSarray = new JSONArray(jsonParseable);
    
            Vector<Depreciation> vect = new Vector<Depreciation>(JSarray.length());
             
            Vector<Double> anArray = new Vector<Double>();
    
            Depreciation cur = vect.get(0);
    
             //duda -> xD
            int category = cur.getCategory();

            double Principal= cur.getPrincipal();
    
            if(category == 5)
            {
                double[] axArray = {20,32,19.2,11.52,11.52,5.76,1,1,1,1,1};
                
                for(int i = 0; i < axArray.length; i++)
                    anArray.add(i, axArray[i]);
            }
             
            if(category == 3)
            {
                double axArray[] = {33.33,44.45,14.81,7.41,1,1,1,1,1,1,1};
    
                for(int i = 0; i < axArray.length; i++)
                    anArray.add(i, axArray[i]);
            }
             
            if(category == 7)
            {
                double axArray[] = {14.29,24.49,17.49,12.49,8.93,8.92,8.93,4.46,1,1,1};
    
                for(int i = 0; i < axArray.length; i++)
                    anArray.add(i, axArray[i]);
            }
             
            if(category == 10)
            {
                double axArray[] = {10,18,14.4,11.52,9.22,7.37,6.55,6.55,6.56,6.55,3.28};
    
                for(int i = 0; i < axArray.length; i++)
                    anArray.add(i, axArray[i]);
            }
                
            for (int i = 0; i < JSarray.length(); i++) {
                JSONObject curr = (JSONObject)JSarray.get(i);
    
                Depreciation cFlow = new Depreciation();
    
                cFlow.setPeriod(curr.getInt("period"));
                cFlow.setMacrsRate(anArray.get(i));
                cFlow.setDepreciationPeriod((anArray.get(i)/100)*(Double.parseDouble(curr.getString("principal"))));
                //cFlow.setDepreciationAccrued(((anArray.get(i)/100)*(Double.parseDouble(curr.getString("principal")))+((anArray.get(i-1)/100)*(Double.parseDouble(curr.getString("principal")))));
                cFlow.setValueInLedgers((1+anArray.get(i)/100)*(Double.parseDouble(curr.getString("principal"))));
                cFlow.setTaxPerYear(0);
                cFlow.setTaxRate(Double.parseDouble(curr.getString("taxrate")));
                cFlow.setSalvageValue(Double.parseDouble(curr.getString("salvageval")));
    
                vect.add(i, cFlow);
            }
    
            return vect;
        }
        
        //This method accepts a string json formatted
        public String _getDepreciation(String jsonParseable){
            String result = "";

            Vector<Depreciation> depresed = getJSONDepreciation(jsonParseable);

            for(int i = 0; i < depresed.size(); i++){
                result+="period="+depresed.get(i).getPeriod()+"&depXperiod="+depresed.get(i).getDepreciationPeriod()+"|";
            }

            return result;
        }
}