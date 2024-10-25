import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/TesteView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk (Login.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      // component: HomeView
      component: () => import('../views/NewIndexView.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/IndexView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/TassView.vue') // Mudo de ProfileView.vue para MyProfile ?
    },
    {
    path: '/book',
    name: 'book',
    component: () => import('../views/BookView.vue')
    },
    {
    path: '/author',
    name: 'author',
    component: () => import('../views/AuthorView.vue')
    }
    ]
})

export default router
