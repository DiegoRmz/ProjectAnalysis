function clickDep(){
    var dataArray = [];

        var limit = $("#npvPeriods").val();

        for (let index = 0; index <= limit; index++) {
            obj = {};
            
            obj.period     =$("#depPeriods").val();
            obj.taxrate=$("#depTax").val();
            obj.salvageval=$("#depSalvage").val();
            obj.principal = $("#depPrincipal").val();
    
            Object.keys(obj).forEach(function (key) {
                if(obj[key] === "" || !obj[key])
                    obj[key] = "0";
            })
    
            dataArray.push(obj);
        }
    
        console.log(dataArray);
    
        var urlData = {action:"dep", "data":JSON.stringify(dataArray)};
        var uri  = "../Proyecto_Final/svcs";
    
        var call = new Comms(uri);
    
        call.communicateBackend('text','POST',urlData,handleResponseNPV);
    
}

function handleResponseDep(response){
    response = response.substr(0, response.length -1);
    response = response.split("|");

    var result = [];

    response.forEach(element => {
        result.push(parseURLFormat(element));
    });

    console.log(result);

    result.forEach(element => {
        $("#cumulativeDep"+element.period).val(parseFloat(element.depXperiod).formatMoney(2));
    });
}