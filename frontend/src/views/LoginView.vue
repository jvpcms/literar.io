<script setup>

const emit = defineEmits(["authEvent"])

function emitLogin() {
  emit("authEvent", "login")
}
</script>

<template>
  <h1>This is an login page</h1>
  <button @click="emitLogin">Login</button>
</template>

<style></style>
