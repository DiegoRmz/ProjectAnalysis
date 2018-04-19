function showHideDiv(ele) {
	var srcElement = document.getElementById(ele);
	if (srcElement != null) {
		if (srcElement.style.display == "block") {
			srcElement.style.display = 'none';
		}
		else {
			srcElement.style.display = 'block';
			$('html, body').animate({
			    scrollTop: $(".second").offset().top
			}, 3000);
		}
		return false;
	}

	
	
}

function showHideDiv2(ele) {
	var srcElement = document.getElementById(ele);
	if (srcElement != null) {
		if (srcElement.style.display == "block") {
			srcElement.style.display = 'none';
		}
		else {
			srcElement.style.display = 'block';
			$('html, body').animate({
			    scrollTop: $(".third").offset().top
			}, 3000);
		}
		return false;
	}
}

function showHideDiv3(ele) {
	var srcElement = document.getElementById(ele);
	if (srcElement != null) {
		if (srcElement.style.display == "block") {
			srcElement.style.display = 'none';
		}
		else {
			srcElement.style.display = 'block';
			$('html, body').animate({
			    scrollTop: $(".forth").offset().top
			}, 3000);
		}
		return false;
	}
}

function showHideDiv4(ele) {
	var srcElement = document.getElementById(ele);
	if (srcElement != null) {
		if (srcElement.style.display == "block") {
			srcElement.style.display = 'none';
		}
		else {
			srcElement.style.display = 'block';
			$('html, body').animate({
			    scrollTop: $(".btnSec-5").offset().top
			}, 3000);
		}
		return false;
	}
}

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function createTableFields(periodNo, principal, tableId, foreType) {
	console.log(periodNo);
	console.log(principal);
	console.log(tableId);
	console.log(foreType);

	for (var i = 0; i <= parseInt(periodNo); i++) {
		$("#"+tableId).append(
			"<tr>"+
				"<td>"+i+"</td>"+
				"<td>"+"<input type='text' id='inflow"+foreType+i+"'>"+"</td>"+
				"<td>"+"<input type='text' id='outflow"+foreType+i+"'>"+"</td>"+
				"<td>"+"<input type='text' id='cumulative"+foreType+i+"'>"+"</td>"+
			"</tr>"
		);		
	}

	$("#outflow"+foreType+'0').val(principal);
}

function parseURLFormat(url){
    var obj = {};
    url = url.split("&");
    
    url.forEach(element => {
        pair = element.split("=");
        obj[pair[0]] = pair[1];
    });

    return obj;
}

//Prototypes and extensions
Number.prototype.formatMoney = function(c, d, t){
	var n = this, 
		c = isNaN(c = Math.abs(c)) ? 2 : c, 
		d = d == undefined ? "." : d, 
		t = t == undefined ? "," : t, 
		s = n < 0 ? "-" : "", 
		i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), 
		j = (j = i.length) > 3 ? j % 3 : 0;
	
	return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};