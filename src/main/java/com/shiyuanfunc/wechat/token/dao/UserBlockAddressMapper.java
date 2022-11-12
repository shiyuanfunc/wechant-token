package com.shiyuanfunc.wechat.token.dao;

import com.shiyuanfunc.wechat.token.domain.entity.UserBlockAddress;

public interface UserBlockAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBlockAddress record);

    int insertSelective(UserBlockAddress record);

    UserBlockAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBlockAddress record);

    int updateByPrimaryKey(UserBlockAddress record);
}