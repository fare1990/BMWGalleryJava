
$(document).ready(function(){
	smallToBigImage("#image1");
	changingColors();
	pause();
	BackForward();
	//timingInterval();
	/*$('a').click(function() {
	var selector = $(this).find("img").get();
	smallToBigImage(selector);		
	return false; });*/
});
			 
				
	
	
var counter =0;
var imagecounter = 1;
var paused = false;

function pause (){
	$("#bigImage").click(function (){
		if(paused)
			return;
		else
			paused = !paused;
		});
}

function smallToBigImage(selector){
	//$(".crop-img").click(function(){
			$("#bigImage").attr('src',$(selector).attr('src'));
			//});
}

function changingColors(){
	$("#hren").hover(function(){$("#hren").css('color', 'red');}, 
		function(){$("#hren").css('color', 'blue');});
}

function BackForward(){
	//$("#image"+imagecounter).click();
	smallToBigImage("#image"+imagecounter);
	$("#backward").click(function (){
			imagecounter = imagecounter - 1;
			if(imagecounter < 1){
				imagecounter = 4;
			}
			//$("#image"+imagecounter).click();
			smallToBigImage("#image"+imagecounter);
		});

		$("#forward").click(function (){
			
			imagecounter = imagecounter + 1;
			
			if(imagecounter > 4){
				imagecounter = 1;
			}
			
			//$("#image"+imagecounter).click();
			smallToBigImage("#image"+imagecounter);
		});
	
}

function timingInterval(){
	setInterval(function (){
			
			if(!paused){
				
				$("#forward").click();
			};
		}, 3000);
}
