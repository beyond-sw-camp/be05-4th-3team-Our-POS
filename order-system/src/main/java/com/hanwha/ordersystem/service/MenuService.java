package com.hanwha.ordersystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanwha.ordersystem.domain.Menu;
import com.hanwha.ordersystem.domain.Store;
import com.hanwha.ordersystem.dto.MenuRequestDto;
import com.hanwha.ordersystem.dto.MenuResponseDto;
import com.hanwha.ordersystem.dto.MenuUpdateDto;
import com.hanwha.ordersystem.file.FileStore;
import com.hanwha.ordersystem.file.UploadFile;
import com.hanwha.ordersystem.repository.MenuRepository;
import com.hanwha.ordersystem.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

	public static final String MENU_NOT_FOUND_MESSAGE = "Menu not found";

	private final MenuRepository menuRepository;
	private final StoreRepository storeRepository;
	private final FileStore fileStore;

	@Transactional
	public void deleteMenu(Long menuId) {
		log.info("MenuService.deleteMenu() called");
		menuRepository.deleteById(menuId);
	}

	@Transactional
	public void addMenu(MenuRequestDto menuRequestDto, MultipartFile multipartFile) {
		log.info("MenuService.addMenu() called");
		Store store = storeRepository.findById(menuRequestDto.getStoreId()).orElseThrow(
			() -> new IllegalArgumentException("Store not found"));

		addMenuPicture(menuRequestDto, multipartFile);

		Menu menu = Menu.builder()
			.name(menuRequestDto.getName())
			.price(menuRequestDto.getPrice())
			.store(store)
			.category(menuRequestDto.getMenuCategory())
			.menuPictureUrl(menuRequestDto.getMenuPictureUrl())
			.build();

		menuRepository.save(menu);
	}

	private void addMenuPicture(MenuRequestDto menuRequestDto, MultipartFile multipartFile) {
		if (multipartFile != null) {
			UploadFile uploadFile = fileStore.storeFile(multipartFile);
			menuRequestDto.setMenuPictureUrl(uploadFile.getStoreFilename());
		} else {
			menuRequestDto.setMenuPictureUrl("default.png");
		}
	}

	@Transactional
	public void updateMenu(Long menuId, MenuUpdateDto menuDto) {
		log.info("MenuService.updateMenu() called");
		Menu menu = menuRepository.findById(menuId).orElseThrow(
			() -> new IllegalArgumentException(MENU_NOT_FOUND_MESSAGE));

		menu.update(menuDto.getName(), menuDto.getPrice(), menuDto.getMenuCategory(), menuDto.getMenuPictureUrl());
	}

	public MenuResponseDto getMenu(Long menuId) {
		log.info("MenuService.getMenu() called");
		Menu menu = menuRepository.findById(menuId).orElseThrow(
			() -> new IllegalArgumentException(MENU_NOT_FOUND_MESSAGE));

		return new MenuResponseDto(menu);
	}

	public List<MenuResponseDto> getMenusByStoreId(Long storeId) {
		log.info("MenuService.getMenusByStoreId() called");
		List<Menu> menus = menuRepository.findByStoreId(storeId).orElseThrow(
			() -> new IllegalArgumentException(MENU_NOT_FOUND_MESSAGE));

		return menus.stream().map(MenuResponseDto::new).toList();
	}
}
