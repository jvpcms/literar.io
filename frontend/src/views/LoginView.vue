<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const emit = defineEmits(["authEvent"])

const loginEndpoint = 'http://localhost:8080/auth/login'
const router = useRouter()

const username = ref('')
const password = ref('')

// checks
const invalid = ref(false)
const errorMessage = ref('No errors')

function emitLogin() {
  emit("authEvent", "login")
}

function fetchLogin() {

  let payload = {
    username: username.value,
    password: password.value
  }

  fetch(loginEndpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(payload),
  })
    .then(response => {
      return response.json()
    })
    .then(responseBody => {
      if (responseBody.ok === 'false') {
        errorMessage.value = responseBody.message
        invalid.value = true
      }
      else {
        localStorage.setItem('literarioToken', responseBody.token)
        emitLogin()
        console.log(localStorage.getItem('literarioToken'))
      }
    })
    .catch(error => {
      console.error('Error:', error)
    })

  // emit("authEvent", "register")

  function checkResponse() {

  }
}

function submitLogin(event: Event) {
  event.preventDefault()

  invalid.value = false

  if (invalid.value === false) {
    fetchLogin()
  }
}
</script>

<template>
  <div class="parent">
    <div class="container">
      <div class="heading">Sign In</div>
      <form @submit.prevent="submitLogin" class="form">
        <button class="back-btn" @click="() => { router.push('/') }">Cancel</button>
        <input v-model="username" required class="input" type="username" name="username" id="username"
          placeholder="Username">
        <input v-model="password" required class="input" type="password" name="password" id="password"
          placeholder="Password">
        <p v-if="invalid" id="error">{{ errorMessage }}</p>
        <input class="login-button" type="submit" value="Sign In">
      </form>
    </div>
  </div>
</template>

<style>
.parent {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
}

.container {
  position: relative;
  max-width: 350px;
  background: #F8F9FD;
  background: linear-gradient(0deg, #eff0f3 0%, #fffffe 100%);
  border-radius: 40px;
  padding: 25px 35px;
  box-shadow: #7b7b7b 0px 30px 30px -20px;
  margin: 20px;
  position: relative;
}

.heading {
  text-align: center;
  font-weight: 900;
  font-size: 30px;
  color: #434343;
}

.form {
  margin-top: 20px;
}

.form .back-btn {
  position: absolute;
  top: 0;
  left: 0;
  margin-left: 35px;
  margin-top: 21px;
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
  height: 45px;
  width: 18%;
  padding: 1%;
  line-height: 10px;
  text-decoration: none;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;

}

.form .back-btn:disabled {
  pointer-events: none;
}

.form .back-btn:hover {
  color: #fff;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.form .back-btn:active {
  box-shadow: none;
  transform: translateY(0);
}

.form .login-button {
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
  height: 45px;
  margin-top: 10%;
  width: 100%;
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

.form .login-button:disabled {
  pointer-events: none;
}

.form .login-button:hover {
  color: #fff;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.form .login-button:active {
  box-shadow: none;
  transform: translateY(0);
}

.input {
  width: 100%;
  padding: 12px;
  margin: 5px;
  border-radius: 12px;
  border: 1.5px solid lightgrey;
  outline: none;
  transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
  box-shadow: 0px 0px 20px -18px;
}

#error {
  color: red;
  text-align: center;
}
</style>
