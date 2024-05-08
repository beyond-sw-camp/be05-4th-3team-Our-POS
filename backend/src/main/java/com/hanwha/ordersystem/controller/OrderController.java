package com.hanwha.ordersystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanwha.ordersystem.dto.OrderRequestDto;
import com.hanwha.ordersystem.dto.OrderResponseDto;
import com.hanwha.ordersystem.global.Result;
import com.hanwha.ordersystem.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/{orderId}")
	public Result<OrderResponseDto> getOrder(@PathVariable Long orderId) {
		log.info("OrderController.getOrder() called");
		log.info("orderId: {}", orderId);

		OrderResponseDto order = orderService.getOrder(orderId);
		return new Result<>(true, "개별 주문 조회 성공", order);
	}

	@GetMapping("/store/{storeId}")
	public Result<List<OrderResponseDto>> getOrdersByStoreId(@PathVariable Long storeId) {
		log.info("OrderController.getOrdersByStoreId() called");
		log.info("storeId: {}", storeId);

		List<OrderResponseDto> orders = orderService.findAllByStoreId(storeId);
		return new Result<>(true, "가게 별 주문 조회 성공", orders);
	}

	@GetMapping("/customer/{customerId}")
	public Result<List<OrderResponseDto>> getOrdersByCustomerId(@PathVariable Long customerId) {
		log.info("OrderController.getOrdersByCustomerId() called");
		log.info("customerId: {}", customerId);

		List<OrderResponseDto> orders = orderService.findAllByCustomId(customerId);
		return new Result<>(true, "테이블 별 주문 조회 성공", orders);
	}

	@PostMapping
	public Result<Void> order(@RequestBody OrderRequestDto orderDto) {
		log.info("OrderController.order() called");
		log.info("order: {}", orderDto);

		orderService.order(orderDto);
		return new Result<>(true, "주문 성공", null);
	}

	@PutMapping("/{orderId}/accept")
	public Result<Void> acceptOrder(@PathVariable Long orderId) {
		log.info("OrderController.acceptOrder() called");
		log.info("orderId: {}", orderId);

		orderService.accept(orderId);
		return new Result<>(true, "주문 수락 요청 성공", null);
	}

	@PutMapping("/{orderId}/cancel")
	public Result<Void> cancelOrder(@PathVariable Long orderId) {
		log.info("OrderController.cancelOrder() called");
		log.info("orderId: {}", orderId);

		orderService.cancel(orderId);
		return new Result<>(true, "주문 취소 요청 성공", null);
	}

	@PutMapping("/{orderId}/complete")
	public Result<Void> completeOrder(@PathVariable Long orderId) {
		log.info("OrderController.completeOrder() called");
		log.info("orderId: {}", orderId);

		orderService.complete(orderId);
		return new Result<>(true, "서빙 완료 요청 성공", null);
	}
}
