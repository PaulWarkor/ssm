<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>FiMi</title>
    <!--<link rel="stylesheet" href="css/common.css">-->
    <style>
        html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p {
            margin: 0;
            padding: 0;
            border: 0;
            outline: 0;
            font-size: 100%;
            vertical-align: baseline;
            background: transparent;
            -webkit-appearance: none;
        }

        *, *:before, *:after {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }

        html {
            font-size: 62.5%;
            font-family: "Microsoft YaHei", Arial, Helvetica, sans-serif;
            height: 100%;
        }

        body {
            background-color: #f0f2f1;
        }

        /*no data*/
        .page-none {
            font-size: 1.6rem;
            text-align: center;
            position: absolute;
            top: 50%;
            height: 100px;
            margin-top: -50px;
            width: 100%;
        }

        .page-none p {
            color: #979799;
        }

        .page-none div {
            margin-top: 20px;
        }
    </style>
</head>
<body>


<div class="page-none">
    <p>服务器内部错误! 500</p>
    <div><a href="/" id="close">返回首页</a></div>
</div>

</body>
</html>
