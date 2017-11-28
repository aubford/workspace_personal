payload = {
 "owner_types": "aliens",
 "list_origin": "unknown",
 "pets": [{
	 "name": "razr",
	 "species": "rabid-iguana"
 }, {
	 "name": "cindi",
	 "species": "chupacabra"
 }, {
	 "name": "farkel",
	 "species": "minion"
 }],
 "store_info": "null"
}


animal = payload["pets"]

animal.forEach(function(p){
	$("table").append(
		"<tr><td>"+p["name"]+"</td><td>"+p["species"]+"</td></tr>"
	)
})
