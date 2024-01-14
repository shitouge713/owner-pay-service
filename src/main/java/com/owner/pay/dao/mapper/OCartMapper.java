package com.owner.pay.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.owner.pay.dao.pojo.OCart;
import com.owner.pay.mybatisplus.EasyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OCartMapper extends EasyBaseMapper<OCart> {

}