<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="description" content="coming soon .. a Novel and Easy way to book restaurant tables online ..." />
<meta name="keywords" content="table booking online, restaurants online, restaurants india" />
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>

 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/home.js"></script>
 
 	<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<script type="text/javascript" src="js/ie.js"></script>
		<link rel="stylesheet" type="text/css" href="css/ie8.css" title="ie8" media="screen" />
	<![endif]-->

	<!--[if lt IE 8]>
		<link rel="stylesheet" type="text/css" href="css/ie.css" title="ie" media="screen" />
	<![endif]-->
 

<title>yumtum.in - Launching soon</title>

</head>
<body>
	<header>
		<a class="logo png" href="http://www.yumtum.in" title="yumtum.in"></a>

		<div id="tagline">
			<span class="bracket left">{</span>
			<p>coming soon .. a <span class="highlight">Novel and Easy</span> way to book restaurant tables online ...</p>
			<span class="bracket right">}</span>
		</div>
	</header>	
		<section>
		<form id="subscribeForm">
			<label for="email_box">*signup for private beta </label>
			<input id="email_box" type="email" name="email" value="your email" tabindex=1 required>
			<input id="refId" type="hidden" value="${param.refId}">
			<label id="error">please enter a valid email address</label>
			<input type="submit" name="submit" id="submit_button" value=" Count me in">
			<div id="loader"></div>
			<label class="disclaimer">*we promise not to spam you or give your email address to any third parties</label>
		</form>
<!-- 
		<div id="thanks">
		    <input id="email_disp" type="email" name="email" value=""/>
			<div>thanks</div>
			<h4>we'll be in touch soon!</h4>
		</div>
 -->
 		<div id="thanks">
 			<h4>Thanks !! Please share this with your friends.</h4>
 		    <input id="email_disp" type="email" name="email" value=""/>
 		<div id="url"></div>
		</div>
		<div id="thanks_ex">
 			<h4>Thanks Again !! Please share this with your friends.</h4>
 			   <div id="url_ex"></div>
		</div>
		<div id="thanks_error">
 			<h4>There seems to be a problem right now. Please drop a mail to contact@yumtum.in to register.</h4>
 		</div>
		
	</section>
	<footer>
		<div id="social_networks">
			<span>Follow us on: </span>
			<a class="twitter" href="http://twitter.com/yumtumindia" title="Twitter!" target="_blank"><img src="./img/twitter.png"/></a>
			
			<a class="facebook" href="http://www.facebook.com/yumtumindia" title="Facebook!" target="_blank"><img src="./img/facebook.png"/></a>
		</div>
	</footer>
	<a title="Web Analytics" href="http://getclicky.com/66550580" style="display: none;"><img alt="Web Analytics" src="//static.getclicky.com/media/links/badge.gif" border="0" /></a>
	<script type="text/javascript">
	var clicky_site_ids = clicky_site_ids || [];
	clicky_site_ids.push(66550580);
	(function() {
	  var s = document.createElement('script');
	  s.type = 'text/javascript';
	  s.async = true;
	  s.src = '//static.getclicky.com/js';
	  ( document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0] ).appendChild( s );
	})();
	</script>
	<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-29695817-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<div style="display: none"><img src="./img/logo.png"/></div>
</body>
</html>