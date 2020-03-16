package com.easypay.cornucopiatrans.biz;

import com.easypay.cornucopiatrans.dal.dao.UserCardSettleMapper;
import com.easypay.cornucopiatrans.dal.dao.UserMapMapper;
import com.easypay.cornucopiatrans.dal.dao.impl.UserCardSettleMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserInfoMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserLoginMapperImpl;
import com.easypay.cornucopiatrans.dal.dao.impl.UserMapMapperImpl;
import com.easypay.cornucopiatrans.dal.pojo.UserCardSettle;
import com.easypay.cornucopiatrans.dal.pojo.UserInfo;
import com.easypay.cornucopiatrans.dal.pojo.UserLogin;
import com.easypay.cornucopiatrans.dal.pojo.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionalBiz {

    @Autowired
    private UserInfoMapperImpl userInfoMapper;

    @Autowired
    private UserLoginMapperImpl userLoginMapper;

    @Autowired
    private UserMapMapperImpl userMapMapper;

    @Autowired
    private UserCardSettleMapperImpl userCardSettleMapper;

    @Transactional
    public void register(UserLogin userLogin, UserInfo userInfo, UserMap userMap ){
        userInfoMapper.insertSelective(userInfo);
        userLoginMapper.insertSelective(userLogin);
        userMapMapper.insertSelective(userMap);
    }

    @Transactional
    public void checkName( UserInfo userInfo, UserMap userMap ){
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        userMapMapper.updateByPrimaryKeySelective(userMap);

    }

    @Transactional
    public void bindCard(UserCardSettle userCardSettle){
        userCardSettleMapper.updateCardStateByUserId(userCardSettle.getUserId());
        userCardSettleMapper.insertSelective(userCardSettle);

    }
}
