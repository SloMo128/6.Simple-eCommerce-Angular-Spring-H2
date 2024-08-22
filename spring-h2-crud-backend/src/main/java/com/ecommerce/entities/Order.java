package com.ecommerce.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ecommerce_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dataCreated;

	@NotBlank(message = "status can't be empty")
	private String status;

	public Order() {
	}

	public Order(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getDataCreated() {
		return dataCreated;
	}

	@Override
	public String toString() {
		return "Comment [id=" + this.getId() + ", " + "status=" + this.getStatus() + ", " + "dataCreated="
				+ this.getDataCreated() + "]";
	}
}