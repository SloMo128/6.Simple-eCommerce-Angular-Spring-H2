package com.ecommerce.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProduct {

	@EmbeddedId
	private OrderProductId id = new OrderProductId();

	@ManyToOne
	@MapsId("orderId")
	private Order order;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@Column(nullable = false)
	private Integer quantity;

	public OrderProduct() {
	}

	public OrderProduct(Order order, Product product, Integer quantity) {
		this.id = new OrderProductId(order.getId(), product.getId());
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	public OrderProductId getId() {
		return id;
	}

	public void setId(OrderProductId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		this.id.setOrderId(order.getId());
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.id.setProductId(product.getId());
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Embeddable
	public static class OrderProductId implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long orderId;
		private Long productId;

		public OrderProductId() {
		}

		public OrderProductId(Long orderId, Long productId) {
			this.orderId = orderId;
			this.productId = productId;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}
	}
	
	
}
