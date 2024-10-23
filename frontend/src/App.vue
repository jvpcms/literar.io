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
nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;

  display: flex;
  justify-content: center;
  flex-direction: row;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
