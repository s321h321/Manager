$(document).ready(function(){
	$("ul#menu li a").click(function(){
		$(this).next().toggle();
	});
});