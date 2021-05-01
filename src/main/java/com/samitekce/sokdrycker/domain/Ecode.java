package com.samitekce.sokdrycker.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ecode {

	@Id
	private String code;

	private String description;

	@ManyToMany(mappedBy = "keepsEcodes", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Product> products;

	public Ecode() {
		this.products = new HashSet<Product>();
	}

	public Ecode(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public Ecode(String code, String description, Set<Product> products) {
		super();
		this.code = code;
		this.description = description;
		this.products = products;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Ecode [code=" + code + ", description=" + description + "]";
	}
}
