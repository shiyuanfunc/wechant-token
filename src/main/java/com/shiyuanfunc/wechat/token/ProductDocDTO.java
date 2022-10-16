package com.shiyuanfunc.wechat.token;

import java.io.Serializable;
import java.util.List;

/**
 * 商品索引
 * 
 * @author zxy
 *
 */
public class ProductDocDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String enterpriseMS; // 企业微信号 ☆☆☆☆☆☆☆☆☆必填☆☆☆☆☆☆☆☆☆
	private String productId; // 商品 ID ☆☆☆☆☆☆☆☆☆必填☆☆☆☆☆☆☆☆☆

	private String enterpriseId; // 企业ID
	private String brandName; // 品牌名称

	/**
	 * 品牌code
	 */
	private String brandCode;

	/**
	 * 品牌id
	 */
	private String brandId;

	private String proName; // 商品名称
	private String proDisplayName; // 商品显示名称
	private String proNo; // 商品货号 ERP的货号
	private String proUsp; // 设计卖点
	private String proDescribe; // 商品描述
	private String proCategoryId; // 商品类目ID

	/**
	 * 品类code 空格分隔
	 */
	private String proCategoryCode;
	private String proCategoryErpCode; // 商品erp类目code
	private String categoryGroupId; // 企业分组ID
	private double proPrice = 0d; // 商品零售价
	private String logisticsTemplateId; // 物流模板ID
	private long createTime = 0L; // 创建时间 2016-06-30 08:30:30 = 20160630083030
	private long updateTime = 0L; // 更新时间 2016-06-30 08:30:30 = 20160630083030
	private long erpCreateTime = 0L; // erp创建时间 2016-06-30 08:30:30 =
										// 20160630083030
	private long erpUpdateTime = 0L; // erp更新时间 2016-06-30 08:30:30 =
										// 20160630083030
	private int proSource = 1; // 商品来源 1:erp 2:自定义
	private int proStatus = 1; // 商品状态 0:删除 1:待处理 2:未上架（门店商品 下架）3:上架（门店商品）
	private int consummateStatus = 0; // 信息完善状态 1:已完善 0:未完善 (门店商品下的未完善=待更新)
	private int timingShelves = 0;// 定时上架状态
	private long timingTime = 0L;// 定时上架时间 2016-06-30 08:30:30 = 20160630083030
	private int discountPromotions = 0;// 是否折扣促销 0:可以参与打折 1:不参与打折活动

	private List<ProductDocAttrDTO> proAttr; // 商品基本属性
	private List<ProductDocImageDTO> proImage; // 商品图片
	private List<ProductDocSkuDTO> proSku; // 商品sku

	private String[] storeIds; // 展现门店  使用门店4位code

	private long stock = 0L;//商品门店库存

	/**
	 * 1电商发货；2门店发货
	 */
	private Integer deliveryType;

	/**
	 * 商品来源
	 * 1 ERP同步
	 * 2 自建
	 */
	private Integer sourceType;

	/**
	 * 商品用途
	 * 1 售卖
	 * 2 礼品
	 */
	private Integer applyType;

	/**
	 * 商品类型
	 * 1 非兑换码商品
	 * 2 兑换码商品
	 */
	private Integer goodsType;

	/**
	 * 主图完善状态
	 * 1 已完善
	 * 0 未完善
	 */
	private Integer mainImageComplete;

	/**
	 * sku图片完善状态
	 * 1 已完善
	 * 0 未完善
	 */
	private Integer skuImageComplete;

	/**
	 * 图文完善状态
	 * 1 已完善
	 * 0 未完善
	 */
	private Integer detailComplete;

	/**
	 * 视频完善状态
	 */
	private Integer videoComplete;

	/**
	 * 总库存
	 */
	private Integer totalStock;

	/**
	 * 创建人id
	 */
	private String creatorId;

	/**
	 * 创建人
	 */
	private String createName;

	/**
	 * 商品中心上下架时间
	 */
	private Long onShelfTime;

	/**
	 * 商品中心上架时间
	 * 只放上架时间
	 * 非上架状态时需清掉
	 */
	private Long saleTime;

	/**
	 * 微商城销售价
	 */
	private Double mallSalePrice;

	/**
	 * 微商城库存
	 */
	private Integer mallStock;

	/**
	 * 微商城销量
	 */
	private Integer mallSaleAmount;

	/**
	 * 微商城发布时间
	 */
	private Long mallCreateTime;

	/**
	 * 微商城修改时间
	 */
	private Long mallUpdateTime;

	/**
	 * 微商城上架状态
	 * 0删除 1仓库 2上架 3回收站
	 */
	private Integer mallStatus;

	/**
	 * 微商城上架时间
	 */
	private Long mallShelvesTime;

	/**
	 * 引用方  多个用空格分隔
	 * 1 微商城
	 * 2 积分商城
	 * eg: 1 2
	 */
	private String sourceRefer;

	private String proAttrValueIds;

	/**
	 * 商品规格值code
	 */
	private String proNormValues;

	private String skuIds;

	/**
	 * 微商城商品分类
	 */
	private String productMallTag;

	/**
	 * 商品规格类型
	 * 1 统一规格
	 * 2 多种规格
	 */
	private Integer skuType;


	public String getEnterpriseMS() {
		return enterpriseMS;
	}

	public void setEnterpriseMS(String enterpriseMS) {
		this.enterpriseMS = enterpriseMS;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDisplayName() {
		return proDisplayName;
	}

	public void setProDisplayName(String proDisplayName) {
		this.proDisplayName = proDisplayName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProUsp() {
		return proUsp;
	}

	public void setProUsp(String proUsp) {
		this.proUsp = proUsp;
	}

	public String getProDescribe() {
		return proDescribe;
	}

	public void setProDescribe(String proDescribe) {
		this.proDescribe = proDescribe;
	}

	public String getProCategoryId() {
		return proCategoryId;
	}

	public void setProCategoryId(String proCategoryId) {
		this.proCategoryId = proCategoryId;
	}

	public String getProCategoryCode() {
		return proCategoryCode;
	}

	public void setProCategoryCode(String proCategoryCode) {
		this.proCategoryCode = proCategoryCode;
	}

	public String getProCategoryErpCode() {
		return proCategoryErpCode;
	}

	public void setProCategoryErpCode(String proCategoryErpCode) {
		this.proCategoryErpCode = proCategoryErpCode;
	}

	public String getCategoryGroupId() {
		return categoryGroupId;
	}

	public void setCategoryGroupId(String categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}

	public double getProPrice() {
		return proPrice;
	}

	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}

	public String getLogisticsTemplateId() {
		return logisticsTemplateId;
	}

	public void setLogisticsTemplateId(String logisticsTemplateId) {
		this.logisticsTemplateId = logisticsTemplateId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public long getErpCreateTime() {
		return erpCreateTime;
	}

	public void setErpCreateTime(long erpCreateTime) {
		this.erpCreateTime = erpCreateTime;
	}

	public long getErpUpdateTime() {
		return erpUpdateTime;
	}

	public void setErpUpdateTime(long erpUpdateTime) {
		this.erpUpdateTime = erpUpdateTime;
	}

	public int getProSource() {
		return proSource;
	}

	public void setProSource(int proSource) {
		this.proSource = proSource;
	}

	public int getProStatus() {
		return proStatus;
	}

	public void setProStatus(int proStatus) {
		this.proStatus = proStatus;
	}

	public int getConsummateStatus() {
		return consummateStatus;
	}

	public void setConsummateStatus(int consummateStatus) {
		this.consummateStatus = consummateStatus;
	}

	public int getTimingShelves() {
		return timingShelves;
	}

	public void setTimingShelves(int timingShelves) {
		this.timingShelves = timingShelves;
	}

	public long getTimingTime() {
		return timingTime;
	}

	public void setTimingTime(long timingTime) {
		this.timingTime = timingTime;
	}

	public int getDiscountPromotions() {
		return discountPromotions;
	}

	public void setDiscountPromotions(int discountPromotions) {
		this.discountPromotions = discountPromotions;
	}

	public List<ProductDocAttrDTO> getProAttr() {
		return proAttr;
	}

	public void setProAttr(List<ProductDocAttrDTO> proAttr) {
		this.proAttr = proAttr;
	}

	public List<ProductDocImageDTO> getProImage() {
		return proImage;
	}

	public void setProImage(List<ProductDocImageDTO> proImage) {
		this.proImage = proImage;
	}

	public List<ProductDocSkuDTO> getProSku() {
		return proSku;
	}

	public void setProSku(List<ProductDocSkuDTO> proSku) {
		this.proSku = proSku;
	}

	public String[] getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String[] storeIds) {
		this.storeIds = storeIds;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getMainImageComplete() {
		return mainImageComplete;
	}

	public void setMainImageComplete(Integer mainImageComplete) {
		this.mainImageComplete = mainImageComplete;
	}

	public Integer getSkuImageComplete() {
		return skuImageComplete;
	}

	public void setSkuImageComplete(Integer skuImageComplete) {
		this.skuImageComplete = skuImageComplete;
	}

	public Integer getDetailComplete() {
		return detailComplete;
	}

	public void setDetailComplete(Integer detailComplete) {
		this.detailComplete = detailComplete;
	}

	public Integer getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Long getOnShelfTime() {
		return onShelfTime;
	}

	public void setOnShelfTime(Long onShelfTime) {
		this.onShelfTime = onShelfTime;
	}

	public Double getMallSalePrice() {
		return mallSalePrice;
	}

	public void setMallSalePrice(Double mallSalePrice) {
		this.mallSalePrice = mallSalePrice;
	}

	public Integer getMallStock() {
		return mallStock;
	}

	public void setMallStock(Integer mallStock) {
		this.mallStock = mallStock;
	}

	public Integer getMallSaleAmount() {
		return mallSaleAmount;
	}

	public void setMallSaleAmount(Integer mallSaleAmount) {
		this.mallSaleAmount = mallSaleAmount;
	}

	public Long getMallCreateTime() {
		return mallCreateTime;
	}

	public void setMallCreateTime(Long mallCreateTime) {
		this.mallCreateTime = mallCreateTime;
	}

	public String getProAttrValueIds() {
		return proAttrValueIds;
	}

	public void setProAttrValueIds(String proAttrValueIds) {
		this.proAttrValueIds = proAttrValueIds;
	}

	public String getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String skuIds) {
		this.skuIds = skuIds;
	}

	public String getProNormValues() {
		return proNormValues;
	}

	public void setProNormValues(String proNormValues) {
		this.proNormValues = proNormValues;
	}

	public Integer getVideoComplete() {
		return videoComplete;
	}

	public void setVideoComplete(Integer videoComplete) {
		this.videoComplete = videoComplete;
	}

	public String getProductMallTag() {
		return productMallTag;
	}

	public void setProductMallTag(String productMallTag) {
		this.productMallTag = productMallTag;
	}

	public Long getMallShelvesTime() {
		return mallShelvesTime;
	}

	public void setMallShelvesTime(Long mallShelvesTime) {
		this.mallShelvesTime = mallShelvesTime;
	}

	public Integer getMallStatus() {
		return mallStatus;
	}

	public void setMallStatus(Integer mallStatus) {
		this.mallStatus = mallStatus;
	}

	public String getSourceRefer() {
		return sourceRefer;
	}

	public void setSourceRefer(String sourceRefer) {
		this.sourceRefer = sourceRefer;
	}

	public Integer getSkuType() {
		return skuType;
	}

	public void setSkuType(Integer skuType) {
		this.skuType = skuType;
	}

	public Long getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Long saleTime) {
		this.saleTime = saleTime;
	}

	public Long getMallUpdateTime() {
		return mallUpdateTime;
	}

	public void setMallUpdateTime(Long mallUpdateTime) {
		this.mallUpdateTime = mallUpdateTime;
	}
}
