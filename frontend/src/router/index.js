import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/manager',
    name: 'manager',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManagerHome.vue')
  },
  {
    path: '/manager/ordercheck',
    name: 'ordercheck',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManagerOrderHomeView.vue')
  },
  {
    path: '/manager/menu',
    name: 'menu',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManageMenu.vue')
  },
  {
    path: '/manager/menu/add',
    name: 'menuadd',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManageMenuAdd.vue')
  },
  {
    path: '/order',
    name: 'order',
    component: () => import(/* webpackChunkName: "about" */ '../views/OrderView.vue')
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
