<template>
  <Header>
    <nav class="navbar">
      <div class="navbar-left">
        <div class="logo">OUR %POS</div>
        <div class="menu">MENU</div>
        <div class="manage">MANAGE</div>
        <div class="add">ADD</div>
        <div class="admin">ADMIN</div>
      </div>
      <div class="navbar-right">
        <div class="go_back" @click="goBack">Go Back</div>
      </div>
    </nav>
  </Header>

  <form @submit.prevent="addMenu" class="body">

    <div class="menu-top">
      <div class="img">
        <img :src="imageSrc" class="img-show" style="max-width: 200px; max-height: 200px;">
        <input type="file" class="img-upload" @change="readInputFile">
      </div>
      <div class="menu">
        <div class="menu-category">
          <label for="category">상품 카테고리</label>
          <br>
          <select v-model="menuCategory" @change="categoryChanged">
            <option value="">카테고리 선택</option>
            <option value="HAMBURGER">햄버거</option>
            <option value="DESSERT">디저트</option>
            <option value="DRINK">음료</option>
          </select>
        </div>
        <div class="menu-name">
          상품명
          <br>
          <input v-model="menuRequestDto.name" class="menu-name-input" placeholder="상품명">
        </div>
        <div class="menu-price">
          상품 가격
          <br>
          <input v-model.number="menuRequestDto.price" class="menu-price-input" placeholder="상품 가격">
        </div>
      </div>
    </div>

    <div class="menu-bottom">
      <div class="btn-container">
        <button class="btn_add" type="submit">추가</button>
        <button class="btn_exit" @click="goBack">취소</button>
      </div>
    </div>

  </form>

</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';

const router = useRouter();
const categories = ref([]);
const menuCategory = ref('');
const imageSrc = ref('');
const formData = new FormData();
const menuRequestDto = ref({
  storeId: 1,
  name: '',
  price: 0,
  menuCategory: ''
});

const goBack = () => {
  router.push({ name: "menu" });
};

const fetchCategories = async () => {
  try {
    const response = await axios.get('http://43.201.111.166:8080/api/menus/store/1');
    // categories.value = [...new Set(response.data.data.map(menu => menu.menuCategory))];
    categories.value = response.data.data.map(menu => menu.menuCategory);
    categories.value = categories.value.filter((category, index, self) => {
      const key = category.key;
      const isDuplicate = self.findIndex(c => c.key === key) !== index;
      return !isDuplicate;
    });


  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};

const categoryChanged = () => {
  console.log('menuCategory:', menuCategory.value);
  menuRequestDto.value.menuCategory = menuCategory.value;
};

const readInputFile = (event) => {
  formData.append('multipartFile', event.target.files[0]);
  const file = event.target.files[0];
  console.log(file);
  if (!file) return;

  for (const pair of formData.entries()) {
    const key = pair[0];
    const value = pair[1];
    if (key === 'multipartFile') {
      console.log('multipartFile:', value);
    } else {
      console.log(key + ': ' + value);
    }
  }
  const reader = new FileReader();

  reader.onload = () => {
    imageSrc.value = reader.result;
    console.log(imageSrc.value);
  };

  reader.readAsDataURL(file);
};

const addMenu = async () => {
  try {

    formData.append('menuRequestDto', new Blob([JSON.stringify(menuRequestDto.value)], { type: "application/json" }));

    // formData에 잘 들어가 있음..
    for (const pair of formData.entries()) {
      const key = pair[0];
      const value = pair[1];
      if (key === 'multipartFile') {
        console.log('multipartFile:', value);
      } else {
        console.log(key + ': ' + value);
      }
    }

    await axios.post(
        'http://43.201.111.166:8080/api/menus',
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
    );

    console.log('메뉴 추가 완료:');
    router.push({ name: 'menu' });
  } catch (error) {
    console.error('메뉴 추가 에러:', error);
  }

};

fetchCategories();
</script>

<style scoped>
/* Header */
header {
  flex: 3; /* 주문 관리 컴포넌트가 왼쪽에 넓게 설정 */
  padding: 20px; /* 여백 설정 */
}
.img-show {
  width: 200px;
  height: 200px;
  object-fit: contain; /* 이미지 비율 유지하며 요소에 맞춤 */
}
.body{
  margin:300px;
  margin-top: 30px;
  padding : 20px;
  border : #ccc solid 1px;
}

.menu-top {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.img {
  float: left;
  display: flex;
  flex-direction: column;
}

.img-show,
.img-upload {
  margin-bottom: 10px;
}

.menu {
  float: right;
  padding : 5px;
}

.menu-category , .menu-name, .menu-price{
  padding : 3px;
}

.menu-bottom {
  clear: both;
  display: flex; /* 추가된 부분 */
  flex-direction: column; /* 추가된 부분 */
  align-items: flex-start; /* 추가된 부분 */
  padding : 5px;
}

/* 상품 설명과 버튼 컨테이너의 스타일 추가 */
.menu-detail {
  float: left;
}

.btn-container {
  float: right;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 10px 20px;
}

.navbar-left {
  display: flex;
  align-items: center;
}

.navbar-right {
  display: flex;
}

.logo, .menu, .manage, .admin, .add, .go_back {
  margin-right: 20px;
}

.go_back{
  cursor: pointer;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 10px;
  background-color: rgb(255, 255, 255); /* 주황색 배경색 */
  color: rgb(0, 0, 0);
  text-decoration: none;
  border-radius: 50px; /* 원형 버튼을 만들기 위해 반지름을 50%로 설정 */
  border: 3px solid orange; /* 원형 테두리를 주황색으로 설정 */
}
.menu-bottom {
  position: relative; /* 상위 요소에 대해 위치 지정 */
}

.btn-container {
  position: absolute; /* 상위 요소에 대해 위치 지정 */
  bottom: 0; /* 맨 아래에 위치 */
  right: 0; /* 맨 오른쪽에 위치 */
}

</style>