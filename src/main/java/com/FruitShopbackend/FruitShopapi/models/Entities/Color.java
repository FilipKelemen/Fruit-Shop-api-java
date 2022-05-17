package com.FruitShopbackend.FruitShopapi.models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Color {

	@Id
	@GeneratedValue
	private UUID colorId;

	@JsonBackReference
	@ManyToMany(
			cascade = CascadeType.ALL,
			mappedBy = "colors"
	)
	private Set<Product> products = new HashSet<>();

	@Column(unique = true)
	@NotEmpty(message = "Name can not be empty")
	private String name;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Color() {
		super();
	}

	public Color(UUID colorId, Set<Product> products, @NotEmpty(message = "Name can not be empty") String name) {
		super();
		this.colorId = colorId;
		this.products = products;
		this.name = name;
	}
	
	public UUID getColorId() {
		return colorId;
	}

	public void setColorId(UUID colorId) {
		this.colorId = colorId;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Color{" +
				"colorId=" + colorId +
				", products=" + products +
				", name='" + name + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
