
var searchBar;
var resultsContainer;
var displayIndex =0;
var ajax;
var reverse = 1;

window.onload = () => {
	
	searchBar = document.getElementById('searchBar');
	resultsContainer = document.getElementById('resultsContainer');
}


function search(){
	displayIndex = 0;
	var Http = new XMLHttpRequest();
	const url='search?query=' + searchBar.value;
		Http.open("GET", url);
		Http.send();
		
	Http.onreadystatechange = (e) => {
		if(Http.readyState == 4){
			
			ajax =  JSON.parse(Http.response);
			
			ajax.sort((a, b) => {
				if(a.name < b.name) { return -1 * reverse; }
				if(a.name > b.name) { return 1 * reverse; }
				return 0;
			});
			
			document.getElementById('pageNumber').innerHTML =  "1 of " + Math.ceil(ajax.length / 5);
			displayResults();
		}
	}
}


function displayResults(){

	resultsContainer.innerHTML = "";
	for (var i =displayIndex; i < displayIndex + 5; i++){
		if(i > ajax.length -1 || i < 0)
			break;
		var card = createCard(ajax[i]);
		resultsContainer.appendChild(card);
	}

}

function createCard(object) {
	
	var card = document.createElement("div");
	card.classList.add("display-card");
	
	if(object['record'] == "Album"){
		card.classList.add("display-card-album");
		card.innerHTML = object['album'] +" by " + object['singer'] + " in " + object['year'] + " with " + object['company'];
	}
	else {
		card.classList.add("display-card-singer");
		card.innerHTML = object['name'] +"       DoB:  " + object['dob'] + " Sex: " + object['sex'] + " Company: " + object['company'];
	}

	return card;
}

function reverseSort(){

	reverse = reverse * -1;
	search();
}

function nextPage(){
	displayIndex += 5;
		if(displayIndex >= ajax.length)
		displayIndex -= 5;
	displayResults();
	
	document.getElementById('pageNumber').innerHTML = (displayIndex + 5)/ 5 + " of " + Math.ceil(ajax.length / 5);
}

function previousPage(){
	displayIndex -= 5;
	if(displayIndex < 0)
		displayIndex = 0;
	displayResults();
	
	document.getElementById('pageNumber').innerHTML = (displayIndex + 5)/ 5 + " of " + Math.ceil(ajax.length / 5);
}