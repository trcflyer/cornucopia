package com.easypay.cornucopiatrans.services;

import com.easypay.cornucopiatrans.vo.BaseResponse;

public interface BindSettleCardService {

    BaseResponse bindSettleCard(String userId, String cardNo, String cardDistrict);

}
