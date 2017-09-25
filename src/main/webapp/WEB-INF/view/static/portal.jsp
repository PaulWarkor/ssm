<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="shortcut icon" href="/favicon.ico"/>
    <title>FiMi</title>
    <style>
        html {
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%
        }

        body {
            line-height: 1.6;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif
        }

        *, ul, li, ol, dl, dd {
            margin: 0;
            padding: 0;
            list-style: none;
        }
    </style>
    <style>
        .page { overflow: hidden; position: absolute; top: 0; bottom: 0; left: 0; right: 0;}
        .header { height: 120px; background-color: #4a90e2; color: #fff; color: #fff; width: 100%; position: absolute; top: 0; }
        .footer { height: 60px; background-color: #f5f5f5; color: #f5f5f5; color: #b3b3b5; position: absolute; top: auto; bottom: 0; width: 100%; }
        .page .center, .page .content { width: 1100px; margin: 0 auto; }
        .page .content { position: absolute; top: 120px; bottom: 60px; width: 100%; overflow: auto; }
        .page .header .center { width: 762px; margin-top: 31px; }
        .header .left { float: left; border-bottom: 1px solid #559ef2; margin-top: 30px; width: 220px; }
        .page .float { font-size: 14px; height: 60px; float: left; width: 302px; padding-left: 10px; text-align: center; border: 1px solid #559ef2; border-width: 0 1px; }
        .page .float h1 { line-height: 30px; margin-bottom: 12px; font-family: "Microsoft YaHei", Arial, Helvetica, sans-serif; font-size: 30px; }
        .page span { display: inline-block; overflow: hidden; }
        .page a { color: #2e2e33; }
        .page .limit { display: inline-block; white-space: nowrap; overflow: hidden; width: 9em;  text-overflow: ellipsis; }
        .page .quit { color: #edf4fc; border-left: 1px solid #65a1e6; width: 50px; }
        .page .quit a { color: #edf4fc;  }
        .page .block { margin-top: 48px;}
        .page .block li {font-family: Microsoft YaHei, Arial, Helvetica, sans-serif; float: left; margin: 0 48px 48px 0; box-shadow: -1px 1px 3px #a09e9e; width: 330px; height: 160px; background-color: #fff; background: url(./static/img/fimi_05.png) no-repeat right 25px ; position: relative; }
        .page .block li:hover:before  { content: ''; width: 330px; height: 160px; position: absolute; left: -2px; top: -2px; border: 2px solid #4a90e2; }
        .page .block .schedule { background: url(./static/img/fimi_01.png) no-repeat right 23px ;}
        .page .block .bi { background: url(./static/img/fimi_04.png) no-repeat right 23px;}
        .page .block .material { background: url(./static/img/fimi_03.png) no-repeat right 23px ;}
        .page .block .keep-clean { background: url(./static/img/fimi_02.png) no-repeat right 25px; margin-right: 0;}
        .page .block .order-query { background: url(./static/img/fimi_06.png) no-repeat right 25px; margin-right: 0;}
        .page .cont-wrap { margin: 20px 0 0 24px;}
        .page .cont { color: #666666; font-size: 14px; line-height: 18px;}
        .cont-wrap h2 { font-size: 32px; margin-bottom: 5px; }
        .page .footer .center { width: 752px; }
        .footer .left { float: left; border-bottom: 1px solid #e3e4e6; margin-top: 30px; width: 220px;  }
        .footer-content { margin-top: 12px; float: left; color: #979799; float: left;width: 300px; border: 1px solid #d2d2d3; border-width: 0 1px; font-size: 12px; text-align: center; }

        .block a { float: left; width: 330px; margin-right:48px;}
        .block a:nth-child(3) { margin-right: 0px;}
        .block a:nth-child(6) { margin-right: 0px;}
        .block a:nth-child(9) { margin-right: 0px;}

    </style>
</head>
<body>

<%
    String domainPattern = "(\\w*\\.?){2}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";
    Pattern pattern = Pattern.compile(domainPattern, Pattern.CASE_INSENSITIVE);
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    Matcher matcher = pattern.matcher(serverName.replace(scheme + "//", "").split("/")[0]);
    matcher.find();
    String domain = matcher.group();
    request.setAttribute("domain", domain);
%>

<div class="page">
    <!--header Begin-->
    <div class="header">
        <div class="center">
            <div class="header-left left"></div>
            <div class="header-content float">
                <h1>FiMi管理平台</h1>
                <div>
                    <span>欢迎您，${sessionScope.portalUser.name} </span>
                    <span class="limit"
                          title="${sessionScope.portalUser.jobNames}">${sessionScope.portalUser.jobNames}</span>
                    <span class="quit"><a href="/logout">退出</a></span>
                </div>
            </div>
            <div class="header-left left"></div>

        </div>
    </div>
    <!--header End-->

    <!--content Begin-->
    <div class="content">
        <div class="center">
            <ul class="block">
                <a href="/fm/" target="_blank">
                    <li>
                        <div class="cont-wrap">
                            <h2>FM</h2>
                            <p class="cont"> 关联程序 </p>
                            <p class="cont"> 计划排程 </p>
                        </div>
                    </li>
                </a>
                <sec:authorize access="hasAnyRole('ROLE_LB20009,ROLE_LB10016,ROLE_LB10018,ROLE_VB10001,ROLE_VB10002,ROLE_VB10003,ROLE_VB10004,ROLE_VB10005,ROLE_VB10006')">
                    <a href="http://order.${domain}" target="_blank">
                        <li class="schedule">
                            <div class="cont-wrap">
                                <h2>排期调整</h2>
                                <p class="cont"> 计划临时有变怎么办? </p>
                                <p class="cont"> 在这里做30天内的排期微调 </p>
                            </div>
                        </li>
                    </a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_LB10017')">
                    <a href="http://report.${domain}" target="_blank">
                        <li class="keep-clean">
                            <div class="cont-wrap">
                                <h2>保洁质检</h2>
                                <p class="cont"> 保洁质检日报表 </p>
                            </div>
                        </li>
                    </a>
                </sec:authorize>
                <a href="/bi/" target="_blank">
                    <li class="bi">
                        <div class="cont-wrap">
                            <h2>运营报表</h2>
                            <p class="cont"> FM运营数据报表 (内网访问) </p>
                        </div>
                    </li>
                </a>
                <a href="/material/" target="_blank">
                    <li class="material">
                        <div class="cont-wrap">
                            <h2>啄木鸟学堂</h2>
                            <p class="cont"> 导入模板, 培训文档和视频 </p>
                        </div>
                    </li>
                </a>
                <a href="/fmobile/" target="_blank">
                    <li class="order-query">
                        <div class="cont-wrap">
                            <h2>工单查询</h2>
                            <p class="cont"> 查看周期工单和临时工单明细  </p>
                        </div>
                    </li>
                </a>
            </ul>
        </div>
    </div>
    <!--content End-->

    <!--footer Begin-->
    <div class="footer">
        <div class="center">
            <div class="footer-left left"></div>
            <div class="footer-content">
                <p>对一切的完美打理 </p>
                <p>©2015-2016万科物业啄木鸟工作室出品</p>
            </div>
            <div class="footer-left left"></div>
        </div>
    </div>
    <!--footer End-->
</div>
</body>
</html>
