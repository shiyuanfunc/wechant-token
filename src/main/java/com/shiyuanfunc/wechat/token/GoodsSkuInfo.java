package com.shiyuanfunc.wechat.token;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @Author MUSI
 * @Date 2022/10/14 9:14 PM
 * @Description
 * @Version
 **/
@Data
public class GoodsSkuInfo implements Serializable {

    @Id
    private String id;

    private List<RecommendInfo> recommondInfos;
}
