package com.easypay.cornucopiatrans.services;

import com.easypay.cornucopiatrans.vo.BaseResponse;

public interface CheckRealService {
    BaseResponse checkName(String userId, String idno, String legelName,String userName);
    BaseResponse checkPhoto(String userId,String photoF,String photoB);
}
