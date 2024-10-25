<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
    integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <div id="app">
    <main id="content">
      <!-- Home Section -->
      <section id="home">
        <div id="cta">
          <h1 class="title">Literar.io</h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Est cumque unde ex nulla
            molestias. In voluptas nihil hic, incidunt expedita atque fuga. Accusamus, sunt dolor
            facere aliquid velit architecto iure?
          </p>
        </div>
        <div id="cta-button">
          <input placeholder="Books or Authors" type="text" class="input" v-model="searchQuery" />
          <button class="btn" @click="search">Search</button>
        </div>
      </section>

      <!-- Catalog Section -->
      <section id="book-container">
        <h2 class="section-title">Catalog</h2>
        <h3 class="section-subtitle">Trending books</h3>
        <div class="book-wrapper">
          <div class="book-list">
            <div class="book" v-for="book in filteredBooks" :key="book.id">
              <a @click.prevent="goToBookView(book)">
                <img :src="bookImage" class="book-image" />
              </a>
              <a @click.prevent="goToBookView(book)">{{ book.title }}</a>
              <span class="book-description">{{ book.synopsis }}</span>
              <div class="book-rating">
                <i class="fa-solid fa-star" v-for="n in 5" :key="n" :class="{ active: n <= book.rating }"></i>
                <span>({{ book.reviews }}+)</span>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Reviews Section -->
      <section id="rating">
        <img src="https://img.freepik.com/fotos-gratis/celebracao-do-dia-mundial-do-livro_23-2151245176.jpg" alt=""
          class="rating-image" />

        <div id="rating_content">
          <h2 class="section-title">Reviews</h2>
          <h3 class="section-subtitle">Popular reviews</h3>

          <div id="reviews">
            <div class="review" v-for="review in reviews" :key="review.id">
              <a :href="`/book/${review.bookId}`">
                <img :src="review.bookImage" class="book-image" />
              </a>

              <div class="review-content">
                <a class="book-title" :href="`/book/${review.bookId}`">
                  {{ review.bookTitle }}
                </a>
                <div class="profile">
                  <div class="profile-review">
                    <a :href="`/profile/${review.userId}`" class="profile-info">
                      <img :src="review.userAvatar" class="avatar-image" />
                      {{ review.userName }}
                    </a>
                    <span>
                      <i v-for="n in 5" :key="n" class="fa-solid fa-star" :class="{ active: n <= review.rating }"></i>
                    </span>
                  </div>
                  <p>"{{ review.text }}"</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import ScrollReveal from 'scrollreveal';
export default {
  data() {
    return {
      isMobileMenuActive: false,
      searchQuery: '',
      activeSection: 'home',
      visibleCount: 4,
      bookImage: '/images/books.jpg',
      books: [],
      reviews: [
        {
          id: 1,
          bookId: 1,
          bookImage: '/images/books.jpg',
          bookTitle: 'Book Title',
          userId: 1,
          userAvatar: '/images/avatar-image.avif',
          userName: 'Fulano Sicrano',
          rating: 5,
          text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit.',
        },
        {
          id: 2,
          bookId: 1,
          bookImage: '/images/books.jpg',
          bookTitle: 'Book Title',
          userId: 1,
          userAvatar: '/images/avatar-image.avif',
          userName: 'Fulano Sicrano',
          rating: 5,
          text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit.',
        },
        // ... (outros reviews hardcoded)
      ],
    };
  },
  computed: {
    filteredBooks() {
      return this.books.filter((book) =>
        book.title.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
    visibleBooks() {
      return this.filteredBooks.slice(0, this.visibleCount);
    },
  },
  methods: {
    toggleMobileMenu() {
      this.isMobileMenuActive = !this.isMobileMenuActive;
    },
    navigateTo(page) {
      // Implementar navegação para login ou registro
      console.log(`Navegando para: ${page}`);
    },
    scrollToSection(section) {
      this.activeSection = section;
      const element = document.getElementById(section);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
      }
      this.isMobileMenuActive = false;
    },
    search() {
      // Implementar funcionalidade de busca
      console.log(`Buscando por: ${this.searchQuery}`);
    },
    handleScroll() {
      const sections = ['home', 'catalog', 'rating'];
      for (let section of sections) {
        const element = document.getElementById(section);
        if (element) {
          const rect = element.getBoundingClientRect();
          if (rect.top <= 100 && rect.bottom >= 100) {
            this.activeSection = section;
            break;
          }
        }
      }
    },
    goToBookView(book) {
      this.$router.push({
        name: "BookrView",
        query: {
          authorId: book.authorId,
          bookId: book.id,
          title: book.title,
          synopsis: book.synopsis,
          image: book.image,
          rating: book.rating,
          reviews: book.reviews,
        }
      })
    },
    async fetchBooks() {
      try {
        const response = await fetch('http://localhost:8080/books');
        if (response.ok) {
          const fetchedBooks = await response.json();
          this.books = this.books.concat(fetchedBooks);
        } else {
          console.error('Erro ao buscar livros:', response.statusText);
        }
      } catch (error) {
        console.error('Erro na requisição:', error);
      }
    },
  },
  computed: {
    visibleBooks() {
      return this.books.slice(0, this.visibleCount);
    },
    filteredBooks() {
      return this.books.filter((book) =>
        book.title.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
  created() {
    // Carrega os livros assim que o componente é criado
    this.fetchBooks();
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
    const sr = ScrollReveal();

    sr.reveal('#cta', {
      origin: 'top',
      duration: 2000,
      distance: '20%',
    });

    sr.reveal('#cta-button', {
      origin: 'top',
      duration: 2000,
      distance: '20%',
    });

    sr.reveal('.book', {
      origin: 'right',
      duration: 2000,
      distance: '20%',
    });

    sr.reveal('.rating-image', {
      origin: 'left',
      duration: 2000,
      distance: '20%',
    });

    sr.reveal('.review', {
      origin: 'right',
      duration: 2000,
      distance: '20%',
    });
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');

* {
  font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif,
    'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
  width: 100%;
}

#content {
  background-color: #eff0f3;
}

section {
  padding: 28px 8%;
}

.section-title {
  color: #ff8e3c;
  font-size: 1.56rem;
}

.section-subtitle {
  font-size: 2.8175rem;
}

.btn {
  appearance: none;
  background-color: #0d0d0d;
  border: 0.125em solid #0d0d0d;
  border-radius: 0.9375em;
  box-sizing: border-box;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif,
    'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  font-size: 16px;
  font-weight: 600;
  margin: 0;
  height: 45px;
  min-width: 0;
  padding: 0 2em;
  text-align: center;
  align-items: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;
}

.btn:disabled {
  pointer-events: none;
}

.btn:hover {
  color: #0d0d0d;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.btn:active {
  box-shadow: none;
  transform: translateY(0);
}

.input {
  width: 100%;
  height: 45px;
  padding: 12px;
  margin: 5px;
  border-radius: 12px;
  border: 1.5px solid lightgrey;
  outline: none;
  transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
  box-shadow: 0px 0px 20px -18px;
}

.input:hover {
  border: 2px solid lightgrey;
  box-shadow: 0px 0px 20px -17px;
}

.input:active {
  transform: scale(0.99);
}

.input:focus {
  border: 2px solid grey;
}

/*Conteudo para a Home*/
#home {
  text-align: center;
  background: linear-gradient(to top left, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
    linear-gradient(to top right, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
    linear-gradient(to bottom left, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
    linear-gradient(to bottom right, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),


    url('https://img.freepik.com/fotos-gratis/arranjo-de-livros-de-vista-acima_23-2148882757.jpg?t=st=1729189342~exp=1729192942~hmac=d7d25804d0d57df5310b9ae738c2fc9487f3824b7bf27a491b73ceec81666baf&w=1060');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: calc(100vh-88.8px);
  padding: 7%;
  width: 100%;

}

#cta .title {
  font-size: 70px;
  font-weight: bold;
  color: #0d0d0d;
  margin-bottom: 10px;
  padding-top: 2%;
}

#cta p {
  font-size: 18px;
  padding: 28px 8%;
  margin: 20px;
  color: #333;
  margin-bottom: 40px;
}

#cta-button {
  display: flex;
  justify-content: center;
  align-items: center;
}

#cta-button .input {
  width: 20%;
  height: 45px;
  padding: 12px;
  border-radius: 12px;
  border: 1.5px solid lightgrey;
  outline: none;
  transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
  box-shadow: 0px 0px 20px -18px;
}

#cta-button .input:hover {
  border: 2px solid lightgrey;
  box-shadow: 0px 0px 20px -17px;
}

#cta-button .input:active {
  transform: scale(0.99);
}

#cta-button .input:focus {
  border: 2px solid grey;
}

/* Conteudo para o catalog */

#book-container {
  width: 100%;
  position: relative;
  height: 100vh;
  padding: 28px 8%;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.book-wrapper {
  position: relative;
  overflow: hidden;
  width: 100%;
  max-width: 1500px;
}

.book-list {
  display: flex;
  align-items: center;
  gap: 22px;
  margin-top: 32px;
  transition: transform 1s ease-in-out;
  transform: translateX(0);
}

.book {
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 20px;
  gap: 18px;
  min-width: 300px;
  max-width: 300px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
  position: relative;
}

.book-image {
  width: 100%;
  max-width: 158px;
  height: auto;
  max-height: 157px;
  border-radius: 8px;
}

.book a {
  text-decoration: none;
  color: #0d0d0d;
  font-weight: bold;
  font-size: 18px;
  margin-top: 10px;
}

.book-description {
  color: #434343;
  font-size: 14px;
  margin-top: 8px;
}

.book-rating {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10px;
}

.book-rating i {
  color: #eedb2d;
  font-size: 20px;
  margin-right: 5px;
}

.load-book {
  appearance: none;
  background-color: #0d0d0d;
  border: 0.125em solid #0d0d0d;
  border-radius: 0.9375em;
  box-sizing: border-box;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 16px;
  font-weight: 600;
  margin: 0;
  height: 45px;
  min-width: 0;
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

.load-book:disabled {
  pointer-events: none;
}

.load-book:hover {
  color: #0d0d0d;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.load-book:active {
  box-shadow: none;
  transform: translateY(0);
}

@media screen and (max-width: 1170px) {
  .book-list {
    flex-wrap: wrap;
    justify-content: center;
  }

  .book {
    width: calc(50% - 12px);
  }
}

@media screen and (max-width: 600px) {
  .book {
    width: 100%;
  }

  #catalog .section-subtitle {
    text-align: center;
  }
}

/* Conteudo para o ratings */

#rating {
  min-height: calc(110vh - 91px);
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 48px;
}

.rating-image {
  width: 670px;
  height: auto;
  border-radius: 5%;
}

#rating_content {
  width: 50%;
}

#reviews {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-bottom: 40px;
  margin-top: 30px;
}

.review {
  display: flex;
  align-items: center;
  gap: 20px;
  background-color: #fff;
  padding: 12px;
  border-radius: 12px;
  box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
}

.book-image {
  width: 100px;
  height: auto;
}

.book-title {
  text-decoration: none;
  color: #0d0d0d;
  font-size: 1.56rem;
}

.review-content {
  display: flex;
  flex-direction: column;
}

.profile {
  display: flex;
  flex-direction: column;
}

.profile-info {
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: #0d0d0d;
}

.profile i {
  color: #f6e553;
  font-size: 20px;
  margin-right: 5px;
}

.avatar-image {
  width: 30px;
  height: 30px;
  border-radius: 10%;


}

.profile-review {
  display: flex;

}

.review-content p {
  display: flex;

}

@media screen and (max-width: 1170px) {
  #rating {
    flex-direction: column;
  }

  #rating .section-subtitle {
    text-align: center;
    font-size: 2.5rem;
  }

  .rating-image {
    display: none;
  }

  #rating_content {
    width: 70%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}

@media screen and (max-width: 600px) {
  #ratings .section-subtitle {
    font-size: 2rem;
  }

  #rating_content {
    width: 100%;
  }

  .review {
    flex-direction: column;
  }
}
</style>
