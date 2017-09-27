<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/commonTag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/font-awesome.min.css" />" />
<script src="<c:url value="/static/js/jquery-1.11.1.js" />"></script>
</head>
<body style="height: 100%; width: 100%">
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="main-title text-center">
							<h2>Coming Soon</h2>
							<div class="clock w3agile">
								<div class="column days">
									<div class="timer" id="days">12</div>
									<div class="text">DAYS</div>
								</div>

								<div class="column">
									<div class="timer" id="hours">34</div>
									<div class="text">HOURS</div>
								</div>

								<div class="column">
									<div class="timer" id="minutes">56</div>
									<div class="text">MINUTES</div>
								</div>

								<div class="column">
									<div class="timer" id="seconds">78</div>
									<div class="text">SECONDS</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
