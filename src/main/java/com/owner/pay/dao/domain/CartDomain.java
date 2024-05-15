package com.owner.pay.dao.domain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.owner.pay.dao.mapper.OCartMapper;
import com.owner.pay.dao.pojo.OCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title CartDomain
 * @Copyright: Copyright (c) 2021
 * @Description:
 * @Auther: zgd
 * @Created on 2022/7/08
 */
@Slf4j
@Component
public class CartDomain extends ServiceImpl<OCartMapper, OCart> implements IService<OCart> {
    @Autowired
    private OCartMapper oCartMapper;

    public List<OCart> queryByUserId(String userId) {
        List<OCart> list = oCartMapper.selectList(new LambdaQueryWrapper<OCart>().eq(OCart::getUserId, userId));
        return list;
    }

    public int deleteByUserId(String userId) {
        return oCartMapper.delete(new LambdaQueryWrapper<OCart>().eq(OCart::getUserId, userId));
    }

}
