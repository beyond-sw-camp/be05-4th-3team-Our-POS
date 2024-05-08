<template>
  <div>
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
        <div class="order-now" @click="goToOrderNow">ORDER NOW</div>
      </div>
    </nav>

    <!-- 랜딩 페이지 -->
    <div class="landing">
      <img src="https://www.foodandwine.com/thmb/u491s8vce12ss_XI-Wm6mc02F5k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Shake-Shack-Launching-a-Spicy-New-Hot-Menu-Nationwide-FT-2-BLOG0923-ba15a62d69624e4585ae958e6d52358d.jpg" alt="쉐이크 랜딩 페이지 이미지">

      <div class="landing-text">ORDER NOW!</div>
      <div class="landing-text2">"OUR POS" helps tens of thousands of companies expand globally with unmatched speed,</div>
      <div class="landing-text2">flexibility and compliance. Get our all-in-one Global People Platform that simplifies </div>
      <div class="landing-text2">the way you onboard, offboard, and everything else in between.</div>
      <div class="landing-buttons">
        <button @click="goToManager" class="manager-btn">MANAGER</button>

        <button @click="goToOrderNow" class="order-btn">ORDER</button>
      </div>
    </div>
  </div>
</template>

<script>
// import { useRouter } from 'vue-router'

// const router = useRouter();

export default {
  methods: {
    goToOrderNow() {
      this.$router.push({ name: "order" });
    },
    goToManager() {
      this.$router.push({ name: "manager" });
    }
  }
}
</script>

<style scoped>
/* 네비게이션 바 스타일 */
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

/* 랜딩 페이지 스타일 */
.landing {
  text-align: center;
  padding: 50px;
  background-color: rgb(247, 241, 206);

}

.landing img {
  width: 100%; /* 이미지 너비를 100%로 설정하여 부모 요소의 너비에 맞춤 */
  height: auto; /* 이미지 높이를 자동으로 조절하여 가로세로 비율을 유지함 */
  max-width: 800px; /* 최대 너비를 설정하여 너비가 너무 커지지 않도록 제한 */
  max-height: 400px; /* 최대 높이를 설정하여 높이가 너무 커지지 않도록 제한 */
  margin-bottom: 20px;
}

.landing-text {
  font-size: 40px;
  font-weight: bold;
  margin-bottom: 20px;
}

.landing-text2 {
  font-size: 17px;
  font-weight: thin;
  margin-bottom: 10px;
}


.landing-buttons {
  display: flex;
  justify-content: center;

}

.user-btn, .manager-btn, .order-btn {
  margin-top: 20px;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 10px;
  background-color: #ff8800;
  color: white;
  text-decoration: none;
  border-radius: 20px;
}

.user-btn:hover, .manager-btn:hover, .order-btn:hover {
  background-color: #5d5f62;
}
</style>