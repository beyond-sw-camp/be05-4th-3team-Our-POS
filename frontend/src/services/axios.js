import axios from 'axios';

// Axios 인스턴스 생성
const axiosInstance = axios.create({
    // baseURL: 'http://43.201.111.166:8080/', // API의 기본 URL 설정
});

export default axiosInstance;