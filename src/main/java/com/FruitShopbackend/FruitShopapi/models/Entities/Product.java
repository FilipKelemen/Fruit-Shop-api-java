package com.FruitShopbackend.FruitShopapi.models.Entities;

import com.FruitShopbackend.FruitShopapi.utility.PriceUtility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private UUID productId;

	@Column(unique = true)
	@NotEmpty(message = "The name can not be empty or null")
	private String name;

	@NotNull
	private Integer numberInStock;

	@NotNull
	private String imageUrl;

	@NotNull
	private Long priceValue;

	@Transient
	private String formattedPrice;

	@NotNull
	private String currency;

	@NotNull
	private String description;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@JsonBackReference
	@OneToMany(
		fetch = FetchType.LAZY,
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private List<CartEntry> cartEntries = new ArrayList<>();

	@JsonManagedReference
	@ManyToMany
	@JoinTable(
			name = "Product_Color",
			joinColumns = @JoinColumn(name = "product_id") ,
			inverseJoinColumns = @JoinColumn(name = "color_id")
	)
	private Set<Color> colors = new HashSet<>();
	
	public Product() {
		super();
	}

	public Product(UUID productId, @NotEmpty(message = "The name can not be empty or null") String name,
				   Set<Color> colors,@NotNull Integer numberInStock,@NotNull String imageUrl,
				   @NotNull Long priceValue, @NotNull String currency,@NotNull String description) {
		super();
		this.productId = productId;
		this.name = name;
		this.colors = colors;
		this.numberInStock = numberInStock;
		this.imageUrl = imageUrl;
		this.priceValue = priceValue;
		this.currency = currency;
		this.description = description;
	}
	
	public List<CartEntry> getCartEntries() {
		return cartEntries;
	}
	
	public void setCartEntries(List<CartEntry> cartEntries) {
		this.cartEntries = cartEntries;
	}
	
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Color> getColors() {
		return colors;
	}

	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}

	public Integer getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(Integer numberInStock) {
		this.numberInStock = numberInStock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Long priceValue) {
		this.priceValue = priceValue;
	}

	public String getFormattedPrice() {
		try {
			return PriceUtility.getFormattedPrice(priceValue,"$");
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", name='" + name + '\'' +
				", numberInStock=" + numberInStock +
				", imageUrl='" + imageUrl + '\'' +
				", priceValue=" + priceValue +
				", formattedPrice='" + getFormattedPrice() + '\'' +
				", currency='" + currency + '\'' +
				", description='" + description + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", colors=" + colors +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(priceValue, product.priceValue) && Objects.equals(productId, product.productId) && Objects.equals(name, product.name) && Objects.equals(numberInStock, product.numberInStock) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(formattedPrice, product.formattedPrice) && Objects.equals(currency, product.currency) && Objects.equals(description, product.description) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt) && Objects.equals(cartEntries, product.cartEntries) && Objects.equals(colors, product.colors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, name, numberInStock, imageUrl, priceValue, formattedPrice, currency, description, createdAt, updatedAt, cartEntries, colors);
	}
}
