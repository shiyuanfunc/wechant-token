package com.shiyuanfunc.wechat.token.service.local;

import com.shiyuanfunc.wechat.token.dao.UserBlockAddressMapper;
import com.shiyuanfunc.wechat.token.domain.entity.UserBlockAddress;
import com.shiyuanfunc.wechat.token.domain.tron.TronAccountInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tron.trident.core.key.KeyPair;

import java.util.Date;

/**
 * @Author MUSI
 * @Date 2022/11/12 11:40 AM
 * @Description
 * @Version
 * 用户地址服务
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class UserBlockAddressService {

    private final UserBlockAddressMapper userBlockAddressMapper;

    /**
     * 生成tron帐号
     * @return
     */
    public String generateAddress(Long userId) {
        KeyPair keyPair = KeyPair.generate();
        String privateKey = keyPair.toPrivateKey();
        String publicKey = keyPair.toPublicKey();
        String address = keyPair.toBase58CheckAddress();
        log.info(" {} {} {}", privateKey, publicKey, address);
        this.saveUserBlockAddress(privateKey, publicKey, address, userId);
        return address;
    }

    public Long saveUserBlockAddress(String privateKey, String publicKey, String tronAddress, Long userId){

        UserBlockAddress userBlockAddress = new UserBlockAddress();
        userBlockAddress.setUserId(userId);
        userBlockAddress.setTronAddress(tronAddress);
        userBlockAddress.setTronPrivateKey(privateKey);
        userBlockAddress.setTronPublicKey(publicKey);
        userBlockAddress.setStatus(1);
        userBlockAddress.setCreateTime(new Date());
        userBlockAddress.setUpdateTime(new Date());
        userBlockAddressMapper.insertSelective(userBlockAddress);
        return userBlockAddress.getId();
    }

}
