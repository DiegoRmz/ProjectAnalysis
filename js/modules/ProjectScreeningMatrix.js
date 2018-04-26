var prev = 0;

var subTotals = {};
subTotals['PRIMARY'] = 0;
subTotals['SECONDARY']=0;
subTotals['OTHER']   = 0;  

var factors = {};
factors.PRIMARY  = 0.5;
factors.SECONDARY=0.35;
factors.OTHER    =0.15;

var history = {};

//Trace to access elements, where it came from
function calculateElement(idTrace,origin) {
    _calculatePElement(idTrace,origin);
}

function _calculatePElement(idTrace, origin) {
    var holder = parseFloat($("#"+idTrace+"_select").val())*parseFloat($("#"+idTrace+"_val").val());

    console.log(holder);

    if(!history[idTrace])
        history[idTrace] = 0;
    
    subTotals[origin] -= history[idTrace];
    subTotals[origin] += holder;

    history[idTrace] = holder;

    $("#"+idTrace+"_result").val(holder);

    $("#"+origin+"_TOTAL_W").html(subTotals[origin]);

    $("#"+origin+"_cons").html(subTotals[origin]*factors[origin]);  
    
    passProject();
}

function passProject(){
    var tot = 0;

    Object.keys(subTotals).forEach(function (item) {
        tot+=subTotals[item];
    });

    if(tot < 50){
        $("#resultssss").html("No pasa: "+tot);
    }else if(tot > 65){
        $("#resultssss").html("Do it: "+tot);
    }else{
        $("#resultssss").html("Maybe: "+tot);
    }
}