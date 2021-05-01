package com.samitekce.sokdrycker.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {

	@Id
	private String ean;

	private String name;

	private double sugar;

	@ManyToMany
	@JoinTable(name = "products_have", joinColumns = {
			@JoinColumn(name = "product_ean", referencedColumnName = "ean") }, inverseJoinColumns = {
					@JoinColumn(name = "ecode_code", referencedColumnName = "code") })
	private Set<Ecode> keepsEcodes = new HashSet<Ecode>(0);

	public Product() {
		this.keepsEcodes = new HashSet<Ecode>();
	}

	public Product(String ean, String name, double sugar) {
		this.ean = ean;
		this.name = name;
		this.sugar = sugar;
	}

	public Product(String ean, String name, double sugar, Set<Ecode> keepsEcodes) {
		this.ean = ean;
		this.name = name;
		this.sugar = sugar;
		this.keepsEcodes = keepsEcodes;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public Set<Ecode> getKeepsEcodes() {
		return keepsEcodes;
	}

	public void setKeepsEcodes(Set<Ecode> keepsEcodes) {
		this.keepsEcodes = keepsEcodes;
	}

	@Override
	public String toString() {
		return "Product [ean=" + ean + ", name=" + name + ", sugar=" + sugar + ", keepsEcodes=" + keepsEcodes + "]";
	}
}
