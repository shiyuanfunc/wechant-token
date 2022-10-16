package com.shiyuanfunc.wechat.token;

import java.io.Serializable;

public class ProductDocAttrDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String enterpriseAttrId; // 企业属性ID
	private String enterpriseAttrName; // 平台标准属性名称

	/**
	 * 属性code
	 */
	private String enterpriseAttrCode;
	private String dictAttrName; // 平台标准属性名称
	private String enterpriseAttrValueId; // 企业属性值ID
	private String enterpriseAttrValue; // 企业属性值

	/**
	 * 属性值code
	 */
	private String enterpriseAttrValueCode;
	private String dictAttrValue; // 平台标准属性值

	public String getEnterpriseAttrId() {
		return enterpriseAttrId;
	}

	public void setEnterpriseAttrId(String enterpriseAttrId) {
		this.enterpriseAttrId = enterpriseAttrId;
	}

	public String getEnterpriseAttrName() {
		return enterpriseAttrName;
	}

	public void setEnterpriseAttrName(String enterpriseAttrName) {
		this.enterpriseAttrName = enterpriseAttrName;
	}

	public String getDictAttrName() {
		return dictAttrName;
	}

	public void setDictAttrName(String dictAttrName) {
		this.dictAttrName = dictAttrName;
	}

	public String getEnterpriseAttrValueId() {
		return enterpriseAttrValueId;
	}

	public void setEnterpriseAttrValueId(String enterpriseAttrValueId) {
		this.enterpriseAttrValueId = enterpriseAttrValueId;
	}

	public String getEnterpriseAttrValue() {
		return enterpriseAttrValue;
	}

	public void setEnterpriseAttrValue(String enterpriseAttrValue) {
		this.enterpriseAttrValue = enterpriseAttrValue;
	}

	public String getDictAttrValue() {
		return dictAttrValue;
	}

	public void setDictAttrValue(String dictAttrValue) {
		this.dictAttrValue = dictAttrValue;
	}

	public String getEnterpriseAttrCode() {
		return enterpriseAttrCode;
	}

	public void setEnterpriseAttrCode(String enterpriseAttrCode) {
		this.enterpriseAttrCode = enterpriseAttrCode;
	}

	public String getEnterpriseAttrValueCode() {
		return enterpriseAttrValueCode;
	}

	public void setEnterpriseAttrValueCode(String enterpriseAttrValueCode) {
		this.enterpriseAttrValueCode = enterpriseAttrValueCode;
	}

	public ProductDocAttrDTO() {
	}
	
	public ProductDocAttrDTO(String enterpriseAttrId, String enterpriseAttrName,
                             String enterpriseAttrValueId, String enterpriseAttrValue, String dictAttrName , String dictAttrValue) {
		this.enterpriseAttrId = enterpriseAttrId;
		this.enterpriseAttrName = enterpriseAttrName;
		this.dictAttrName = dictAttrName;
		this.enterpriseAttrValueId = enterpriseAttrValueId;
		this.enterpriseAttrValue = enterpriseAttrValue;
		this.dictAttrValue = dictAttrValue;
	}
	
	

}
