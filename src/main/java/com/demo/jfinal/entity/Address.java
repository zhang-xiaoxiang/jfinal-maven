package com.demo.jfinal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 收货地址列表
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货地址表主键id
     */
    private String addressId;

    /**
     * 用户主键ID
     */
    private String userId;

    /**
     * 收货人姓名
     */
    private String addressName;

    /**
     * 联系方式
     */
    private String addressPhone;

    /**
     * 所在地区
     */
    private String addressRegion;

    /**
     * 详细收货地址
     */
    private String addressLocation;

    /**
     * 是否设置为默认地址(0普通状态,1是默认)
     */
    private Integer addressState;

    /**
     * 时间
     */
    private LocalDateTime addressTime;


}
