<template>
  <div>
    <div class="table-list">
      <div 
        class="table" 
        v-for="table in tables" 
        :key="table.id" 
        @click="selectTable(table.orderId)"
      >
        <img :src="table.image" alt="Table {{ table.id }}" />
        <p>Table {{ table.id }}</p>
      </div>
    </div>
    <!-- 선택된 테이블의 orderId를 전달하여 OrderConfirmationTab 컴포넌트를 표시합니다. -->
    <OrderConfirmationTab :orderId="selectedOrderId" v-if="selectedOrderId !== null" :key="selectedOrderId" />
  </div>
</template>

<script>
import OrderConfirmationTab from './OrderConfirmationTab.vue';

export default {
  data() {
    return {
      tables: [
        { id: 1, image: 'https://cdn-icons-png.flaticon.com/512/7113/7113274.png', orderId: 1 },
        { id: 2, image: 'https://cdn-icons-png.flaticon.com/512/7113/7113274.png', orderId: 2 },
        { id: 3, image: 'https://cdn-icons-png.flaticon.com/512/7113/7113274.png', orderId: 3 },
        { id: 4, image: 'https://cdn-icons-png.flaticon.com/512/7113/7113274.png', orderId: 4 },
        { id: 5, image: 'https://cdn-icons-png.flaticon.com/512/7113/7113274.png', orderId: 5 }
       
      ],
      selectedOrderId: null
    };
  },
  methods: {
    selectTable(orderId) {
      // 선택된 테이블의 orderId를 업데이트합니다.
      this.selectedOrderId = orderId;
    }
  },
  components: {
    OrderConfirmationTab
  }
};
</script>

<style>
.table-list {
  display: flex;
}

.table {
  margin: 10px;
  cursor: pointer;
  flex-basis: calc(50% - 10px); /* 한 줄에 두 개의 테이블이 표시되도록 설정 */
  margin-right: 20px; /* 테이블 이미지 사이에 간격을 추가합니다. */
}

.table img {
  width: 200px;
  height: 150px;
}
</style>