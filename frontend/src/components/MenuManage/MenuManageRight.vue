<template>
  <div class = "container">
    <div v-for="menu in filteredMenus" :key="menu.menuId" class = "menu" :class="{ 'menu-new-row': index % 3 === 0 }">
      <div class = "menu-img">
        <img :src="getMenuImageUrl(menu)" alt="Menu Image">
      </div>
      <div class =  "menu-name">메뉴명 : {{ menu.name }}</div>
      <div class = "menu-price">가격 : {{ menu.price }}원</div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue"
import { ref, watch, computed } from 'vue';
import axios from 'axios';

const props = defineProps({
  // 한국어임.
  category: String
});

console.log(props.category);

// Data
const menus = ref([]);

// Methods
const fetchMenus = async () => {
  try {
    const response = await axios.get('http://43.201.111.166:8080/api/menus/store/1');
    menus.value = response.data.data;
  } catch (error) {
    console.error('Error fetching menus:', error);
  }
};

// Computed
const filteredMenus = computed(() => {
  if (!menus.value.length) {
    return [];
  }

  return menus.value.filter(menu => {
    return menu.menuCategory.value == props.category;
  });
});

// 메뉴 이미지의 URL 생성하는 함수
const getMenuImageUrl = (menu) => {
  // 프로필 이미지 파일명
  const profileImageFileName = menu.menuPictureUrl;
  // 백엔드에서 프로필 이미지가 저장된 디렉토리 경로
  const profileImageDirectory = 'http://43.201.111.166:8080/images/';
  console.log(`${profileImageDirectory}${profileImageFileName}`);
  // 프로필 이미지의 URL 생성
  return `${profileImageDirectory}${profileImageFileName}`;
};

// Watch
watch(() => props.category, () => {
  fetchMenus();
});

// 초기 데이터 가져오기
fetchMenus();
</script>

<style scoped>
.container {
  display: flex;
  flex-wrap: wrap;
}

.menu {
  width: calc(33.33% - 20px);
  margin: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-sizing: border-box; /* 너비에 padding, border 포함 */
}

.menu-new-row {
  clear: both;
}

.menu-img {
  width: 80px;
  height: 70px;
  background-color: #ddd; /* 이미지 박스의 색상 */
  border-radius: 5px; /* 모서리를 둥글게 */
  margin: 0 auto 10px; /* 이미지를 가로 중앙으로 정렬하고, 아래에 공백 추가 */
  display: block; /* 가로 중앙 정렬을 위해 블록 레벨로 설정 */
}

.menu-name,
.menu-price {
  text-align: center;
}

.menu-img img {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: 100%;
}
</style>