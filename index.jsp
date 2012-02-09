<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>

 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/home.js"></script>

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
			<label id="error">please enter a valid email address</label>
			<input type="submit" name="submit" id="submit_button" value=" I'm interested">
			<div id="loader"></div>
			<label class="disclaimer">*we promise not to spam you or give your email address to any third parties</label>
		</form>

		<div id="thanks">
			<h3>thanks</h3>
			<h4>we'll be in touch soon! test update</h4>
		</div>

	</section>
	
</body>
</html>