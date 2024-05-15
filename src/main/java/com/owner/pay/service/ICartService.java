package com.owner.pay.service;

import com.owner.pay.dao.pojo.OCart;

import java.util.LinkedList;
import java.util.List;

public interface ICartService {
    Boolean save();

    List<OCart> queryByUserId(String userId);

    int deleteByUserId(String userId);
}
