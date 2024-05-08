<template>
  <header>
    <MenuHeader />
  </header>
  <div class="menu-order">
    <div class="menu-order-left">
      <MenuOrderCategory :categories="categories" @categorySelected="onCategorySelected" />
    </div>
    <div class="menu-order-right">
      <MenuList :category="selectedCategory"/>
    </div>
  </div>
</template>

<script setup>
import { ref} from 'vue';
import MenuHeader from "@/components/MenuHeader.vue";
import MenuOrderCategory from "@/components/MenuOrder/MenuOrderCategory.vue";
import MenuList from "@/components/MenuOrder/MenuList.vue";
// import axios from 'axios';

// data
const categories = ref([]);
const selectedCategory = ref('');
// const menus = ref([]);

// methods 자식의 카테고리. category 를 넘김 => 자식 -> 부모 성공.
const onCategorySelected = (category) => {
  // 한국어임.
  selectedCategory.value = category;
};

</script>

<style scoped>
.menu-order {
  display: flex;
}

.menu-order-left {
  flex: 2; /* 왼쪽 영역을 2의 비율로 설정 */
}
.menu-order-right {
  flex: 8; /* 오른쪽 영역을 8의 비율로 설정 */
}
</style>