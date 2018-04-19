function clickNpv(){
    var dataArray = [];
    //To do: check for empty values
    var limit = $("#npvPeriods").val();

    for (let index = 0; index <= limit; index++) {
        obj = {};
        
        obj.period    = index;
        obj.inflow    = $("#inflowNPV"+index).val();
        obj.outflow   = $("#outflowNPV"+index).val();
        obj.interest  = $("#npvInterest").val();
        obj.taxrate   = $("#nvpTax").val();
        obj.salvageval= $("#nvpSalvage").val();

        Object.keys(obj).forEach(function (key) {
            if(obj[key] === "" || !obj[key])
                obj[key] = "0";
        })

        dataArray.push(obj);
    }

    console.log(dataArray);

    var urlData = {action:"npv", "data":JSON.stringify(dataArray)};
    var uri  = "../Proyecto_Final/svcs";
    
    /*$.ajax({
        url: "../Proyecto_Final/svcs",
        data: urlData,
        method: 'POST',
        dataType:'text',
        success: function (result) {
            console.log(result);

            handleResponseNPV(result);
        },
        error: function (errorP) {
            console.log(errorP);
            alert("Su petición no pudo procesarse");
        }
    });*/

    var call = new Comms(uri);

    call.communicateBackend('text','POST',urlData,handleResponseNPV);
}

function handleResponseNPV(response){
    response = response.substr(0, response.length -1);
    response = response.split("|");

    var result = [];

    response.forEach(element => {
        result.push(parseURLFormat(element));
    });

    console.log(result);

    result.forEach(element => {
        $("#cumulativeNPV"+element.period).val(parseFloat(element.accrued).formatMoney(2));
    });
}