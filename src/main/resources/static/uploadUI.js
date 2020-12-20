
var selectBox;
var formWrapper
window.onload = function(){
	 selectBox = document.getElementById('recordType');
	 formWrapper = document.getElementById('formWrapper');

};

function change(){
		switch(selectBox.value){
			case "user": 
				formWrapper.innerHTML = "	<form id='form' action ='/addUser' method='POST'><input name='username'> Username </input><input name = 'password'> Password </input> <br> <input type='submit' value='submit'></input></form>"
				break;
			
			case "album":
				formWrapper.innerHTML = "	<form id='form' action ='/addAlbum' method='POST'><input name='singer'> Singer</input><input name = 'album'>Album </input> <input name = 'year'> Year</input><input name = 'company'>Company </input> <br> <input type='submit'></input></form>"
				break;
			
			case "singer":
				formWrapper.innerHTML = "	<form id='form' action ='/addSinger' method='POST'><input name='name'> Singer</input><input name = 'sex'>Sex </input> <input name = 'dob'> DoB</input><input name = 'company'>Company </input> <br> <input type='submit'></input></form>"
		}
	}