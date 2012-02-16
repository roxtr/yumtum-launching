<!--
		
		$(document).ready(function(){
	
			$("#email_box").focus(function() {
				$(this).val("");
				$(this).css("color", "rgb(102,102,102)");
			});
			$("#email_box").blur(function() {
				if(this.value === "") {
					$(this).val("your email");
					$(this).css("color", "rgb(204,204,204)");
					$("#email_box").css("border-color", "rgb(153,153,153)");
				}
			});
			
			$("#submit_button").click(function() {
				var hasError = false;
				var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
				var emailAddress = $("#email_box").val();
				
				if(emailAddress == "" || emailAddress == "your email") {
					hasError = true;
				} else if(!emailReg.test(emailAddress)) {
					hasError = true;
				}
				
				if(hasError) {
					$("#error").show().fadeOut(3000);
					$("#email_box").css("border-color", "rgb(255,0,0)");
					$("#email_box").focus();
					return false;
				} else {
					var dataString = "email=" + $("#email_box").val() ;
					dataString = dataString+"&refId=";
					$("#submit_button").hide();
					$("label.disclaimer").hide();
					$("#loader").show();
					$.ajax({
						type: "POST",
						url: "./saveInfo",
						data: dataString,
						xhr: function() {
							if (window.XMLHttpRequest) {
								return new XMLHttpRequest();
							} else {
								var progIDs = ['Msxml2.XMLHTTP.3.0', 'Msxml2.XMLHTTP', 'Microsoft.XMLHTTP'];
								for (var i = 0; i < progIDs.length; i++) {
									try {
										var xmlHttp = new ActiveXObject(progIDs[i]);
										return xmlHttp;
									} catch (ex) {
									}
								}
								return null;
							}
						},
						success: function(msg) {
							if(msg=="success") {
								$("form").fadeOut(1000, function() {
									$("#thanks").fadeIn(1000);
								});
							} else {
								alert(msg);
								window.location.reload()
							}
						},
						error: function() {
							alert("sorry, there seems to be a problem\nplease try again later");
						}
					});
					return false;
				}
			});
		
		});

//-->