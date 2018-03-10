package com.kiranreddy.budgettracker.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionCategory {

	@Id
	@GeneratedValue
	private Long id;

	private String category;

	private String type;
	
	public TransactionCategory() {
	}

	public TransactionCategory(Long id, String category, String type) {
		this.id = id;
		this.category = category;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}