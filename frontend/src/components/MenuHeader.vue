<script setup>
  import { useRouter } from 'vue-router'

  const router = useRouter();

  const goBack = () => {
    router.push({ name: "manager" });
  }
</script>

<template>
  <Header>
    <nav class="navbar">
      <div class="navbar-left">
        <div class="logo">OUR %POS</div>
        <div class="menu">MENU</div>
        <div class="manage">MANAGE</div>
        <div class="admin">ADMIN</div>
      </div>
      <div class="navbar-right">
        <div class="order-now" @click="goBack">Go Back</div>
      </div>
    </nav>
  </Header>

</template>

<style scoped>
  header {
  flex: 3; /* 주문 관리 컴포넌트가 왼쪽에 넓게 설정 */
  padding: 20px; /* 여백 설정 */
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

  .logo, .menu, .manage, .admin, .order-now {
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
</style>