<script setup lang="ts">

const emit = defineEmits(["authEvent"])

function emitLogout() {
  emit("authEvent", "logout")
}
</script>

<template>
  <h1>This is an profile page</h1>
  <button @click="emitLogout">Logout</button>
</template>

<style></style>

