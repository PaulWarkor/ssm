<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/commonTag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<link href="<c:url value="/static/css/login.css" />" rel='stylesheet' type='text/css' />
<script src="<c:url value="/static/js/jquery-1.11.1.js" />"></script>
</head>
<body>
	<script>
		$(document).ready(function(c) {
			$('#btn_login').click(function(){
			     var username = $('input[name=username]').val();
		   		 var pwd = $('input[name=password]').val();
		   		 $.ajax({
		   		        url: "${ctx}/doLogin",
		   		        data: { 'userName' : username,'userPassword':pwd },
		   		        type: "post",
		   		        success: function (result) {
		   		        	if(result == 'SUCCESS'){    		        		
		   		            	location.href = '${ctx}/main';
		   		        	}else{
		   		        		alert(result);
		   		        	}
		   		        },
		   		        error: function (jqXHR, textStatus, errorThrown) {
		   		            alert(jqXHR.responseText);
		   		        }
		   		    });
			 });
		});
	</script>
	<form action="${ctx}/login" method="post">
		<!--SIGN UP-->
		<h1>用户登录</h1>
		<div class="login-form">
			<div class="avtar">
				<img src="/static/img/login/avtar.png" />
			</div>
			<form>
				<input type="text" class="text" value="username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'username';}"> 
				<input type="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}">
	
			</form>
			<div class="signin">
				<input type="submit" value="Login" id="btn_login">
			</div>
		</div>
	</form>
</body>
</html>