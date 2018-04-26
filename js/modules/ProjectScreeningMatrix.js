var prev = 0;

var subTotals = {};
subTotals['PRIMARY'] = 0;
subTotals['SECONDARY']=0;
subTotals['OTHER']   = 0;  

var factors = {};
factors.PRIMARY  = 0.4;
factors.SECONDARY=0.35;
factors.OTHER    =0.25;

//Trace to access elements, where it came from
function calculateElement(idTrace,origin) {
    console.log(idTrace);
    //Alter place
    var hold = parseFloat($("#"+idTrace+"_select").val())*parseFloat($("#"+idTrace+"_val").val());

    subTotals[origin]-=prev;
    subTotals[origin]+=hold;

    prev = hold;

    console.log(hold);
    console.log(subTotals[origin]);

    //Alter fields
    $("#"+origin+"_result").html(hold);

    $("#"+origin+"_TOTAL_W").html(subTotals[origin]);

    $("#"+origin+"_cons").html(subTotals[origin]*factors[origin]);
}