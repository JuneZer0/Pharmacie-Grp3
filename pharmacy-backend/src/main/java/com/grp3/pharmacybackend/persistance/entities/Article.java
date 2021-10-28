package com.grp3.pharmacybackend.persistance.entities;

import java.io.Serializable;

import org.hibernate.annotations.Table;


import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "article")
public class Article implements Serializable {

   	@Id
	@GeneratedValue(generator = "article_id")
	@GenericGenerator(name = "article_id", strategy = GenerationType.IDENTITY)

	@Column (name= "article_id")
	private Long articleId;

	@Column (name= "article_barcode")
	private Long articleBarcode;

	@Column (name= "article_name")
	private String articleName;

	@Column (name= "article_quantity")
	private Integer articleQuantity;

	@Column (name= "article_price")
	private Float articlePrice; 
	

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

