package com.easypay.cornucopiatrans.dal.dao.impl;

import com.easypay.cornucopiatrans.dal.dao.SequenceMapper;
import com.easypay.cornucopiatrans.dal.pojo.Sequence;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapperImpl extends SequenceMapper {

    @Select({
            "select",
            "id, seq_name, next_value, increment_by, min_value, max_value, version",
            "from sequence",
            "where seq_name = #{seqName,jdbcType=VARCHAR}"
    })
    @ResultMap("com.easypay.cornucopiatrans.dal.dao.SequenceMapper.BaseResultMap")
    Sequence selectBySeqName(@Param("seqName") String seqName);


    @Update({
            "update sequence",
            "SET next_value = #{nextValue,jdbcType=BIGINT},",
            "version = version + 1",
            "WHERE seq_name = #{seqName,jdbcType=VARCHAR}",
            "AND version = #{version,jdbcType=INTEGER}"
    })
    int updateBySeqNameAndVersion(@Param("seqName") String seqName, @Param("nextValue") long nextValue, @Param("version") Integer version);

}
