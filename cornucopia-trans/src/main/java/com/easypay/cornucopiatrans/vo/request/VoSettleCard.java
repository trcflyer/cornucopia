package com.easypay.cornucopiatrans.vo.request;

import com.easypay.cornucopiatrans.vo.BaseRequest;
import lombok.Data;

@Data
public class VoSettleCard extends BaseRequest {
    private String userId;
    private String cardNo;
    private String bankId;
}
