$(document).ready(function() {

	$('.nav').find('.1st').click(function(){

		$('.main').children().remove();
		$('.main').prepend($("<img alt='richmond' src='assets/monument-stuart2.jpg'>"));
		$('.main').append($("<p>I'm from Richmond, VA.  It's an ok town, but a bit too quiet and conservative for my tastes.  Five, years ago, I decided to move to Denver, which I like a lot better.  There's much more to do and people are generally more laid back.  I don't plan on moving back to the east coast any time soon, unless I'm going to NYC, however I could see myself in Northern California in a decade or so.</p>"));
		
	});



	$('.nav').find('.2nd').click(function(){

		$('.main').children().remove();
		$('.main').prepend($("<img alt='theogen' src='assets/13.jpg'>"));
		$('.main').append($("<p>I have a band called <a href='http://www.theogenmusic.com'>Theogen</a>.  We are trying to take electronic music into more organic and improvisational territory.  I created a music-creation system of controllers and software which my guitarist, Lucas, dubbed “The Determinator” to be able to produce music live and improvise with people that play regular instruments.  We perform around Colorado pretty regularly, though we are taking a short break while both myself and Lucas go to our respective web development schools.  The third member of the band, Nyk, is already a professional coder himself.  We may work together when our studies are finished.</p>"));

	});



	$('.nav').find('.3rd').click(function(){

		$('.main').children().remove();
		$('.main').prepend($("<img alt='needles' src='assets/needles.jpg'>"));
		$('.main').append($("<p>Besides music, I spend as much time as possible in the mountains backpacking.  I try to take at least 10 trips a summer.  My favorite trip so far has been to the needles are in Canyonlands National Park near Moab.  It is the most outlandish place I have ever seen.  The picture on the right is a good example of how incredible that place is.  If I’m not in the woods, I like to go to hang out with friends, go to shows, eat good food, and drink good beer.  I’m also a big fan of yoga, and, despite not having been too involved lately, I plan to get a really serious practice going soon to compliment my web development career.</p>"));

	});



	$('.nav').find('.last').click(function(){

		$('.main').children().remove();
		$('.main').prepend($("<img alt='Miami' src='assets/past.jpeg'>"));
		$('.main').append($("<p>I went to the University of Miami where I studied music and anthropology.  I graduated in 2008 and spent a year traveling abroad.  I lived and worked under a student visa in England with my British friend Marcus from Miami.  With the super-valuable British Pounds I saved up, I took a 2 month train trip around Europe and then went to Vietnam where my brother was studying abroad and stayed there for another 2 months.  When I got back to the states, I stayed with my dad for a few months to save up money and then moved out here to Colorado.  Since I’ve been here, I’ve made a living as a pedicab driver.  It was a really fun job for a while, but now I'm totally sick of it, so I sold my cab and enrolled in Galvanize!</p>"));

	});



});