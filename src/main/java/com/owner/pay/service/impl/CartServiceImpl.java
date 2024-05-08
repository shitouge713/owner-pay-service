package com.owner.pay.service.impl;

import com.owner.pay.remote.IRemoteOrderService;
import com.owner.pay.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IRemoteOrderService remoteOrderService;

    @Override
    public Boolean save() {
        remoteOrderService.handleOrder();
        return true;
    }
}
