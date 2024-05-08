package com.hanwha.ordersystem.domain;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	private int totalPrice;

	@Builder
	public Order(Customer customer, Store store, List<OrderDetail> orderDetails) {
		this.customer = customer;
		this.store = store;
		this.totalPrice = 0;

		for (OrderDetail orderDetail : orderDetails) {
			addOrderDetail(orderDetail);
			this.totalPrice += orderDetail.getOrderDetailPrice();
		}
	}

	// 연관관계 편의 메서드
	public void addOrderDetail(OrderDetail orderDetail) {
		orderDetails.add(orderDetail);
		orderDetail.setOrder(this);
	}
}
