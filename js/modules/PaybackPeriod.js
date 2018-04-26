function clickPaybackPeriod(){
    //Check taxrate is OK
    if(parseFloat($("#paybackInterest").val()) > 100)
        alert("La tasa de interés debe ir de 0 a 100%");
    else{
        var dataArray = [];
        //To do: check for empty values
        var limit = $("#paybackPeriods").val();
    
        for (let index = 0; index <= limit; index++) {
            obj = {};
            
            obj.period    = index;
            obj.inflow    = ""+$("#inflowPaybackPeriod"+index).val();
            obj.outflow   = ""+$("#outflowPaybackPeriod"+index).val();
            obj.interest  = ""+$("#paybackInterest").val();
            obj.taxrate   = 0;
            obj.salvageval= ""+$("#paybackSalvage").val();
    
            Object.keys(obj).forEach(function (key) {
                if(obj[key] === "" || !obj[key])
                    obj[key] = "0";
            })
    
            dataArray.push(obj);
        }
    
        console.log(dataArray);
    
        var urlData = {action:"pbp", "data":JSON.stringify(dataArray)};
        var uri  = "../Proyecto_Final/svcs";
    
        var call = new Comms(uri);
    
        call.communicateBackend('text','POST',urlData,handleResponsePaybackPeriod);
    }
}

function handleResponsePaybackPeriod(response){
    response = response.substr(0, response.length -1);
    response = response.split("|");

    var result = [];

    response.forEach(element => {
        result.push(parseURLFormat(element));
    });

    console.log(result);

    result.forEach(element => {
        $("#cumulativePaybackPeriod"+element.period).val(parseFloat(element.accrued).formatMoney(2));
    });
}