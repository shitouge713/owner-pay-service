package com.owner.pay.service.impl;

import com.owner.pay.dao.domain.CartDomain;
import com.owner.pay.dao.pojo.OCart;
import com.owner.pay.exception.NoWarnException;
import com.owner.pay.remote.IRemoteOrderService;
import com.owner.pay.service.ICartService;
import com.owner.pay.vo.Result;
import feign.FeignException;
import io.seata.spring.annotation.GlobalLock;
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

    /**
     * seata-AT模式处理分布式事务
     * @return
     */
    @Override
    @GlobalTransactional(lockRetryInterval=3, lockRetryTimes=2, rollbackFor = Exception.class)
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
        for (OCart car : carList) {
            cartDomain.save(car);
        }
        log.info("支付业务插入数据成功");
        Result result = remoteOrderService.handleOrder();
        if(result.getCode()!="200"){
            throw new NoWarnException("调用订单中台异常");
        }
        return true;
    }

    @Override
    public List<OCart> queryByUserId(String userId) {
        return cartDomain.queryByUserId(userId);
    }

    /**
     * @GlobalTransactional/@GlobalLock
     * 这个如果不用@GlobalTransactional/@GlobalLock，可能会导致脏写，就是写操作 对全局事务未执行完但本地事务执行完的数据进行了修改，造成脏写
     * @param userId
     * @return
     */
    @Override
    @GlobalLock(lockRetryInterval=3,lockRetryTimes=2)
    public int deleteByUserId(String userId) {
        return cartDomain.deleteByUserId(userId);
    }
}
