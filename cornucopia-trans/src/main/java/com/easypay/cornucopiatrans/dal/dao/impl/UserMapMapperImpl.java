package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.UserMapMapper;
import com.easypay.cornucopiatrans.dal.pojo.UserMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapMapperImpl extends UserMapMapper {

    @Select({"select * from user_map",
            "where user_Id = #{userId,jdbcType=VARCHAR}"
    }
    )
    @ResultMap("com.easypay.cornucopiatrans.dal.dao.UserMapMapper.BaseResultMap")
    UserMap selectByUserId(@Param("userId") String userId);
}
