<!--
		
		$(document).ready(function(){
			$("#submit_img").click(function() {
				var hasError = false;
				var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
				var emailAddress = $("#email").val();
				
				if(emailAddress == "" || emailAddress == "your email") {
					hasError = true;
				} else if(!emailReg.test(emailAddress)) {
					hasError = true;
				}
				
				if(hasError) {
					$("#error").show().fadeOut(3000);
					return false;
				} else {
					var dataString = "email=" + $("#email").val() ;
					dataString = dataString+"&refId="+ $("#refId").val();
					$("#sendbox").hide().fadeOut(2000,function(){
						$("#loader").show().fadeIn(2000);	
					});
					
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
							var jsonObj = eval('('+msg+')');
				//			alert(jsonObj.result);
				//			alert(jsonObj.refId);
							if(jsonObj.result=="success") {
									$("#loader").fadeOut(1000);
									$("#thanks").fadeIn(1000);
									//alert('www.yumtum.in/?refId'+jsonObj.refId);
									$("#url").append('<a href="http://www.yumtum.in/?refId='+jsonObj.refId+'">www.yumtum.in/?refId='+jsonObj.refId+'</a>');
								
							} else if (jsonObj.result=="exists"){
								//alert(jsonObj.result);
								//alert(jsonObj.refId);
									$("#loader").fadeOut(1000);
									$("#thanks_ex").fadeIn(1000);
									//alert('www.yumtum.in/?refId'+jsonObj.refId);
									$("#url_ex").append('<a href="http://www.yumtum.in/?refId='+jsonObj.refId+'">www.yumtum.in/?refId='+jsonObj.refId+'</a>');
								}else {
					//			alert(msg);
								window.location.reload()
							}
						},
						error: function() {
							//alert("sorry, there seems to be a problem\nplease try again later");
							$("form").fadeOut(1000, function() {
								$("#loader").fadeOut(1000);
								$("#thanks_error").fadeIn(1000);
								//alert('www.yumtum.in/?refId'+jsonObj.refId);
								
							});
						}
					});
					return false;
				}
			});
		
		});

//-->