var prev = 0;

var subTotals = {};
subTotals['PRIMARY'] = 0;
subTotals['SECONDARY']=0;
subTotals['OTHER']   = 0;  

var factors = {};
factors.PRIMARY  = 0.4;
factors.SECONDARY=0.35;
factors.OTHER    =0.25;

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

    $("#"+idTrace+"_result").html(holder);

    $("#"+origin+"_TOTAL_W").html(subTotals[origin]);

    $("#"+origin+"_cons").html(subTotals[origin]*factors[origin]);    
}