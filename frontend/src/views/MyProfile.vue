<!-- src/components/MyProfile.vue -->
<script setup lang="ts">
import { ref } from 'vue'

const emit = defineEmits(['authEvent'])

const profileImage = ref('src/images/avatar-image.avif')
const formData = ref({
  username: '',
  email: '',
  description: '',
  password: '',
  confirmPassword: ''
})

const fileInput = ref<HTMLInputElement | null>(null)

const handleImageChange = () => {
  fileInput.value?.click()
}

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement
  if (target.files && target.files[0]) {
    const reader = new FileReader()
    reader.onload = (e) => {
      if (e.target?.result) {
        profileImage.value = e.target.result as string
      }
    }
    reader.readAsDataURL(target.files[0])
  }
}

const handleSubmit = () => {
  if (formData.value.password !== formData.value.confirmPassword) {
    alert('As senhas não correspondem!')
    return
  }
  console.log('Dados do formulário:', formData.value)
}

const handleLogout = () => {
  emit('authEvent', 'logout')
}
</script>

<template>
  <div id="profile">
    <div class="profile-settings-container">
      <div class="profile-picture-section">
        <img :src="profileImage" alt="Foto do Perfil" class="profile-picture">
        <button class="btn" @click="handleImageChange">Alterar Foto</button>
        <input 
          type="file" 
          ref="fileInput" 
          @change="onFileChange" 
          style="display: none"
          accept="image/*"
        >
        <button class="btn btn-logout" @click="handleLogout">Logout</button>
      </div>

      <div class="profile-info-section">
        <h2>Profile settings</h2>
        <form @submit.prevent="handleSubmit">
          <label for="username">Username</label>
          <input 
            type="text" 
            id="username" 
            v-model="formData.username" 
            placeholder="New username" 
            class="input"
          >
      
          <label for="email">E-mail</label>
          <input 
            type="email" 
            id="email" 
            v-model="formData.email" 
            placeholder="New e-mail" 
            class="input"
          >
      
          <label for="description">Description</label>
          <textarea 
            id="description" 
            v-model="formData.description" 
            placeholder="Sua nova descrição" 
            rows="6" 
            class="description-input"
          ></textarea>
      
          <div class="form-divider"></div>
      
          <label for="password">New password</label>
          <input 
            type="password" 
            id="password" 
            v-model="formData.password" 
            placeholder="Nova senha" 
            class="input"
          >
      
          <label for="confirm-password">Confirm password</label>
          <input 
            type="password" 
            id="confirm-password" 
            v-model="formData.confirmPassword" 
            placeholder="Confirme sua nova senha" 
            class="input"
          >
      
          <button type="submit" class="btn">Save changes</button>
        </form>
      </div>   
    </div>
  </div>
</template>

<style scoped>
#profile {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 100px);
  padding: 20px;
}

.profile-settings-container {
  display: flex;    
  background-color: #fff;
  min-height: 625px;
  border-radius: 10%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 800px;
  padding: 20px;
}

.profile-picture-section {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-right: 20px;
  border-right: 1px solid #ccc;
}

.profile-picture {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 10px;
}

.profile-info-section {
  flex: 2;
  padding-left: 20px;
}

.profile-info-section h2 {
  margin-bottom: 20px;
}

#description {
  height: 110px;
}

#confirm-password {
  margin-bottom: 15px;
}

form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

form .input, .description-input {
  width: 92%;
  padding: 8px;
  margin-bottom: 3px;
  border-radius: 12px;
  border: 1.5px solid lightgrey;
  outline: none;
  transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
  box-shadow: 0px 0px 20px -18px;
}

form .description-input {
  resize: vertical;
}

form .input:hover, .description-input:hover {
  border: 2px solid lightgrey;
  box-shadow: 0px 0px 20px -17px;
}

form .input:active, .description-input:active {
  transform: scale(0.95);
}

form .input:focus, .description-input:focus {
  border: 2px solid grey;
}

.btn {
  width: 100%;
  max-width: 200px;
  padding: 8px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin: 5px 0;
}

.btn-logout {
  background-color: #dc3545;
  margin-top: 20px;
}

.btn:hover {
  opacity: 0.9;
}
</style>