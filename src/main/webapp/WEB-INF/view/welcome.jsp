<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
        response.sendRedirect("/home");
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>登录</title>
    <style>
        html, * { margin: 0; padding: 0; }
        body, html { background-color: #0351A6;height: 100%; }
        .page { height: 100%; }
        .login-bg {  background: url(./static/img/login_bg.jpg) no-repeat center center; width: 100%; min-height: 100%;}
        .login-manage { position: absolute; left: 50%; top: 50%; margin: -67px 0 0 -229px;  }
        .btn-go { border: none; width: 242px; border: 2px solid #4590d9; height: 42px; line-height: 42px; margin: 50px 0 30px 115px;  color: #4590d9;  text-align: center; border-radius: 5px;  font-size: 26px; display: inline-block;  text-decoration: none; font-family: "Microsoft YaHei", Arial, Helvetica, sans-serif; }
    </style>
</head>
<body>
<%--<a href="/login">登录</a>--%>

<div class="page">
    <div class="login-bg">
        <div class="login-manage">
            <p><img src="./static/img/login_manage.png" alt="FiMi管理平台" width="458" height="133" class="animated  zoomIn"></p>
            <a href="/login" id="login" class="btn-go animated  ">
                登录
            </a>
        </div>
    </div>
</div>

<link rel="stylesheet" href="./static/css/animate.min.css">
<style>
    .bounceIn { animation-duration: .6s; }
</style>
<script>
    !function () {
        var login = document.getElementById('login');
        setTimeout(function () {
            login.className = 'btn-go animated  bounceIn'
        }, 300)
    }()

</script>

</body>
</html>