<template>
  <div class="admin-home">
    <div class="management">
      <!-- 네비게이션 바 -->
      <nav class="navbar">
        <div class="navbar-left">
          <div class="logo">OUR %POS</div>
          <div class="menu">MENU</div>
          <div class="store">STORE</div>
          <div class="user">USER</div>
          <div class="admin">ADMIN</div>
        </div>
        <div class="navbar-right">
          <button class="order-now" @click="goBack">GO BACK</button>
        </div>
      </nav>

      <Table />
    </div>
   
    
  </div>
  
</template>

<script>

import Table from '@/components/OrderManage/SelectTable.vue';


export default {
  methods: {
    goBack() {
      this.$router.push({ name: "manager" });
    }
  },
  components: {

    Table,
    //Table2
  }
}
</script>

<style scoped>
.admin-home {
  display: flex;
}

.management {
  flex: 3; /* 주문 관리 컴포넌트가 왼쪽에 넓게 설정 */
  padding: 20px; /* 여백 설정 */
}

.order-tab {
  flex: 1; /* 오른쪽에 Order 컴포넌트가 꽉 차도록 설정 */
  padding: 20px; /* 여백 설정 */
  background-color: rgba(255, 255, 255, 0.542);
  
  
}
/* 네비게이션 바 스타일 */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 10px 20px;
  width: 100%; 
}

.navbar-left {
  display: flex;
  align-items: center;
}

.navbar-right {
  display: flex;
}

.logo, .menu, .store, .user, .admin, .order-now {
  margin-right: 20px;
}
.order-now {
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
.table {
  /* Table 컴포넌트의 크기를 줄이는 스타일 */
  width: 50%; /* 원하는 크기로 설정 */
  margin: auto; /* 가운데 정렬을 위해 margin 설정 */
}




</style>