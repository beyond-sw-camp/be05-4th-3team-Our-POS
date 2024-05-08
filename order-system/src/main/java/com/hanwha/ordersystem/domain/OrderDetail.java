package com.hanwha.ordersystem.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Long id;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; // [WAITING("대기"), PROCESSING("조리 중"), COMPLETED("서빙 완료"), CANCELED("주문 취소");]

	private int quantity;

	@Builder
	public OrderDetail(Menu menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
		this.orderStatus = OrderStatus.WAITING;
	}

	public int getOrderDetailPrice() {
		return menu.getPrice() * quantity;
	}

	public void acceptOrder() {
		if (this.orderStatus != OrderStatus.WAITING) {
			throw new IllegalStateException("이미 주문이 처리되었습니다.");
		}

		this.orderStatus = OrderStatus.PROCESSING;
	}

	public void completeOrder() {
		if (this.orderStatus != OrderStatus.PROCESSING) {
			throw new IllegalStateException("주문이 처리되지 않았습니다.");
		}

		this.orderStatus = OrderStatus.COMPLETED;
	}

	public void cancelOrder() {
		if (this.orderStatus != OrderStatus.WAITING) {
			throw new IllegalStateException("이미 주문이 처리되었습니다.");
		}

		this.orderStatus = OrderStatus.CANCELED;
	}
}
