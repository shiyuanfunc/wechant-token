package com.shiyuanfunc.wechat.token;

import java.io.Serializable;

public class ProductDocImageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imageUrl;
	private int mainPic = 0; // 是否主图 0:非主图 1:主图 2:长图

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getMainPic() {
		return mainPic;
	}

	public void setMainPic(int mainPic) {
		this.mainPic = mainPic;
	}

	public ProductDocImageDTO() {
	}
	public ProductDocImageDTO(String imageUrl, int mainPic) {
		this.imageUrl = imageUrl;
		this.mainPic = mainPic;
	}
}
