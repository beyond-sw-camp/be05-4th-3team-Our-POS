package com.hanwha.ordersystem.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

	@Test
	@DisplayName("주문을 수락하면 주문 상태가 ACCEPTED로 변경된다.")
	void orderCancel() {
		OrderDetail orderDetail = OrderDetail.builder().build(); // default status is WAITING
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.WAITING);

		orderDetail.cancelOrder();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
	}

	@Test
	@DisplayName("이미 주문이 처리된 상태에서 주문 취소를 시도하면 예외가 발생한다.")
	void orderCancelException() {
		OrderDetail orderDetail = OrderDetail.builder().build();

		orderDetail.acceptOrder();
		assertThatThrownBy(orderDetail::cancelOrder).isInstanceOf(IllegalStateException.class);

		orderDetail.completeOrder();
		assertThatThrownBy(orderDetail::cancelOrder).isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("주문을 수락하면 주문 상태가 ACCEPTED로 변경된다.")
	void orderAccept() {
		OrderDetail orderDetail = OrderDetail.builder().build();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.WAITING);

		orderDetail.acceptOrder();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.PROCESSING);
	}

	@Test
	@DisplayName("이미 주문이 처리된 상태에서 주문 수락을 시도하면 예외가 발생한다.")
	void orderAcceptException() {
		OrderDetail orderDetail = OrderDetail.builder().build();

		orderDetail.acceptOrder();
		assertThatThrownBy(orderDetail::acceptOrder).isInstanceOf(IllegalStateException.class);

		orderDetail.completeOrder();
		assertThatThrownBy(orderDetail::acceptOrder).isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("주문을 완료하면 주문 상태가 COMPLETED로 변경된다.")
	void orderComplete() {
		OrderDetail orderDetail = OrderDetail.builder().build();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.WAITING);

		orderDetail.acceptOrder();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.PROCESSING);

		orderDetail.completeOrder();
		assertThat(orderDetail.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
	}

	@Test
	@DisplayName("주문이 처리되지 않은 상태 혹은 이미 주문이 완료된 상태에서 주문 완료를 시도하면 예외가 발생한다.")
	void orderCompleteException() {
		OrderDetail orderDetail = OrderDetail.builder().build();
		assertThatThrownBy(orderDetail::completeOrder).isInstanceOf(IllegalStateException.class);

		orderDetail.acceptOrder();
		orderDetail.completeOrder();
		assertThatThrownBy(orderDetail::completeOrder).isInstanceOf(IllegalStateException.class);
	}

	@Test
	@DisplayName("주문 상세 객체의 가격은 메뉴의 가격과 수량을 곱한 값이다.")
	void getOrderDetailPrice() {
		Menu menu = Menu.builder().price(10000).build();
		OrderDetail orderDetail = OrderDetail.builder().menu(menu).quantity(3).build();

		assertThat(orderDetail.getOrderDetailPrice()).isEqualTo(30000);
	}

}