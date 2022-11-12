package com.shiyuanfunc.wechat.token.domain.entity;

import java.util.Date;

public class UserBlockAddress {
    private Long id;

    private Long userId;

    private String tronAddress;

    private String tronPrivateKey;

    private String tronPublicKey;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTronAddress() {
        return tronAddress;
    }

    public void setTronAddress(String tronAddress) {
        this.tronAddress = tronAddress == null ? null : tronAddress.trim();
    }

    public String getTronPrivateKey() {
        return tronPrivateKey;
    }

    public void setTronPrivateKey(String tronPrivateKey) {
        this.tronPrivateKey = tronPrivateKey == null ? null : tronPrivateKey.trim();
    }

    public String getTronPublicKey() {
        return tronPublicKey;
    }

    public void setTronPublicKey(String tronPublicKey) {
        this.tronPublicKey = tronPublicKey == null ? null : tronPublicKey.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}