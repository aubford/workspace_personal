$(document).ready(function() {


	$('.cat').click(function() {


			
			$('.cat').text("Nooooooooo!");
			$('.cat').after($("<div id='catdiv'><img id='catimg' alt='cat' src='assets/giphy.gif'><p id='catp'>I love coding! <br> I wish I was coding <br> right meow!!</p></div>"));


		
			for(x=0; x<1000; x++){ 
			$('#catdiv').animate({left: "+=70%"}, 3000);
			$('#catdiv').animate({left: "-=70%"}, 3000);
			
		};
	
	});


});
