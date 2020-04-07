<#assign base = request.contextPath />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="htf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <base id="base" href="${base}">
    <title>互恩智惠聚合支付</title>
    <style>
        body{font-family: 'Microsoft YaHei';}
        #amount,#error{height: 80px; line-height: 80px; text-align: center; color: #f00; font-size: 30px; font-weight: bold;}
        #error{font-size: 20px;}
        #info{padding: 0 10px; font-size: 12px;}
        table{width: 100%; border-collapse: collapse;}
        tr{border: 1px solid #ddd;}
        td{padding: 10px;}
        .fr{text-align: right; font-weight: bold;}
    </style>
    <script src="//cdn.jsdelivr.net/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>
<div style="width:100%;text-align:center">
<form action="/goods/qrPay.html" method="post">
    支付金额: <input type="text" name="payAmt" />
    <input type="hidden" name="mchId" value="${mchId}" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</div>
</body>
</html>