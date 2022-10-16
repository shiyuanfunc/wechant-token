package com.shiyuanfunc.wechat.token;

import java.io.Serializable;
import java.util.List;

public class ProductDocSkuDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String skuCode; //skuCode
	private String imageUrl;
	
	private List<ProductDocAttrDTO> skuAttr; //sku属性

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ProductDocAttrDTO> getSkuAttr() {
		return skuAttr;
	}

	public void setSkuAttr(List<ProductDocAttrDTO> skuAttr) {
		this.skuAttr = skuAttr;
	}

}
