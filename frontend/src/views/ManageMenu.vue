<template>
  <header>
    <MenuHeader />
  </header>
  <div class="menu-manage">
    <div class="menu-manage-left">
      <MenuManageLeft :categories="categories" @categorySelected="onCategorySelected" />
    </div>
    <div class="menu-manage-right">
      <MenuManageRight :category="selectedCategory"/>
<!--      :selectedCategory="selectedCategory"-->
    </div>
  </div>
</template>

<script setup>
import MenuHeader from "@/components/MenuHeader.vue";
import MenuManageLeft from "@/components/MenuManage/MenuManageLeft.vue";
import MenuManageRight from "@/components/MenuManage/MenuManageRight.vue";
import { ref} from 'vue';
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
.menu-manage {
  display: flex;
}

.menu-manage-left {
  flex: 2; /* 왼쪽 영역을 2의 비율로 설정 */
}

.menu-manage-right {
  flex: 8; /* 오른쪽 영역을 8의 비율로 설정 */
}
</style>
