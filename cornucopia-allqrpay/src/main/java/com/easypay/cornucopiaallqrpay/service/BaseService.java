package com.easypay.cornucopiaallqrpay.service;

import com.easypay.cornucopiaallqrpay.dal.dao.impl.TMchInfoMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TPayChannelMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.dao.impl.TPayOrderMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.*;
import com.easypay.cornucopiacommon.constant.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: dingzhiwei
 * @date: 17/9/9
 * @description:
 */
@Service
public class BaseService {

    @Autowired
    private TPayOrderMapperImpl payOrderMapper;

    @Autowired
    private TMchInfoMapperImpl mchInfoMapper;

    @Autowired
    private TPayChannelMapperImpl payChannelMapper;


    public TMchInfo baseSelectMchInfo(String mchId) {
        return mchInfoMapper.selectByMchId(mchId);
    }

    public TPayChannel baseSelectPayChannel(String mchId, String channelId) {
        TPayChannelExample example = new TPayChannelExample();
        TPayChannelExample.Criteria criteria = example.createCriteria();
        criteria.andChannelidEqualTo(channelId);
        criteria.andMchidEqualTo(mchId);
        List<TPayChannel> payChannelList = payChannelMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(payChannelList)) return null;
        return payChannelList.get(0);
    }

    public int baseCreatePayOrder(TPayOrder payOrder) {
        return payOrderMapper.insertSelective(payOrder);
    }

    public TPayOrder baseSelectPayOrder(String payOrderId) {
        return payOrderMapper.selectByPrimaryKey(payOrderId);
    }

    public TPayOrder baseSelectPayOrderByMchIdAndPayOrderId(String mchId, String payOrderId) {
        TPayOrderExample example = new TPayOrderExample();
        TPayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andPayOrderIdEqualTo(payOrderId);
        List<TPayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public TPayOrder baseSelectPayOrderByMchIdAndMchOrderNo(String mchId, String mchOrderNo) {
        TPayOrderExample example = new TPayOrderExample();
        TPayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andPayOrderIdEqualTo(mchOrderNo);
        List<TPayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public int baseUpdateStatus4Ing(String payOrderId, String channelOrderNo) {
        TPayOrder payOrder = new TPayOrder();
        payOrder.setStatus(PayConstant.PAY_STATUS_PAYING);
        if(channelOrderNo != null) payOrder.setChannelorderno(channelOrderNo);
        payOrder.setPaysucctime(System.currentTimeMillis());
        TPayOrderExample example = new TPayOrderExample();
        TPayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_INIT);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateStatus4Success(String payOrderId, String channelOrderNo) {
        TPayOrder payOrder = new TPayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
        if(channelOrderNo != null) payOrder.setChannelorderno(channelOrderNo);
        payOrder.setPaysucctime(System.currentTimeMillis());
        TPayOrderExample example = new TPayOrderExample();
        TPayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_PAYING);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateStatus4Complete(String payOrderId) {
        TPayOrder payOrder = new TPayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_COMPLETE);
        TPayOrderExample example = new TPayOrderExample();
        TPayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_SUCCESS);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateNotify(String payOrderId, byte count) {
        TPayOrder newPayOrder = new TPayOrder();
        newPayOrder.setNotifycount(count);
        newPayOrder.setLastnotifytime(System.currentTimeMillis());
        newPayOrder.setPayOrderId(payOrderId);
        return payOrderMapper.updateByPrimaryKeySelective(newPayOrder);
    }

    public int baseUpdateNotify(TPayOrder payOrder) {
        return payOrderMapper.updateByPrimaryKeySelective(payOrder);
    }

}
