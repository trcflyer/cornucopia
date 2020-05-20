package com.easypay.cornucopiaallqrpay.dal.dao.impl;

import com.easypay.cornucopiaallqrpay.bean.PayOrderQueryBean;
import com.easypay.cornucopiaallqrpay.dal.dao.TPayOrderMapper;
import com.easypay.cornucopiaallqrpay.dal.pojo.TPayOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPayOrderMapperImpl extends TPayOrderMapper {

    TPayOrder selectByPayOrderId(@Param("payOrderId") String payOrderId);

    TPayOrder selectMchOrderId(@Param("mchId") String mchId,@Param("mchOrderId") String mchOrderId);

    int updateRespByPayOrderId(TPayOrder tPayOrder);

    List<TPayOrder> selectMchOrderIdList(PayOrderQueryBean tPayOrder);

    Long selectMchTotalAmt(PayOrderQueryBean tPayOrder);

    Long selectMchTotalNum(PayOrderQueryBean tPayOrder);

}
