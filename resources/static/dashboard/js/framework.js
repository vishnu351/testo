
$(document).ready(function(){
	
	//contrast
	if(getCookie('contrast') == 0 || getCookie('contrast') == null){
	$(".light").hide();
	$(".dark").show();
    }else{
	$(".light").show();
	$(".dark").hide();
}


// Fix Header

	var num = 36; //number of pixels before modifying styles
    $(window).bind('scroll', function () {
        if ($(window).scrollTop() > num) {
        $('.fixed-wrapper').addClass('sticky');
		
    
        } else {
        $('.fixed-wrapper').removeClass('sticky');
    
        }
    });

			
$(function () {

    $(".menu li").on('mouseenter mouseleave', function (e) {
        if ($('ul', this).length) {
            var elm = $('ul:first', this);
            var off = elm.offset();
            var l = off.left;
            var w = elm.width();
            var docH = $(".container").height();
            var docW = $(".container").width();

            var isEntirelyVisible = (l + w <= docW);

            if (!isEntirelyVisible) {
                $(this).addClass('edge');
            } else {
                $(this).removeClass('edge');
            }
        }
    });
});		
			
		
	
// Mobile Nav	
$('.sub-menu').append('<i class="fa fa-caret-right"></i>');
	$('.toggle-nav-bar').click(function(){	
	$('#nav').slideToggle();
	$('#nav li').removeClass('open');
    $("#nav li").click(function(){
		$("#nav li").removeClass('open');
		$(this).addClass('open');
	}); 
		
	});

	
//Skip Content
$('a[href^="#skipCont"]').click(function() {
$('html,body').animate({ scrollTop: $(this.hash).offset().top}, 500);
return false;
e.preventDefault();

});

// Toggle Search



    $("#toggleSearch").click(function(e) {
        $(".search-drop").toggle();
        e.stopPropagation();
    });

    $(document).click(function(e) {
        if (!$(e.target).is('.search-drop, .search-drop *')) {
            $(".search-drop").hide();
        }
    });

})

//Drop down menu for Keyboard accessing
function dropdown1(dropdownId, hoverClass, mouseOffDelay) { 
	if(dropdown = document.getElementById(dropdownId)) {
		var listItems = dropdown.getElementsByTagName('li');
		for(var i = 0; i < listItems.length; i++) {
			listItems[i].onmouseover = function() { this.className = addClass(this); }
			listItems[i].onmouseout = function() {
				var that = this;
				setTimeout(function() { that.className = removeClass(that); }, mouseOffDelay);
				this.className = that.className;
			}
			var anchor = listItems[i].getElementsByTagName('a');
			anchor = anchor[0];
			anchor.onfocus = function() { tabOn(this.parentNode); }
			anchor.onblur = function() { tabOff(this.parentNode); }
		}
	}
	
	function tabOn(li) {
		if(li.nodeName == 'LI') {
			li.className = addClass(li);
			tabOn(li.parentNode.parentNode);
		}
	}
	
	function tabOff(li) {
		if(li.nodeName == 'LI') {
			li.className = removeClass(li);
			tabOff(li.parentNode.parentNode);
		}
	}
	
	function addClass(li) { return li.className + ' ' + hoverClass; }
	function removeClass(li) { return li.className.replace(hoverClass, ""); }
}

jQuery(document).ready(function(){
	dropdown1('header-nav','hover',10);
	checkWidth();
});

var $window = $(window);
function checkWidth() {
   var windowsize = $window.width();

   if (windowsize < 940) {
      $("#nav .main-menu ul > li a").on("click", function(e) {
		  e.preventDefault() ;
		  $("#nav .main-menu ul > li .sub-nav").stop().slideUp('slow');
		  $(this).parent().find('.sub-nav').stop().slideToggle('slow');
      });
   } else if (windowsize > 940) { 
    function dropdown2(dropdownId, hoverClass, mouseOffDelay) { 
	if(dropdown = document.getElementById(dropdownId)) {
		var listItems = dropdown.getElementsByTagName('li');
		for(var i = 0; i < listItems.length; i++) {
			listItems[i].onmouseover = function() { this.className = addClass(this); }
			listItems[i].onmouseout = function() {
				var that = this;
				setTimeout(function() { that.className = removeClass(that); }, mouseOffDelay);
				this.className = that.className;
			}
			var anchor = listItems[i].getElementsByTagName('a');
			anchor = anchor[0];
			anchor.onfocus = function() { tabOn(this.parentNode); }
			anchor.onblur = function() { tabOff(this.parentNode); }
		}
	}
	
	function tabOn(li) {
		if(li.nodeName == 'LI') {
			li.className = addClass(li);
			tabOn(li.parentNode.parentNode);
		}
	}
	
	function tabOff(li) {
		if(li.nodeName == 'LI') {
			li.className = removeClass(li);
			tabOff(li.parentNode.parentNode);
		}
	}
	
	function addClass(li) { return li.className + ' ' + hoverClass; }
	function removeClass(li) { return li.className.replace(hoverClass, ""); }
	}
	dropdown2('main_menu','hover',10);
  }
}


// Custom scrollbar code
$(window).on("load",function(){				
	$("#content-1").mCustomScrollbar({
		theme:"minimal"
			});
			});



















