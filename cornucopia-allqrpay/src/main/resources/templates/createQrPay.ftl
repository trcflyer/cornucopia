<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

<!-- Begin page content -->
<div class="container">
    <div class="main" align="center">
        <div class="inwrap">
            <h3>扫码支付</h3>
            <div class="example" >
                <div id="qrcode"></div>
                <div><h4 >请使用支付宝或微信手机客户端扫一扫</h4></div>
            </div>
        </div>
    </div>
</div>

<#--<footer class="footer">-->
    <#--<div class="container">-->
        <#--<p class="text-muted">&copy;2017 xxpay <script src="https://s13.cnzz.com/z_stat.php?id=1262480096&web_id=1262480096" language="JavaScript"></script></p>-->
    <#--</div>-->
<#--</footer>-->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="//cdn.jsdelivr.net/ie10-viewport/1.0.0/ie10-viewport.min.js"></script>
<script src="//cdn.jsdelivr.net/jquery/1.12.1/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="${base}/js/qrcode.min.js"></script>

<script>
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        width : 200,
        height : 200
    });

    function makeCode () {
        var baseUrl = '${baseUrl}';
        var value = '${value}';
        var mchId = ${mchId};
        var qrText = baseUrl+"/goods/payOrder.html?mchId=" + mchId+"&value="+value;
        qrcode.makeCode(qrText);
    }

    makeCode();

</script>

</body>
</html>
