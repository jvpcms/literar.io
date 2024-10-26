<template>
    <div v-if="isDataLoaded">
      <!-- Exibir informações do perfil -->
      <section id="profile-info">
        <div class="image-container">
          <a href="#" class="image-placeholder">
            <img class="profile-image" :src="reviewerImage" alt="Profile Image" />
          </a>
        </div>
        <div v-if="user.username" class="profile-name"><b>{{ user.username }}</b></div>
      </section>
  
      <!-- Exibir reviews -->
      <section id="book-container">
        <h1><b>Reviews</b></h1>
        <div class="book-wrapper">
          <div class="book-list">
            <div class="book-card" v-for="review in visibleReviews" :key="review.id">
              <div class="image-container">
                <img class="book-image" src="/images/books.jpg" alt="Book Image" />
                <div class="book-info">
                  <span class="book-title">{{ review.book.title }}</span>
                  <p class="book-desc">{{ review.text }}</p>
                </div>
              </div>
              <a @click.prevent="goToBookView(review.book.bookId)">
                <button class="book-button">Read more</button>
              </a>
            </div>
          </div>
        </div>
        <button class="more-books" @click="loadMoreReviews">Mais livros</button>
      </section>
    </div>
    <div v-else>
      <p>Carregando informações do usuário...</p>
    </div>
</template>

<script lang="ts">
export default {
    data(this: any): {
    user: {
        id: string;
        username: string;
    };
    reviewList: Array<{ 
        id: string;
        text: string;
        userId: string;
        book: {
            title: string;
            bookId: string;
            authorId: string;
            synopsis: string;
        };
    }>;
    reviewerImage: string;
    visibleReviews: Array<{ 
        id: string;
        text: string;
        book: {
            title: string;
            bookId: string;
            authorId: string;
            synopsis: string;
        };
    }>;
    bookImage: string;
    isDataLoaded: boolean;
    visibleCount: number;
} {
    return {
        user: {
            id: '',
            username: ''
        },
        reviewList: [{
            id: '',
            text: '',
            userId: '',
            book: {
                title: '',
                bookId: '',
                authorId: '',
                synopsis: '',
            }
        }],
        reviewerImage: 'src/images/avatar-image.avif',
        visibleReviews: [{
            id: '',
            text: '',
            book: {
                title: '',
                bookId: '',
                authorId: '',
                synopsis: '',
            }
        }],
        bookImage: 'src/images/books.jpg',
        isDataLoaded: false,
        visibleCount: 5 // Inicialmente mostra 5 reviews
    };
},
    methods: {
        getUserAndReviews() {
            fetch("http://localhost:8080/users/info/" + this.$route.query.id, {
                headers: { 
                    'authentication': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJURVNURSIsInVzZXJfaWQiOiJmMThiNWRlMi1hNjk0LTQ0ZTktODExOC1jYjZiYmY3Mjg1ZTQiLCJpYXQiOjE3Mjk4OTYzNjQsImV4cCI6MTczMDUwMTE2NH0.wId_D1IPxbah-IRwelk1p6-eWV0qqFTL44nPira7eqw"
                } 
            })
            .then(response => response.json())
            .then(data => {
                this.user = { id: data.id, username: data.username };
                this.listReviews();
            });
        },
        
        listReviews(this: any) {
            fetch("http://localhost:8080/users/" + this.user.id + "/reviews", {
                headers: { 
                    'authentication': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJibGEiLCJ1c2VyX2lkIjoiZTg3NzUzZTQtOTBhZS00MTUwLWI5MzAtMDViYzQ2NWEzMTdkIiwiaWF0IjoxNzI5ODE4MTA0LCJleHAiOjE3MzA0MjI5MDR9.VgcLkbvyeLToz2tIm50SOBlR2JfHyL1m6MqRkmnxy0M"
                }
            })
            .then(response => response.json())
            .then(data => {
                this.reviewList = data;
                this.getBooksForReviews();
            });
        },
        
        getBooksForReviews(this: any) {
            const reviewPromises = this.reviewList.map((review: { bookId: string; text: string }) =>
                fetch("http://localhost:8080/books/info/" + review.bookId, {
                    headers: { 
                        'authentication': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJibGEiLCJ1c2VyX2lkIjoiZTg3NzUzZTQtOTBhZS00MTUwLWI5MzAtMDViYzQ2NWEzMTdkIiwiaWF0IjoxNzI5ODE4MTA0LCJleHAiOjE3MzA0MjI5MDR9.VgcLkbvyeLToz2tIm50SOBlR2JfHyL1m6MqRkmnxy0M"
                    }
                })
                .then(response => response.json())
                .then(bookData => {
                    return {
                        ...review,
                        book: {
                            title: bookData.title,
                            bookId: bookData.id,
                            authorId: bookData.authorId,
                            synopsis: bookData.synopsis,
                            
                        }
                    };
                })
            );
            
            Promise.all(reviewPromises).then(reviewsWithBooks => {
                this.visibleReviews = reviewsWithBooks.slice(0, this.visibleCount);
                this.isDataLoaded = true;
            });
        },
        
        updateVisibleReviews(this: any) {
            this.visibleReviews = this.reviewList.slice(0, this.visibleCount);
        },
        
        loadMoreReviews(this: any) {
            this.visibleCount += 5;
            this.updateVisibleReviews();
        },
        
        goToBookView(this: any, bookId: string) {
            this.$router.push({
                name: 'book',  // Nome da rota correspondente à página TassView
                path: '/book',  // Caminho da URL que será usado
                query: { 
                    id: bookId, 
            }});
            
        }
    },
    created() {
        this.getUserAndReviews(); // Chama o método ao criar o componente
    }
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
  