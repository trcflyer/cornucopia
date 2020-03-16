package com.easypay.cornucopiatrans.vo.response;

import com.easypay.cornucopiatrans.dal.pojo.TransOrdInfo;
import com.easypay.cornucopiatrans.vo.BaseResponse;

import java.util.List;


public class ResultQueryOrderLit extends BaseResponse {

    private List<TransOrdInfo> ordInfoList;

    public List<TransOrdInfo> getOrdInfoList() {
        return ordInfoList;
    }

    public void setOrdInfoList(List<TransOrdInfo> ordInfoList) {
        this.ordInfoList = ordInfoList;
    }
}
