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

const homeButtonClass = ref('nav-item active')
const catalogButtonClass = ref('nav-item')
const reviewsButtonClass = ref('nav-item')

function handleScroll(event: Event) {
  const target = event.currentTarget as HTMLElement
  const id = target.id

  if (id === 'home') {
    homeButtonClass.value = 'nav-item active'
    catalogButtonClass.value = 'nav-item'
    reviewsButtonClass.value = 'nav-item'

    /* TODO: scroll to section */
  }
  else if (id === 'catalog') {
    homeButtonClass.value = 'nav-item'
    catalogButtonClass.value = 'nav-item active'
    reviewsButtonClass.value = 'nav-item'

    /* TODO: scroll to section */
  }
  else if (id === 'reviews') {
    homeButtonClass.value = 'nav-item'
    catalogButtonClass.value = 'nav-item'
    reviewsButtonClass.value = 'nav-item active'

    /* TODO: scroll to section */
  }
}

</script>

<template>
  <header>

    <div class="wrapper">
      <div class="left-container">
        <a><i class="fa-solid fa-book"> <span id="nav_logo">Literar.io</span></i></a>
      </div>

      <div class="center-container">
        <ul id="nav_list">
          <li :class="homeButtonClass" id="home" @click="handleScroll">
            <RouterLink class="btn" to="/">
              Home
            </RouterLink>
          </li>
          <li :class="catalogButtonClass" id="catalog" @click="handleScroll">
            <!-- TODO: usar routerlink! -->
            <a>Catalog</a>
          </li>
          <li :class="reviewsButtonClass" id="reviews" @click="handleScroll">
            <!-- TODO: usar routerlink! -->
            <a>Reviews</a>
          </li>
        </ul>
      </div>

      <div class="right-container">

        <nav id="nav-account">
          <RouterLink class="btn" to="/login" v-if="!loggedIn">
            Sign in
          </RouterLink>

          <RouterLink class="btn" to="/register" v-if="!loggedIn">
            Register
          </RouterLink>

          <RouterLink class="btn" to="/profile" v-else>
            Profile
          </RouterLink>
        </nav>

      </div>
    </div>
  </header>

  <RouterView @authEvent="handleAuthEvent" />
</template>

<style scoped>
header {
  width: 100vw;
  position: sticky;
  padding: 28px 0;
  margin: 0;
  top: 0;
  background: linear-gradient(to bottom, #eff0f3, #e3e4e7);
  z-index: 3;

  display: flex;
  justify-content: center;
}

.wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin: 0;
  padding: 0 8%
}

.left-container,
.right-container {
  flex: 0 0 300px;
  /* Ensure the left and right containers don't shrink */
}

.right-container {
  display: flex;
  justify-content: flex-end;
}

.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex: 1;
  /* Allow the center container to take up remaining space */
}

#nav_logo {
  font-family: Roobert, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 30px;
  color: #262626;
}

.fa-solid {
  color: #0d0d0d;
  font-size: 30px;
}

#nav_list {
  display: flex;
  list-style: none;
  gap: 48px;
}

.nav-item a {
  text-decoration: none;
  color: #0d0d0d;
  font-weight: 600;
}

.nav-item.active a {
  color: #0d0d0d;
  border-bottom: 3px solid #ff8e3c;
}

#nav-account {
  display: flex;
  justify-content: space-between;
}

#nav-account .btn {
  appearance: none;
  background: linear-gradient(#ff7b1d, #ff8e3c);
  border: 0.125em;
  border-radius: 0.9375em;
  box-sizing: border-box;
  color: #1A1A1A;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 16px;
  font-weight: 600;
  margin: 2px;
  height: 45px;
  min-width: 0;
  padding: 0 2em;
  text-align: center;
  line-height: 45px;
  text-decoration: none;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;
}

#nav-account .btn:disabled {
  pointer-events: none;
}

#nav-account .btn:hover {
  color: #fff;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

#nav-account .btn:active {
  box-shadow: none;
  transform: translateY(0);
}

h1.logo {
  font-size: 1.5rem;
  margin: 0;
  color: red;
  background: blue;
}


/* @media (min-width: 1024px) {
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
} */
</style>
