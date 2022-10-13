import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'
import About from '../views/AboutView.vue'
import Product from "@/views/Product";
import ProductDetail from "@/views/ProductDetail";


const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: Home
  },
  {
    path: '/product',
    name: 'Product',
    component: Product
  },
  {
    path: '/detail/:id',
    name: 'ProductDetail',
    component: ProductDetail
  },

  {
    path: '/aboutView',
    name: 'About',
    component: About
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router