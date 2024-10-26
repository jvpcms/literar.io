import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/IndexView.vue'

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
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue')
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
  ],
  scrollBehavior(to, from, savedPosition) {
    if (to.name === 'book') {
      return { left: 0, top: 0 };
    }
    if (savedPosition) {
      return savedPosition;
    } else {
      return { left: 0, top: 0 };
    }
  }
})

export default router
