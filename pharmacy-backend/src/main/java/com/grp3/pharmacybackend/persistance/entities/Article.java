package com.grp3.pharmacybackend.persistance.entities;

import java.io.Serializable;





public class Article implements Serializable {




	private Long articleId;

	
	private Long articleBarcode;

	
	private String articleName;

	
	private Integer articleQuantity;

	
	private Float articlePrice; 

	public Float getArticlePrice() {
		return this.articlePrice;
	}

	public void setArticlePrice(Float articlePrice) {
		this.articlePrice = articlePrice;
	}
	

	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getArticleBarcode() {
		return this.articleBarcode;
	}

	public void setArticleBarcode(Long articleBarcode) {
		this.articleBarcode = articleBarcode;
	}

	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Integer getArticleQuantity() {
		return this.articleQuantity;
	}

	public void setArticleQuantity(Integer articleQuantity) {
		this.articleQuantity = articleQuantity;
	}
	
	

	
}

