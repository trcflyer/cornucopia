<#assign base = request.contextPath />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="htf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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

    <style>
        body{font-family: 'Microsoft YaHei'; height:100%;background: rgb(250, 250, 250);}
        #amount,#error{height: 80px; line-height: 80px; text-align: center; color: #f00; font-size: 30px; font-weight: bold;}
        #error{font-size: 20px;}
        #info{padding: 0 10px; font-size: 12px;}

        .fr{text-align: right; font-weight: bold;}

        .logo {  background-color: white;  width: 100%;}

        .page-section {
            background: rgb(250, 250, 250);
            width: 100%;
            height: 50%;
            display: block;
            margin-top:10px;
        }


        .form-row {
            display: flex;
            align-items: center;
            margin-left: 10px;
            margin-right: 10px;
            height: 120px;
            align-items: center;
            margin-bottom:10px;
        }

        .form-row-label {
            font-size: 18px;
            margin: 10px 15px;
            text-align: right;
            color: #333333;
        }

        .form-row-content {

            font-size: 15px;

        }

        .input{
            height:25px;
            border:1px solid #F5BFBB;
            border-radius:6px;
            align:right;
        }

        .login-button{
            background:#EA5042;
            border-radius:220px;
            margin: 50px 100px 0;
            padding: 10px 30px;
            border:2px solid #EA5042;
            font-size:16px;
            color:white;
            vertical-align:middle;

        }
    </style>
    <script src="//cdn.jsdelivr.net/jquery/1.12.1/jquery.min.js"></script>
</head>
<body>
<div style="width:100%;text-align:center;background: rgb(250, 250, 250);">

    <img class="logo" src="${base}/img_login.png"  />

    <div class="page-section">
<form action="/allqrpay/goods/qrPay.html" method="post">

    <table border="0" align="center">
        <tbody>
        <tr class="form-row" border="0" align="center">
            <td class="form-row-label" >支付金额:</td>
            <td class="form-row-content"><input class="input"  type="text" name="payAmt"/></td>
            <td class="form-row-label">元</td>
        </tr>
        </tbody>


    </table>

    <input type="hidden" name="mchId" value="${mchId}">
    <input type="hidden" name="key" value="${key}">
    <input type="hidden" name="checkValue" value="${checkValue}">
    <br><br>
    <input class="login-button" type="submit" value="确认支付">
</form>
    </div>
</div>
</body>
</html>