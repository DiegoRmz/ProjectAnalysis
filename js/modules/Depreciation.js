var depreciations = {};

depreciations["3"] = [33.33,44.45,14.81,7.41];
depreciations["5"] = [20,32,19.2,11.52,11.52,5.76];
depreciations["7"] =[14.29,24.49,17.49,12.49,8.93,8.92,8.93,4.46];
depreciations["10"]=  [10,18,14.4,11.52,9.22,7.37,6.55,6.55,6.56,6.55,3.28];
function clickDep(){
    var periods = $("#depPeriods").val();
    var principal=$("#depPrincipal").val();
    
    var category = $("#depCategory").val();
    var salvageV = $("#depSalvage").val();

    
    var units = (parseFloat(principal)-parseFloat(salvageV))/parseFloat(periods);
    var p = parseFloat(principal);
    var u = 0;

    $("#depreciation").append(
        "<tr>"+
            "<td>"+0+"</td>"+
            "<td>"+0+"</td>"+
            "<td>"+principal+"</td>"+
        "</tr>"
    );

    for (let index = 1; index <= periods; index++) {
        u+=parseFloat(units);
        p-=parseFloat(units);
        $("#depreciation").append(
            "<tr>"+
                "<td>"+index+"</td>"+
                "<td>"+u+"</td>"+
                "<td>"+p+"</td>"+
            "</tr>"
        );
    }

}

function clickDep1() {
    if(!depreciations[$("#depCategory").val()])
        alert("Categoria valida");
    else{
        var periods = $("#depPeriods").val();
        var principal=$("#depPrincipal").val();
        
        var category = $("#depCategory").val();
        var salvageV = $("#depSalvage").val();
    
        var p = 0;

        $("#depreciation").append(
            "<tr>"+
                "<td>"+0+"</td>"+
                "<td>"+0+"</td>"+
                "<td>"+0+"</td>"+
            "</tr>"
        );
    
        for (let index = 1; index <= depreciations[$("#depCategory").val()].length; index++) {
            p+=parseFloat(principal)*(depreciations[$("#depCategory").val()][index-1]/100);
            
            $("#depreciation").append(
                "<tr>"+
                    "<td>"+index+"</td>"+
                    "<td>"+parseFloat(principal)*(depreciations[$("#depCategory").val()][index-1]/100)+"</td>"+
                    "<td>"+p+"</td>"+
                "</tr>"
            );
        }   
    }
}