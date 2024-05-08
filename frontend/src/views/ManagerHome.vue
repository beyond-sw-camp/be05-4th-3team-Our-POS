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
        <div class="order-now" @click="goToHome">GO BACK</div>
      </div>
    </nav>

    <!-- 매장 관리자 메뉴 -->
    <div class="manager-menu">
      <!-- 매장 이미지 -->
      <img src="https://www.nofuss.me/wp-content/uploads/2021/03/Interior_3.jpg" alt="매장 이미지" class="store-image">
      <!-- 매장 정보 테이블 -->
      <div>
        <table class="store-table">
          <tr>
            <th>매장명/주소</th>
            <td>맥도날드 서울역점 / 서울특별시 용산구...</td>
          </tr>
          <tr>
            <th>전화번호</th>
            <td>010-1234-5678</td>
          </tr>
          <tr>
            <th>영업시간</th>
            <td>오전 10시 ~ 오후 10시</td>
          </tr>
          <tr>
            <th>이용가능 서비스</th>
            <td>
              <!-- 이미지 표시를 위한 빈 요소 -->
              <div class="service-images">
                <img src="https://www.mcdonalds.co.kr/upload/main/store_service/1580887217994.png" alt="Service 1">
                <img src="https://www.mcdonalds.co.kr/upload/main/store_service/1580887371824.png" alt="Service 2">
                <img src="https://www.mcdonalds.co.kr/upload/main/store_service/1580887410227.png" alt="Service 3">
                <img src="https://www.mcdonalds.co.kr/upload/main/store_service/1580887307761.png" alt="Service 4">
                <img src="https://www.mcdonalds.co.kr/upload/main/store_service/1580887501964.png" alt="Service 5">
                <!-- 필요한 만큼 이미지를 추가 -->
              </div>
            </td>
          </tr>

        </table>
      </div>
      <div class="margin-between"></div>

      <div class="landing-buttons">
        <!-- 메뉴 관리 버튼 -->
        <button @click="goToMenuPage" class="menu-btn">메뉴관리</button>
        <!-- 주문 관리 버튼 -->
        <button @click="goToOrderNow" class="orders-btn">주문관리</button>
      </div>

    </div>
  </div>
</template>

<script>
// import { useRouter } from 'vue-router'

// const router = useRouter();

export default {

  methods: {
    goToHome() {
      this.$router.push({ name: "home" });
    },
    goToOrderNow() {
      // ORDER NOW 버튼 클릭 시 처리할 내용
      this.$router.push({ name: "ordercheck" });
    },
    goToMenuPage() {
      this.$router.push({ name: "menu" });
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
/* 매장 정보 스타일 */
.store-info {
  margin-top: 20px;
  text-align: center;
}
.store-image-container {
  margin-bottom: 20px;
}
.store-image {
  width: 100%;
  max-width: 600px;
  height: auto;
}
/* 테이블과 버튼 사이 여백 */
.margin-between {
  margin-bottom: 30px;
}
.store-table {
  width: 65%; /* 테이블의 너비를 조절 */
  margin: 0 auto; /* 가운데 정렬을 위해 margin 설정 */
  border-collapse: collapse;
  margin-top: 20px;
}
.store-table th,
.store-table td {
  border: 1px solid #ddd;
  padding: 10px;
}
.store-table th {
  background-color: #f2f2f2;
}
.store-table td {
  text-align: left;
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
  border: 3px solid rgb(255, 166, 0); /* 원형 테두리를 주황색으로 설정 */
}

/* 매장 관리자 메뉴 스타일 */
.manager-menu {
  text-align: center;
  margin-top: 20px;
  position: relative;
}
.store-image-container {
  position: absolute; /* 사진을 절대 위치로 설정 */
  top: 0; /* 부모 요소의 맨 위에 위치 */
  left: 50%; /* 가운데 정렬 */
  transform: translateX(-50%); /* 가운데 정렬 */
  z-index: -1; /* 버튼 위에 사진이 위치하도록 설정 */
}

.store-image {
  width: 100%; /* 이미지 너비를 100%로 설정하여 부모 요소의 너비에 맞춤 */
  height: auto; /* 이미지 높이를 자동으로 조절하여 가로세로 비율을 유지함 */
  max-width: 800px; /* 최대 너비를 설정하여 너비가 너무 커지지 않도록 제한 */
  max-height: 400px; /* 최대 높이를 설정하여 높이가 너무 커지지 않도록 제한 */
  margin-bottom: 20px;
}
.landing-buttons {
  display: flex;
  justify-content: center;

}

.menu-btn, .orders-btn {

  margin-top: 20px;
  display: inline-block;
  padding: 40px 40px;
  margin-right: 10px;
  background-color: #ff8800;
  color: white;
  text-decoration: none;
  border-radius: 20px;;
}

.menu-btn:hover, .orders-btn:hover {
  background-color: #5d5f62;
}
</style>