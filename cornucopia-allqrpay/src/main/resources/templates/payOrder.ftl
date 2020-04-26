<#assign base = request.contextPath />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="htf-8"/>
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

<style type="text/css">
    #main{
        font-family: sans-serif;
    }

    #head{
        text-align: center;
        padding-top: 10rem;
        font-size: 3rem;
        margin-bottom: 4rem;
    }
    .text_title{
        margin: 0.6rem;
    }

    #controls_amount{
        margin: 0 auto;
        padding-left: 2rem;
        padding-right: 2rem;
        border-radius: 1rem 1rem 1rem 1rem;
        background: #ffffff;
        width: 85%;
        height: 8rem;
    }

    #amount{
        float: left;
        width: 70%;
        margin: 0 auto;

    }

    #number{
        font-size: 3rem;
        height: 7.5rem;
        border:0;
        outline:0;
    }

    #icon{
        float: right;
        font-size: 4rem;
        color: #333333;
        line-height: 8rem;

    }

    #controls_text{
        margin: 0 auto;
        margin-top: 3rem;
        padding-left: 2rem;
        padding-right: 2rem;
        border-radius: 1rem 1rem 1rem 1rem;
        background: #ffffff;
        width: 85%;
        height: 8rem;
    }

    #text{
        width: 100%;
        font-size: 2.5rem;
        height: 7.5rem;
        border: 0;
        outline:0;
    }

    input{
        border: 0;
        background: none;
        outline:0;
        border:1px solid #ffffff;
        border-radius: 1rem 1rem 1rem 1rem;
    }

    #button_div{
        padding: 5rem;
        text-align: center;
    }

    .button{
        background: #e61c21;
        width: 25rem;
        height: 8rem;
        border-radius:50rem 50rem 50rem 50rem;
        font-size: 2.5rem;
        color: #ffffff;
</style>
</head>
<body style="background:#f6f6f6;">
<div id="main">
    <div id="head">
        <img src="${base}/logo.png" width="120px" height="120px" />
        <p class="text_title">${mchName}</p>
    </div>
    <form id="formid" action="/allqrpay/goods/qrPay.html" method="post">
        <div id="controls_amount">
            <div id="amount">
                <input type="text" name="payAmt" id="number" placeholder="消费金额" title="金额/元" required="required" onkeyup="this.value=/^[0-9]*\.?[0-9]{0,2}$/.test(this.value) ? this.value : this.value.substring(0,this.value.length-1)"/>
            </div>
            <div id="icon">
                元
            </div>
        </div>
        <div id="controls_text">
            <input type="text" id="text" placeholder="添加备注(20字以内)" maxlength="40" />
        </div>
        <input type="hidden" name="mchId" value="${mchId}">
        <input type="hidden" name="key" value="${key}">
        <input type="hidden" name="checkValue" value="${checkValue}">

        <div id="button_div">
            <input type="submit"  class="button" value="确认支付">
        </div>
    </form>
</div>
</body>
</html>