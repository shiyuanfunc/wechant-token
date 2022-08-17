package com.shiyuanfunc.wechat.token;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.util.Date;

/**
 * @Author MUSI
 * @Date 2022/8/16 11:29 PM
 * @Description
 * @Version
 **/
@Data
public class RecommendInfo implements Serializable {

    @Id
    private String id;

    private String href;

    private String imgUrl;

    private String title;

    private String price;

    private String describe;

    private String timeStr;

    private String brand;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
