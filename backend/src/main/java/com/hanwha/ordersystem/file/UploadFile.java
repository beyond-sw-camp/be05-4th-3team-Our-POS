package com.hanwha.ordersystem.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {

	private String originalFilename;
	private String storeFilename;
}
