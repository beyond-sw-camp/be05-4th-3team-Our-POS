package com.hanwha.ordersystem.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hanwha.ordersystem.global.Result;

@RestControllerAdvice
public class ControllerAdvisor {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public Result<Void> handleIllegalArgumentException(IllegalArgumentException e) {
		return new Result<>(false, e.getMessage(), null);
	}

	@ExceptionHandler(IllegalStateException.class)
	public Result<Void> handleIllegalStateException(IllegalStateException e) {
		return new Result<>(false, e.getMessage(), null);
	}
}
