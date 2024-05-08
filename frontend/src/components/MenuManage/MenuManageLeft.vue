<template>
  <div class = "container">
    <div class = "category" v-for="category in categories" :key="category" @click="selectCategory(category)" :class="{ 'selected': selectedCategory === category }">
      {{ category }}
    </div>
    <button @click="goToMenuAdd()">+ 메뉴 추가</button>
  </div>
</template>

<script setup>
  import { ref, defineEmits } from 'vue';
  import axios from 'axios';
  import router from "@/router";

  const categories = ref([]);
  // 자식 컴포넌트에서 이벤트 생성 후 호출
  const emit = defineEmits(["categorySelected"]);
  let selectedCategory = null;

  // fetch categories
  const fetchCategories = async () => {
    try {
      const response = await axios.get('http://43.201.111.166:8080/api/menus/store/1');
      categories.value = [...new Set(response.data.data.map(menu => menu.menuCategory.value))];
      console.log(categories);
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };

  const goToMenuAdd = () => {
    router.push({ name: "menuadd" });
  }
  // select category
  // 이건 부모 컴포넌트에서 이벤트 수신
  // category 는 menuCategory.value(한국어) 임.
  const selectCategory = (category) => {
    emit('categorySelected', category);
    selectedCategory = category;
    console.log(category);
  };

  fetchCategories(); // 데이터 가져오기
</script>

<style scoped>
.container {
  height: 100vh; /* 세로 길이를 화면의 전체 높이로 설정 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.container > div {
  font-weight: bold; /* 두꺼운 글씨체로 설정 */
  cursor: pointer;
  padding: 10px;
  margin: 5px;
}

.container > div.selected {
  background-color: white; /* 클릭된 카테고리의 배경색을 흰색으로 설정 */
  border-radius: 5px; /* 모서리를 둥글게 만듭니다. */
}

button {
  position: fixed; /* 화면 아래에 고정되도록 설정 */
  bottom: 20px; /* 아래 여백 조정 */
  right: 20px; /* 오른쪽 여백 조정 */
  margin-right: auto;
  cursor: pointer;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 10px;
  background-color: rgb(255, 255, 255); /* 주황색 배경색 */
  color: rgb(0, 0, 0);
  text-decoration: none;
  border-radius: 50px; /* 원형 버튼을 만들기 위해 반지름을 50%로 설정 */
  border: 1px solid orange; /* 원형 테두리를 주황색으로 설정 */
}
.category{
  cursor: pointer;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 10px;
  background-color: rgb(255, 255, 255); /* 주황색 배경색 */
  color: rgb(0, 0, 0);
  text-decoration: none;
  border-radius: 50px; /* 원형 버튼을 만들기 위해 반지름을 50%로 설정 */
  border: 3px solid orange; /* 원형 테두리를 주황색으로 설정 */
  width: 150px; /* 가로 넓이를 200px로 고정 */
}
.category.selected {
  border-color: red; /* 선택된 카테고리에 대한 테두리 색상을 변경 */
}
</style>