package com.easypay.cornucopiaallqrpay.service;

import com.easypay.cornucopiaallqrpay.dal.dao.TGoodsOrderMapper;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TGoodsOrderMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrder;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrderExample;
import com.easypay.cornucopiaallqrpay.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dingzhiwei on 17/6/2.
 */
@Component
public class GoodsOrderService {

    @Autowired
    private TGoodsOrderMapperImpl goodsOrderMapper;

   /*
    `Status` tinyint(6) NOT NULL DEFAULT '0' COMMENT '订单状态,订单生成(0),支付成功(1),处理完成(2),处理失败(-1)',
    */

    public int addGoodsOrder(TGoodsOrder goodsOrder) {
        return goodsOrderMapper.insertSelective(goodsOrder);
    }

    public TGoodsOrder getGoodsOrder(String goodsOrderId) {
        return goodsOrderMapper.selectByGoodsOrderId(goodsOrderId);
    }

    public int updateStatus4Success(String goodsOrderId) {
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_SUCCESS);
        TGoodsOrderExample example = new TGoodsOrderExample();
        TGoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_INIT);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int updateStatus4Complete(String goodsOrderId) {
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_COMPLETE);
        TGoodsOrderExample example = new TGoodsOrderExample();
        TGoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_SUCCESS);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int updateStatus4Fail(String goodsOrderId) {
        TGoodsOrder goodsOrder = new TGoodsOrder();
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_FAIL);
        TGoodsOrderExample example = new TGoodsOrderExample();
        TGoodsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsOrderIdEqualTo(goodsOrderId);
        //criteria.andStatusEqualTo(Constant.GOODS_ORDER_STATUS_SUCCESS);
        return goodsOrderMapper.updateByExampleSelective(goodsOrder, example);
    }

    public int update(TGoodsOrder goodsOrder) {
        return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
    }

}
