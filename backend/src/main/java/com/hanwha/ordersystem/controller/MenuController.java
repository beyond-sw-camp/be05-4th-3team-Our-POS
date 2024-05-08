package com.hanwha.ordersystem.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hanwha.ordersystem.dto.MenuRequestDto;
import com.hanwha.ordersystem.dto.MenuResponseDto;
import com.hanwha.ordersystem.dto.MenuUpdateDto;
import com.hanwha.ordersystem.global.Result;
import com.hanwha.ordersystem.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuController {

	private final MenuService menuService;

	@GetMapping("/{menuId}")
	public Result<MenuResponseDto> getMenu(@PathVariable Long menuId) {
		log.info("MenuController.getMenu() called");
		log.info("menuId: {}", menuId);

		MenuResponseDto menu = menuService.getMenu(menuId);
		return new Result<>(true, "개별 메뉴 조회 성공", menu);
	}

	@GetMapping("store/{storeId}")
	public Result<List<MenuResponseDto>> getMenusByStoreId(@PathVariable Long storeId) {
		log.info("MenuController.getMenusByStoreId() called");
		log.info("storeId: {}", storeId);

		List<MenuResponseDto> menus = menuService.getMenusByStoreId(storeId);
		return new Result<>(true, "가게 별 메뉴 조회 성공", menus);
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public Result<Void> addMenu(@RequestPart MenuRequestDto menuRequestDto,
		@RequestPart(required = false) MultipartFile multipartFile) {
		log.info("MenuController.addMenu() called");

		menuService.addMenu(menuRequestDto, multipartFile);
		return new Result<>(true, "메뉴 추가 성공", null);
	}

	@PutMapping("/{menuId}")
	public Result<Void> updateMenu(@PathVariable Long menuId, @RequestBody MenuUpdateDto menuUpdateDto) {
		log.info("MenuController.updateMenu() called");

		menuService.updateMenu(menuId, menuUpdateDto);
		return new Result<>(true, "메뉴 수정 성공", null);
	}

	@DeleteMapping("/{menuId}")
	public Result<Void> deleteMenu(@PathVariable Long menuId) {
		log.info("MenuController.deleteMenu() called");
		log.info("menuId: {}", menuId);

		menuService.deleteMenu(menuId);
		return new Result<>(true, "메뉴 삭제 성공", null);
	}
}
