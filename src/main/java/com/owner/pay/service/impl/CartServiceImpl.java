package com.owner.pay.service.impl;

import com.owner.pay.dao.domain.CartDomain;
import com.owner.pay.dao.pojo.OCart;
import com.owner.pay.remote.IRemoteOrderService;
import com.owner.pay.service.ICartService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartDomain cartDomain;
    @Autowired
    private IRemoteOrderService remoteOrderService;

    @Override
    @GlobalTransactional
    public Boolean save() {
        OCart cart1 = new OCart();
        cart1.setMerchantCode("11111111");
        cart1.setUserId(11111111L);
        cart1.setSkuId("11111111");
        OCart cart2 = new OCart();
        cart2.setMerchantCode("11111111");
        cart2.setUserId(11111111L);
        cart2.setSkuId("11111111");
        List<OCart> carList = new ArrayList();
        carList.add(cart1);
        carList.add(cart2);
        cartDomain.ownerBatchInsert(carList);
        log.info("支付业务插入数据成功");
        remoteOrderService.handleOrder();
        return true;
    }
}
