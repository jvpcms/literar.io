<script setup>

const emit = defineEmits(["authEvent"])

function emitRegister() {
  emit("authEvent", "register")
}
</script>

<template>
  <h1>This is an register page</h1>
  <button @click="emitRegister">Register</button>
</template>

<style></style>
