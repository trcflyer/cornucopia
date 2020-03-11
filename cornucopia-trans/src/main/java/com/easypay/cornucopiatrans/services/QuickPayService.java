package com.easypay.cornucopiatrans.services;

import com.easypay.cornucopiatrans.vo.BaseResponse;
import com.easypay.cornucopiatrans.vo.request.VoQuickPay;

public interface QuickPayService {

    BaseResponse doquickpay(VoQuickPay voQuickPay);

}
