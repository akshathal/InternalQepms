$(document).ready(function(){
$('input.amount').each(function() {
	 var x = Number($(this).val()).toFixed(2);
            $(this).val(x).text(x);	
				
});
 


 
 /**** Start :: Code to match leftnav height with content panel ****/
 if ($("#leftNavigation").length != 0){
	 
	 var nav = document.getElementById('leftNavigation');
     var cnt = document.getElementById('contenPan');
 
        if (nav.clientHeight > 440)
        {
           if (cnt.clientHeight > 440)
            {cnt.style.minHeight=(nav.clientHeight-10) + 'px';}
        }
        else {}  
	
                                             
	}
/**** End :: Code to match leftnav height with content panel ****/

 if ($("#contenPanFull").length != 0){
	 $("#contenPanFull").css('width',(document.getElementById('header').clientWidth)-20);
 }

/*** Start :: Code for Date Picker ****/
if ($("img[src*='iccal.png']").length != 0){
	
}
/*** End :: Code for Date Picker ****/

/*** Start :: Alternate Row Color ****/
if ($(".datagrid").length != 0){
	
	$('.datagrid table').each(function() {
            $('tbody tr:even',  this).addClass('alt_odd').removeClass('alt_even');
            $('tbody tr:odd', this).addClass('alt_even').removeClass('alt_odd');
			$(".datagrid table tbody tr").hover(
			  function () {
				
				$(this).addClass("hover");
			  }, 
			  function () {
				$(this).removeClass("hover");
			  }
			);
	});
	
	var count = $('.datagrid table tbody').children().length;
	
	if (count == 0)
	{
		 $(".datagrid").css('overflow','hidden');}
	else
	{
		$(".datagrid").css('overflow','auto');
	}
	
}
if ($(".datagrid.tablebrdr").length != 0)
{
	$('.datagrid.tablebrdr').each(function() {
            $('tbody tr:even',  this).addClass('alt_odd').removeClass('alt_even');
            $('tbody tr:odd', this).addClass('alt_even').removeClass('alt_even');
			$(".datagrid.tablebrdr table tbody tr").hover(
			  function () {
			$(this).removeClass("hover");
			  }
			);
	});
	
	}
/*** End :: Alternate Row Color ****/



/*** Start :: Left Menu Selection ****/

/*** End :: Left Menu Selection ****/



});

function chkresize(){
var nav = document.getElementById('leftNavigation');
        var cnt = document.getElementById('contenPan');
 
        if (nav.clientHeight > 440)
        {
           if (cnt.clientHeight > 440)
            {
				cnt.style.minHeight=(nav.clientHeight-10) + 'px';
				}
        }
        else {} 
		
		}

/*** End :: Left Menu Toggle ***/

/*** Start :: Popup Center ***/
var targetWin = '';
function PopupCenter(pageURL, title,w,h) {
var left = (screen.width/2)-(w/2);
var top = (screen.height/2)-(h/2);
var targetWin = window.open (pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
} 


function PopupCenterCalc(pageURL,w,h) {
var left = (screen.width/2)-(w/2);
var top = (screen.height/2)-(h/2);
var targetWin = window.open (pageURL, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
} 

function toggle_leftmenu(){
               if( $("#leftNavigation").is(':visible'))
				 {
					
					$('#leftNavigation').stop().animate({width: 0}, 400, function() {$('#leftNavigation').hide();});
					$('#contenPan').stop().animate({width: '100%'}, 700);
					
					 $('#img_leftmenu').attr({
						alt: 'Open Left Menu',
						title: 'Open Left Menu'
						});
				 }
				 else if( $("#leftNavigation").is(':hidden'))
				 {
					$('#leftNavigation').show();
				    $('#leftNavigation').stop().animate({width: '15%'}, 700);
					$('#contenPan').stop().animate({width: '85%'}, 700);
					 $('#img_leftmenu').attr({
						alt: 'Close Left Menu',
						title: 'Close Left Menu'
						});
				 }
				 else {}

  
               
}

/*** End :: Popup Center ***/



/*** Start :: Main Menu Selection ***/
function selmenu(i)
{
	$("#"+i).addClass("active");
}
/*** End :: Main Menu Selection ***/


/*** Start :: Left Menu Selection ****/

/*** End :: Left Menu Selection ****/







//show hide div script


function footer_01()
{
 
document.write('<div class="fleft">Site best viewed in IE7+, Firefox 3.5+, Chrome 3+ above 1024 x 768 pixels resolution.</div>');
      
}

function paymentsec(k)
{
	if($('#chk_'+k).attr('checked')) {
			$("#paymentsection").show();
			$('#'+k).show();
		}
		else
		{
			$('#'+k).hide();
			if ($('.payoptions').filter(':checked').length == 0)
			{$("#paymentsection").hide();}
		}
}

function ui_rows()
{

 	$('.datagrid table').each(function() {
            $('tr:odd', this).addClass('alt_odd').removeClass('alt_even');
            $('tr:even', this).addClass('alt_even').removeClass('alt_odd');
			$(".datagrid table tbody tr").hover(
			  function () {
				$(this).addClass("hover");
			  }, 
			  function () {
				$(this).removeClass("hover");
			  }
			);
	});
	
	var count = $('.datagrid table tbody').children().length;
	
	if (count == 0)
	{
		 $(".datagrid").css('overflow','hidden');}
	else
	{
		$(".datagrid").css('overflow','auto');
	}
	
}