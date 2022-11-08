package com.shiyuanfunc.wechat.token.tron;

import com.shiyuanfunc.wechat.token.domain.tron.TronAccountInfo;
import com.shiyuanfunc.wechat.token.manage.ElasticSearchManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okio.ByteString;
import org.springframework.stereotype.Component;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.exceptions.IllegalException;
import org.tron.trident.core.key.KeyPair;
import org.tron.trident.core.transaction.TransactionBuilder;
import org.tron.trident.proto.Chain;
import org.tron.trident.proto.Contract;
import org.tron.trident.proto.Response;

/**
 * @Author MUSI
 * @Date 2022/11/8 10:32 PM
 * @Description
 * @Version
 **/

@Slf4j
@Component
@RequiredArgsConstructor
public class TronClientManager {

    private final ElasticSearchManager elasticSearchManager;

    /**
     * 生成tron帐号
     * @return
     */
    public String generateAddress() {
        KeyPair keyPair = KeyPair.generate();
        String privateKey = keyPair.toPrivateKey();
        String publicKey = keyPair.toPublicKey();
        String address = keyPair.toBase58CheckAddress();
        log.info(" {} {} {}", privateKey, publicKey, address);

        TronAccountInfo tronAccountInfo = TronAccountInfo.builder()
                .privateKey(privateKey)
                .publicKey(publicKey)
                .tronAddress(address)
                .build();
        elasticSearchManager.save(tronAccountInfo, TronAccountInfo.TRON_ACCOUNT_INDEX);
        return address;
    }


    public Object sendTransaction() throws IllegalException {
        ApiWrapper apiWrapper = ApiWrapper.ofNile("c01110037444bab19a8774256855b716c6277f81d93fd53ab8f92f14b0eff1d9");

        Response.TransactionExtention transaction = apiWrapper.transfer("TS4N8P4czcVZuKJBkdqPPmkqnHbgYTz9ig", "TVBBbWC26HJZsutQ8LVJyMQUvC5RZZswkT", 1000000);

        Chain.Transaction signTransaction = apiWrapper.signTransaction(transaction);
        TransactionBuilder builder = new TransactionBuilder(signTransaction);
        builder.setFeeLimit(5000000);
        builder.setMemo("this is a message ....");
        Chain.Transaction build = builder.build();
        String s = apiWrapper.broadcastTransaction(build);
        log.info("广播交易 {}", s);
        return s;
    }

}
