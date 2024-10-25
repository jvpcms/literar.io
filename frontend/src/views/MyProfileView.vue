<template>
  <div class="custom-background">
    <div id="profile">
      <div class="profile-settings-container">
        <div class="profile-picture-section">
          <img
            src="/images/avatar-image.avif"
            alt="Profile Picture"
            class="profile-picture"
          >
          <button class="btn btn-upload">Change Picture</button>
        </div>
        <div class="profile-info-section">
          <form @submit.prevent="saveChanges">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="profile.username" placeholder="New username" class="input">
            <div class="form-divider"></div>
            <label for="password">New password</label>
            <input type="password" id="password" v-model="profile.password" placeholder="New password" class="input">
            <label for="confirm-password">Confirm password</label>
            <input type="password" id="confirm-password" v-model="profile.confirmPassword" placeholder="Confirm your new password" class="input">
            <button type="submit" class="btn btn-save">Save changes</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'ProfileView',
  data() {
    return {
      profile: {
        username: '',
        email: '',
        description: '',
        password: '',
        confirmPassword: ''
      }
    };
  },
  methods: {
    saveChanges() {
      console.log('Changes saved', this.profile);
    }
  },
  mounted() {
    document.body.style.backgroundColor = '#f0f4f8';
    document.documentElement.style.backgroundColor = '#f0f4f8';

    ScrollReveal().reveal('#profile', { origin: 'right', duration: 2000, distance: '20%' });
  },
  beforeUnmount() {
    document.body.style.backgroundColor = '';
    document.documentElement.style.backgroundColor = '';
  }
};
</script>
<style>
/* Global styles */
:root {
  --primary-color: #4CAF50;
  --background-color: #f0f4f8;
  --header-color: #333;
}

body 
.profile-settings-container {
  display: flex;
  background-color: white;
  min-height: 235px;
  border-radius: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08);
  width: 800px;
  padding: 40px;
  margin-top: 20px; 
}
.custom-background {
  display: flex; 
  justify-content: center; 
  align-items: center; 
  min-height: 100vh; 
}

.profile-settings-container {
  display: flex;
  background-color: white;
  min-height: 235px;
  border-radius: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08);
  width: 800px;
  padding: 40px;
  margin-top: 20px; 
}

.profile-picture-section {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-right: 40px;
  border-right: 1px solid #e0e0e0;
}

.profile-picture {
  width: 180px;
  height: 180px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 20px;
  border: 4px solid white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.profile-info-section {
  flex: 2;
  padding-left: 40px;
}

.profile-info-section h2 {
  margin-bottom: 30px;
  color: #333;
  font-size: 24px;
  font-weight: 600;
}

#description {
  height: 110px;
  resize: vertical;
}

form label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #555;
}

form .input,
.description-input {
  width: 100%;
  padding: 12px;
  margin-bottom: 16px;
  border-radius: 12px;
  border: 1.5px solid #e0e0e0;
  outline: none;
  transition: all 0.3s ease;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
  background-color: white;
}

form .input:hover,
.description-input:hover {
  border-color: var(--primary-color);
}

form .input:focus,
.description-input:focus {
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.1);
}

.btn {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.btn-upload {
  background-color: white;
  color: var(--primary-color);
  border: 2px solid var(--primary-color);
  margin-top: 10px;
}

.btn-upload:hover {
  background-color: var(--primary-color);
  color: white;
}

.btn-save {
  background-color: var(--primary-color);
  color: white;
  margin-top: 20px;
}

.btn-save:hover {
  background-color: #45a049;
  transform: translateY(-1px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form-divider {
  height: 1px;
  background-color: #e0e0e0;
  margin: 20px 0;
}
</style>
