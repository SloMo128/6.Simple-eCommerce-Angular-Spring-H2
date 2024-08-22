package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "ecommerce_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name can't be empty")
	@Lob
	private String name;

	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	private Double price;

	@NotNull(message = "pictureUrl cannot be null")
	@NotBlank(message = "pictureUrl can't be empty")
	private String pictureUrl;
	
	public Product() {
	  }

	  public Product(Long id, String name, Double price, String pictureUrl) {
		this.id = id;
	    this.name = name;
	    this.price = price;
	    this.pictureUrl = pictureUrl;
	  }
	
	public Long getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public Double getPrice() {
	    return price;
	  }

	  public String getPictureUrl() {
	    return pictureUrl;
	  }
	
	
	@Override
	public String toString() {
		return "Comment [id=" + this.getId() + ", " 
				+ "name=" + this.getName() + ", " 
				+ "price=" + this.getPrice() + ", " 
				+ "pictureUrl=" + this.getPictureUrl() + "]";
	}
}