<template>
    <div>
      <section id="profile-info">
        <div class="image-container">
          <a href="#" class="image-placeholder">
            <img class="profile-image" src="/images/avatar-image.avif" alt="Profile Image" />
          </a>
        </div>
        <div class="profile-name"><b>{{ user.username }}</b></div>
      </section>
  
      <section id="book-container">
        <h1><b>Reviews</b></h1> 
        <div class="book-wrapper">
          <div class="book-list">
            <div class="book-card" v-for="review in visibleReviews" :key="review.bookId">
              <div class="image-container">
                <img class="book-image" src="/images/books.jpg" alt="Book Image" />
                <div class="book-info">
                  <span class="book-title">{{ review.bookTitle }}</span>
                  <p class="book-desc">{{ review.text }}</p>
                </div>
              </div>
              <a :href="'http://127.0.0.1:5500/frontend/book.html?id=' + review.bookId">
                <button class="book-button">Read more</button>
              </a>
            </div>
          </div>
        </div>
        <button class="more-books" @click="loadMoreReviews">Mais livros</button>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    name: 'BookReview',
    data() {
      return {
        user: {
          id: this.$route.query.user_id || '',
          username: 'Usuário'
        },
        reviews: [],
        visibleCount: 5,
      };
    },
    computed: {
      visibleReviews() {
        return this.reviews.slice(0, this.visibleCount);
      },
    },
    methods: {
      listUserReviews(accessToken) {
        fetch(`http://localhost:8080/users/${this.user.id}/reviews`, {
          headers: {
            'Authorization': accessToken
          }
        })
        .then(response => response.json())
        .then(data => {
          this.reviews = data;
        })
        .catch(error => console.error("Erro ao carregar reviews:", error));
      },
      loadMoreReviews() {
        this.visibleCount += 5;
      }
    },
    created() {
      const accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJibGEiLCJ1c2VyX2lkIjoiZTg3NzUzZTQtOTBhZS00MTUwLWI5MzAtMDViYzQ2NWEzMTdkIiwiaWF0IjoxNzI5ODE4MTA0LCJleHAiOjE3MzA0MjI5MDR9.VgcLkbvyeLToz2tIm50SOBlR2JfHyL1m6MqRkmnxy0M";
      if (this.user.id) {
        this.listUserReviews(accessToken);
      } else {
        console.warn("ID do usuário não encontrado na rota.");
      }
    },
  };
  </script>
  
  
  <style>
  @import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');
  
  * {
    font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
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
  
  #book-container {
    padding-top: 0;
  }
  
  h1 {
    color: #0d0d0d;
    font-weight: bold;
    margin-bottom: 24px;
  }
  
  #profile-info {
    padding-bottom: 10px;
  }
  
  #profile-info .image-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .profile-image {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
  }
  
  .profile-name {
    font-size: xx-large;
    margin-top: 20px;
    color: #0d0d0d;
    text-align: center;
  }
  
  .book-wrapper {
    position: relative;
    overflow: hidden;
  }
  
  .book-list {
    display: grid;
    padding: 20px 0;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 30px;
  }
  
  .book-card {
    position: relative;
    width: 220px;
    display: flex;
    flex-direction: column;
    gap: 12px;
    margin: 0 auto;
  }
  
  .book-card .image-container {
    position: relative;
    width: 100%;
    height: 300px;
  }
  
  .book-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  
  .book-info {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.7);
    color: #fff;
    opacity: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 10px;
    text-align: center;
    transition: opacity 0.3s ease;
  }
  
  .book-card:hover .book-info {
    opacity: 1;
  }
  
  .book-title {
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 8px;
    line-height: 1.2;
  }
  
  .book-desc {
    font-size: 12px;
    line-height: 1.3;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 6;
    -webkit-box-orient: vertical;
  }
  
  .book-button {
    width: 100%;
    padding: 8px 16px;
    background-color: #0d0d0d;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  
  .book-button:hover {
    background-color: #333;
    transform: translateY(-2px);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  }
  
  .book-button:active {
    transform: translateY(0);
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
  }
  
  .more-books {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #0d0d0d;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease;
    display: block;
    margin: 20px auto 0;
  }
  
  .more-books:hover {
    background-color: #333;
    transform: translateY(-2px);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  }
  
  @media (max-width: 768px) {
    section {
      padding: 20px 4%;
    }
  
    .book-list {
      grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
      gap: 40px;
    }
  
    .book-card {
      width: 130px;
    }
  
    .book-card .image-container {
      width: 130px;
      height: 180px;
    }
  }
  </style>