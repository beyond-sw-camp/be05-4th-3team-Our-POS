<template>
  <div style="background-color: lightgray; padding: 50px 0; margin: 0 300px;">
    <h2>주문 처리 현황</h2>
    <table class="order-table">
      <thead>
        <tr>
          <th>메뉴 이름</th>
          <th>메뉴 수량</th>
          <th>총 가격</th>
          <th>주문 상태</th>
        </tr>
      </thead>
      <tbody>
          
          <tr v-for="(orderDetail, index) in orderDetails" :key="index">
              <!-- 데이터가 있는 경우에만 내부 코드를 실행 -->
              <td>
              <!-- orderDetailDtos 배열에서 각 항목의 menuName 값만 표시 -->
              <span v-for="(orderDto, orderIndex) in orderDetail.orderDetailDtos" :key="orderIndex">
                  {{ orderDto.menuName }}
                  <!-- 만약 마지막 항목이 아니면 span을 닫고 새로운 span 열기 -->
                  <span v-if="orderIndex !== orderDetail.orderDetailDtos.length - 1">
                  <span style="display: block;"></span>
                  </span>
              </span>
              </td>
              <td>
              <!-- orderDetailDtos 배열에서 각 항목의 quantity 값만 표시 -->
              <span v-for="(orderDto, orderIndex) in orderDetail.orderDetailDtos" :key="orderIndex">
                  {{ orderDto.quantity }}
                  <!-- 만약 마지막 항목이 아니면 span을 닫고 새로운 span 열기 -->
                  <span v-if="orderIndex !== orderDetail.orderDetailDtos.length - 1">
                  <span style="display: block;"></span>
                  </span>
              </span>
              </td>
              <td>{{ orderDetail.totalPrice }}</td> 
              <td>
              <!-- orderDetailDtos 배열에서 각 항목의 orderStatus 값만 표시 -->
              <span v-for="(orderDto, orderIndex) in orderDetail.orderDetailDtos" :key="orderIndex">
                  {{ orderDto.orderStatus.value }}
                  <!-- 만약 마지막 항목이 아니면 span을 닫고 새로운 span 열기 -->
                  <span v-if="orderIndex !== orderDetail.orderDetailDtos.length - 1">
                  <span style="display: block;"></span>
                  </span>
              </span>
              </td>
          </tr>  
</tbody>
    </table>
    <div class="total-amount">
      <p>총 결제 금액: {{ totalAmount }}</p>
    </div>
    <div class="status-container">
          <div class="status" >WAITING</div>
          <div class="status2" >PROCESSING</div>
          <div class="status3" >COMPLETED</div>
          <div class="status4" >CANCELED</div>
      </div>


    <div class="button-container">
      <button class="complete-btn">주문처리완료</button>
      <button class="reject-btn">주문거절</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
      orderId: {
          type: Number,
          required: true
  }
},
  data() {
    return {
      orderDetails: [],
      totalAmount: 0
    };
  },
  created() {
    // 주문 상세 정보를 가져오는 함수 호출
    this.fetchOrderDetails();
  },
  methods: {
    async fetchOrderDetails() {
      try {
        const response = await axios.get(`http://43.201.111.166:8080/api/orders/${this.orderId}`);
        const data = response.data;
        // 주문 상세 정보를 받아와서 컴포넌트의 데이터에 저장
        this.orderDetails = data;
        
        console.log('Error fetching order details:', data);
        console.log('Error fetching order details:', data.data.orderDetailDtos);
        // 총 결제 금액 계산
        this.totalAmount = data.data.totalPrice
        
        console.log('Error fetching order details:', this.totalAmount);
      } catch (error) {
        console.error('Error fetching order details:', error);
      }
    }
  }
};
</script>

<style>
.order-table {
margin-top: 30px;
width: 98%;
border-collapse: collapse;
margin-left: 1%;
justify-content: center;

}

.order-table th, .order-table td {
  border: 0.5px solid #ffffff;
  padding: 8px;
  text-align: center;
}

.total-amount {
  margin-top: 5px;
  text-align: left; 
  
}
.status-container {
display: flex;
justify-content: center;
margin-top: 30px; /* 테이블과 status-container 사이의 간격을 조절 */
flex-wrap: wrap;
}

.status, .status2, .status3, .status4 {
cursor: pointer;
display: inline-block;
padding: 10px 20px;
margin-right: 10px;
margin-bottom: 10px;

color: rgb(4, 4, 4);
text-decoration: none;

border-radius: 50px; /* 원형 버튼을 만들기 위해 반지름을 50%로 설정 */
border: 3px solid rgb(255, 255, 255); /* 원형 테두리를 주황색으로 설정 */
flex-basis: calc(50% - 100px);
}
.button-container {
text-align: center;
margin-top: 20px;
}

.complete-btn {
padding: 20px 50px;
margin-right: 10px;
background-color: rgb(76, 80, 83);
color: white;
border: none;
border-radius: 5px;
cursor: pointer;
}
.reject-btn {
padding: 20px 50px;
margin-right: 10px;
background-color: rgb(238, 79, 79);
color: white;
border: none;
border-radius: 5px;
cursor: pointer;
}
.order-table th, .order-table td {
border: 0.5px solid #ffffff;
padding: 0; /* 테이블 셀의 내부 여백을 0으로 설정 */
text-align: center;
}
</style>
