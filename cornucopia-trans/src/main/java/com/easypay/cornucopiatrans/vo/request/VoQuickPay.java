package com.easypay.cornucopiatrans.vo.request;

import com.easypay.cornucopiatrans.vo.BaseRequest;
import lombok.Data;

@Data
public class VoQuickPay extends BaseRequest {
    private String userId;
    private String amt;
    private String cardSeqId;
}
