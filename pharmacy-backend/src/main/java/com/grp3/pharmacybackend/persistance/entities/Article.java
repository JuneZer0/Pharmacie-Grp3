package com.grp3.pharmacybackend.persistance.entities;

import java.io.Serializable;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

   	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid2")
	private Long articleId;
	private Long articleBarcode;
	private String articleName;
	private Integer articleQuantity;
	private Boolean articleAvailability;

	
}

