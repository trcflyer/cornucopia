<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="fyunli">

    <base id="base" href="${base}">
    <title>互恩智惠</title>
    <!-- Bootstrap core CSS -->
    <link href="//cdn.jsdelivr.net/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/css/main.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="${base}/logo.svg">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="//cdn.jsdelivr.net/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div><h4 >${dealMessage}</h4></div>
</body>
</html>
