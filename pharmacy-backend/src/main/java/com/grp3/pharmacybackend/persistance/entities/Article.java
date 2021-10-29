package com.grp3.pharmacybackend.persistance.entities;

import java.io.Serializable;

public class Article implements Serializable {


	private Long articleId;

	
	private Long articleBarcode;

	
	private String articleName;

	
	private Integer articleQuantity;

	
	private Float articlePrice; 


	/**
	 * Get the article price 
	 * @return a float containing/refering to the articlePrice object
	 */
	public Float getArticlePrice() {
		return this.articlePrice;
	}

	/**
	 * Set price to the article
	 * @param articlePrice the price to set to the article
	 */
	public void setArticlePrice(Float articlePrice) {
		this.articlePrice = articlePrice;
	}
	
	/**
	 * Get the article Id
	 * @return a long refering to the articleId object
	 */
	public Long getArticleId() {
		return this.articleId;
	}

	/**
	 * ad an id to an article 
	 * @param articleId the id to set to the article
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * get the article bar code 
	 * @return a long refering to the bar code of the object
	 */
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

