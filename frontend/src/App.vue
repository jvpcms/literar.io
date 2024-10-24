<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'

const router = useRouter()
const loggedIn = ref(false)

function handleAuthEvent(authEvent: string) {
  if (authEvent === 'login' || authEvent === 'register') {
    loggedIn.value = true
    router.push('/profile')
  }
  else if (authEvent === 'logout') {
    loggedIn.value = false
    router.push('/')
  }
}

</script>

<template>
  <header>

    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink v-if="!loggedIn" to="/login">Login</RouterLink>
        <RouterLink v-if="!loggedIn" to="/register">Register</RouterLink>
        <RouterLink v-else to="/profile">Profile</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView @authEvent="handleAuthEvent" />
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');

* {
  font-family: Roobert,-apple-system,BlinkMacSystemFont,"Segoe UI",Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
}

body {
  background-color: #eff0f3;
}

section {
  padding: 28px 8%;
}

footer {
  background-color: #c6c6c6;
}

.section-title {
  color: #ff8e3c;
  font-size: 1.56rem;
}

.section-subtitle {
  font-size: 2.8175rem;
}

.btn {
  appearance: none;
  background-color: #0d0d0d;
  border: 0.125em solid #0d0d0d;
  border-radius: 0.9375em;
  box-sizing: border-box;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert,-apple-system,BlinkMacSystemFont,"Segoe UI",Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
  font-size: 16px;
  font-weight: 600;
  margin: 0;
  height: 45px;
  min-width: 0;
  padding: 0 2em;
  text-align: center;
  align-items: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;
}

.btn:disabled {
  pointer-events: none;
}

.btn:hover {
  color: #0d0d0d;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.btn:active {
  box-shadow: none;
  transform: translateY(0);
}

.input {
  width: 100%;
  height: 45px;
  padding: 12px;
  margin: 5px;
  border-radius: 12px;
  border: 1.5px solid lightgrey;
  outline: none;
  transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
  box-shadow: 0px 0px 20px -18px;
}

.input:hover {
  border: 2px solid lightgrey;
  box-shadow: 0px 0px 20px -17px;
}

.input:active {
  transform: scale(0.99);
}

.input:focus {
  border: 2px solid grey;
}
</style>
