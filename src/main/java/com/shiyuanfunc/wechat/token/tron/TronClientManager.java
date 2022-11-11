package com.shiyuanfunc.wechat.token.tron;

import com.shiyuanfunc.wechat.token.domain.tron.TronAccountInfo;
import com.shiyuanfunc.wechat.token.manage.ElasticSearchManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.contract.Contract;
import org.tron.trident.core.contract.Trc20Contract;
import org.tron.trident.core.exceptions.IllegalException;
import org.tron.trident.core.key.KeyPair;
import org.tron.trident.core.transaction.TransactionBuilder;
import org.tron.trident.proto.Chain;
import org.tron.trident.proto.Response;
import org.tron.trident.utils.Convert;

import java.math.BigInteger;

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
    public String generateAddress(String userId) {
        KeyPair keyPair = KeyPair.generate();
        String privateKey = keyPair.toPrivateKey();
        String publicKey = keyPair.toPublicKey();
        String address = keyPair.toBase58CheckAddress();
        log.info(" {} {} {}", privateKey, publicKey, address);

        TronAccountInfo tronAccountInfo = TronAccountInfo.builder()
                .privateKey(privateKey)
                .publicKey(publicKey)
                .tronAddress(address)
                .userId(userId)
                .build();
        elasticSearchManager.save(tronAccountInfo, TronAccountInfo.TRON_ACCOUNT_INDEX);
        return address;
    }


    public Object sendTransaction() throws IllegalException {
        ApiWrapper apiWrapper = ApiWrapper.ofNile("c01110037444bab19a8774256855b716c6277f81d93fd53ab8f92f14b0eff1d9");
        Response.TransactionExtention transaction = apiWrapper.transfer("TS4N8P4czcVZuKJBkdqPPmkqnHbgYTz9ig", "TVBBbWC26HJZsutQ8LVJyMQUvC5RZZswkT", 0);
        Chain.Transaction signTransaction = apiWrapper.signTransaction(transaction);
        TransactionBuilder builder = new TransactionBuilder(signTransaction);
        builder.setFeeLimit(5000000);
        builder.setMemo("memo");
        signTransaction = builder.build();
        String s = apiWrapper.broadcastTransaction(signTransaction);
        log.info("广播交易 {}", s);
        return s;
    }


    public Object transferContract(){
        ApiWrapper client = ApiWrapper.ofNile("c01110037444bab19a8774256855b716c6277f81d93fd53ab8f92f14b0eff1d9");
        Contract contract = client.getContract("TLBaRhANQoJFTqre9Nf1mjuwNWjCJeYqUL");
        Trc20Contract trc20Contract = new Trc20Contract(contract, "TS4N8P4czcVZuKJBkdqPPmkqnHbgYTz9ig", client);
        return trc20Contract.transfer("TVBBbWC26HJZsutQ8LVJyMQUvC5RZZswkT", 90, 100000, "demo", 10000000L);
    }


    public String balanceOfAddress(String address){
        ApiWrapper apiWrapper = ApiWrapper.ofNile("98d10cb2ed5efbebd497ed7e6991595b8df1d46ba921f44d9e74dbd04c0db527");
        String ownAddress = "TYuVKUAvXesMFJLLNiGEw5kWVzwkhEEoE5";
        Contract contract = apiWrapper.getContract("TF17BgPaZYbz8oxbjhriubPDsA7ArKoLX3");
        Trc20Contract token = new Trc20Contract(contract, ownAddress, apiWrapper);
        BigInteger balance = token.balanceOf(ownAddress);
        System.out.println("balance: " + balance.toString());

        BigInteger transferAmount = Convert.toSun("0", Convert.Unit.TRX).toBigInteger();
        long l = Convert.toSun("10", Convert.Unit.TRX).longValue();
        String transfer = token.transfer("TS4N8P4czcVZuKJBkdqPPmkqnHbgYTz9ig", transferAmount.longValue(), 0, "备注啊", l);
        return transfer;
    }

}
