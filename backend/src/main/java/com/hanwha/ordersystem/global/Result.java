package com.hanwha.ordersystem.global;

import lombok.Getter;

@Getter
public class Result<T> {

	private boolean success = true;
	private String message;
	private T data;

	public Result() {
	}

	public Result(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
}
