package com.easypay.cornucopiacommon.enums;

public enum HuiFuUrlEmum {
    URL_7100("/ceresmerch/controller7100/register","商户进件接口"),
    URL_7108("/ceresmerch/controller7108/upgrade","身份证照片认证接口"),
    URL_7109("/ceresmerch/controller7109/upgrade","人像认证接口"),
    URL_7111("/ceresmerch/controller7111/updateCashCard","商户结算卡变更接口"),
    URL_7402("/cerestrans/controller7402/getQuickPaySupportBankList","快捷支付支持银行列表查询"),
    URL_7403("/cerestrans/controller7403/getQuickPayBindCardList","快捷支付已绑定银行卡列表查询"),
    URL_7412("/cerestrans/controller7412/bindCardOrder","快捷支付绑卡接口"),
    URL_7409("/cerestrans/controller7409/quickPayOrder","快捷支付下单接口"),
    URL_7206("cerestrans/controller7206/doCash","取现接口"),
    URL_7301("/ceresquery/controller7301/queryOrdInfo","交易订单查询接口"),
    URL_7303("/ceresquery/controller7303/queryAccoutInfo","查询账户的总余额、可用余额")
    ;
    // 成员变量
    private String requestUrl;
    private String urlDesc;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUrlDesc() {
        return urlDesc;
    }

    public void setUrlDesc(String urlDesc) {
        this.urlDesc = urlDesc;
    }

    // 构造方法
    private HuiFuUrlEmum(String requestUrl, String urlDesc) {
        this.requestUrl = requestUrl;
        this.urlDesc = urlDesc;
    }
}
