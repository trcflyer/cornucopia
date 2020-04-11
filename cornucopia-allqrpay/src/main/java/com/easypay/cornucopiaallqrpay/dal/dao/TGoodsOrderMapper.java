package com.easypay.cornucopiaallqrpay.dal.dao;

import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrder;
import com.easypay.cornucopiaallqrpay.dal.pojo.TGoodsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGoodsOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int countByExample(TGoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int deleteByExample(TGoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int deleteByPrimaryKey(String goodsorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int insert(TGoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int insertSelective(TGoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    List<TGoodsOrder> selectByExample(TGoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    TGoodsOrder selectByPrimaryKey(String goodsorderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int updateByExampleSelective(@Param("record") TGoodsOrder record, @Param("example") TGoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int updateByExample(@Param("record") TGoodsOrder record, @Param("example") TGoodsOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int updateByPrimaryKeySelective(TGoodsOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_order
     *
     * @mbggenerated Sat Apr 11 17:18:50 CST 2020
     */
    int updateByPrimaryKey(TGoodsOrder record);
}