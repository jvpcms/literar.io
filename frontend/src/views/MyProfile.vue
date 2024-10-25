<template>
  <div>
    <section id="profile-info">
      <div class="image-container">
        <a href="#" class="image-placeholder">
          <img class="profile-image" src="/images/avatar-image.avif" alt="Profile Image" />
        </a>
      </div>
      <div class="profile">
        <a href="#" class="profile-name"><b>{{ user.username }}</b></a>
      </div>
    </section>

    <section id="book-container">
      <h1><b>Reviews</b></h1> 
      <div class="book-wrapper">
        <div class="book-list">
          <div class="book-card" v-for="book in visibleBooks" :key="book.id">
            <div class="image-container">
              <img class="book-image" src="/images/books.jpg" alt="Book Image" />
              <div class="book-info">
                <span class="book-title">{{ book.title }}</span>
                <p class="book-desc">{{ book.description }}</p>
              </div>
            </div>
            <a :href="'http://127.0.0.1:5500/frontend/book.html?id=' + book.id">
              <button class="book-button">Read more</button>
            </a>
          </div>
        </div>
      </div>
      <button class="more-books" @click="loadMoreBooks">Mais livros</button>
    </section>
  </div>
</template>


<script>
export default {
  data() {
    return {
      user: {},
      books: [],
      visibleCount: 4,
    };
  },
  computed: {
    visibleBooks() {
      return this.books.slice(0, this.visibleCount);
    },
  },
  methods: {
    async fetchUserProfile(userId) {
      const response = await fetch("http://localhost:8080/users/${userId}");
      if (response.ok) {
        this.user = await response.json();
        this.fetchUserReviews(userId);
      } else {
        console.error('Erro ao buscar o perfil do usuário:', response.status);
      }
    },
    async fetchUserReviews(userId) {
      const response = await fetch("http://localhost:8080/users/${userId}/reviews");
      if (response.ok) {
        this.books = await response.json();
      } else {
        console.error('Erro ao buscar reviews do usuário:', response.status);
      }
    },
    loadMoreBooks() {
      this.visibleCount += 4;
    },
    getUserIdFromToken() {
      const token = localStorage.getItem('userToken'); 
      if (token) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.userId; 
      }
      return null;
    },
  },
  mounted() {
    const userId = this.getUserIdFromToken(); 
    if (userId) {
      this.fetchUserProfile(userId);
    } else {
      console.error('Usuário não está autenticado.');
    }
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');

* {
  font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica,
    Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
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

h1 {
  color: #0d0d0d; /* Define a cor como preto */
  font-weight: bold; /* Garante que o texto ficará em negrito */
}

.image-container {
  margin-bottom: 60px; /* Ajuste este valor conforme necessário */
  display: flex;
  justify-content: center; /* Centraliza a imagem no container */
}

.profile-image {
  width: 200px; /* Ajuste a largura para uma imagem menor */
  height: 200px; /* Ajuste a altura para uma imagem menor */
  border-radius: 50%; /* Mantém a imagem em formato circular */
  margin-top: 20vh;
}
#profile-info {
  display: flex;
  flex-direction: column; /* Alinha verticalmente */
  align-items: center; /* Centraliza horizontalmente */
  margin-top: 3%;
}

#book-container {
  margin-top: 10%;
}

.btn {
  appearance: none;
  background-color: #0d0d0d;
  border: 0.125em solid #0d0d0d;
  border-radius: 0.9375em;
  box-sizing: border-box;
  color: #ffffff;
  cursor: pointer;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica,
    Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  font-size: 16px;
  font-weight: 600;
  height: 45px;
  padding: 0 2em;
  text-align: center;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
}

.btn:hover {
  color: #0d0d0d;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}


.image-placeholder {
  width: 10px; /* Ajuste a largura para uma imagem menor, se necessário */
  height: 10px; /* Ajuste a altura para uma imagem menor, se necessário */
  border-radius: 50%; /* Mantém a imagem em formato circular */
  display: flex; /* Centraliza a imagem dentro do placeholder */
  justify-content: center;
  align-items: center;
  background-color: #e0e0e0;
  box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
}

.profile-name {
  font-size: xx-large;
  margin-top: 80px; /* Aumente o espaço entre a imagem e o nome */
  text-decoration: none;
  color: #0d0d0d;
}

.book-title {
  font-size: 32px;
  font-weight: bold;
  position: relative; /* Mudei de absolute para relative */
  margin-top: 10px; /* Adiciona um espaço acima do título */
  color: #0d0d0d; /* Define a cor como preto */
}

.book-desc {
  font-size: 14px; /* Tamanho da fonte para a descrição */
  color: #0d0d0d; /* Define a cor como preto */
  opacity: 1; /* Remove a transparência */
  margin-top: 5px; /* Espaçamento acima da descrição */
}

.book-wrapper {
  position: relative;
  overflow: hidden;
}

.book-list {
  display: flex;
  flex-wrap: wrap; /* Permite que os cards se ajustem na linha */
  gap: 20px; /* Espaçamento entre os cards */
}

.book-card {
  flex: 0 0 calc(25% - 20px); /* Exibe 4 livros por linha */
  text-align: center;
  display: flex;
  flex-direction: column;
}

.more-books {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #0d0d0d;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.more-books:hover {
  background-color: #333; /* Muda a cor do botão ao passar o mouse */
}
</style>