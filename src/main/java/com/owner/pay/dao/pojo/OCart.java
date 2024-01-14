package com.owner.pay.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode
@TableName("o_cart")
public class OCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Integer bizType;

    private String merchantCode;

    private String skuId;

    private Integer quantity;

    private Integer valid;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;
}