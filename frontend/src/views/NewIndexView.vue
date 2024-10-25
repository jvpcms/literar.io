<template>
    <div id="app">
      <header>
        <nav id="navbar">
          <a href="#home">
            <i class="fa-solid fa-book">
              <span id="nav_logo">Literar.io</span>
            </i>
          </a>
          <ul id="nav_list">
            <li class="nav-item" :class="{ active: activeSection === 'home' }">
              <a href="#home" @click.prevent="scrollToSection('home')">Home</a>
            </li>
            <li class="nav-item" :class="{ active: activeSection === 'catalog' }">
              <a href="#catalog" @click.prevent="scrollToSection('catalog')">Catalog</a>
            </li>
            <li class="nav-item" :class="{ active: activeSection === 'rating' }">
              <a href="#rating" @click.prevent="scrollToSection('rating')">Reviews</a>
            </li>
          </ul>
  
          <nav id="nav-account">
            <button class="btn" @click="navigateTo('login')">Sign in</button>
            <button class="btn" @click="navigateTo('register')">Register</button>
          </nav>
  
          <button id="mobile_btn" @click="toggleMobileMenu">
            <i :class="isMobileMenuActive ? 'fa-solid fa-x' : 'fa-solid fa-bars'"></i>
          </button>
        </nav>
  
        <div id="mobile_menu" v-if="isMobileMenuActive">
          <ul id="mobile_nav_list">
            <li class="nav-item" :class="{ active: activeSection === 'home' }">
              <a href="#home" @click.prevent="scrollToSection('home')">Home</a>
            </li>
            <li class="nav-item" :class="{ active: activeSection === 'catalog' }">
              <a href="#catalog" @click.prevent="scrollToSection('catalog')">Catalog</a>
            </li>
            <li class="nav-item" :class="{ active: activeSection === 'rating' }">
              <a href="#rating" @click.prevent="scrollToSection('rating')">Reviews</a>
            </li>
          </ul>
        </div>
      </header>
  
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
            <input
              placeholder="Books or Authors"
              type="text"
              class="input"
              v-model="searchQuery"
            />
            <button class="btn" @click="search">Search</button>
          </div>
        </section>
  
        <!-- Catalog Section -->
        <section id="catalog">
          <h2 class="section-title">Catalog</h2>
          <h3 class="section-subtitle">Trending books</h3>
          <div class="book-wrapper">
            <div class="book-list">
              <div class="book" v-for="book in books" :key="book.id">
                <a :href="`/book/${book.id}`">
                  <img :src="book.image" class="book-image" />
                </a>
                <a :href="`/book/${book.id}`">{{ book.title }}</a>
                <span class="book-description">{{ book.description }}</span>
                <div class="book-rating">
                  <i
                    v-for="n in 5"
                    :key="n"
                    class="fa-solid fa-star"
                    :class="{ active: n <= book.rating }"
                  ></i>
                  <span>({{ book.reviews }}+)</span>
                </div>
              </div>
            </div>
          </div>
        </section>
  
        <!-- Reviews Section -->
        <section id="rating">
          <img
            src="https://img.freepik.com/fotos-gratis/celebracao-do-dia-mundial-do-livro_23-2151245176.jpg"
            alt=""
            class="rating-image"
          />
  
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
                        <i
                          v-for="n in 5"
                          :key="n"
                          class="fa-solid fa-star"
                          :class="{ active: n <= review.rating }"
                        ></i>
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
  export default {
    data() {
      return {
        isMobileMenuActive: false,
        searchQuery: '',
        activeSection: 'home',
        books: [
          {
            id: 1,
            image: '/images/books.jpg',
            title: 'Lorem Ipsum',
            description:
              'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Officiis aut odio totam dignissimos quia eos, placeat eius quisquam id saepe earum consectetur nisi minima vitae atque, esse, harum ducimus exercitationem!',
            rating: 5,
            reviews: 500,
          },
          // Adicione mais livros aqui
        ],
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
          // Adicione mais reviews aqui
        ],
      };
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
    },
    mounted() {
      window.addEventListener('scroll', this.handleScroll);
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
  }
  
  body {
    background-color: #eff0f3;
  }
  
  section {
    padding: 28px 8%;
  }
  
  footer {
    background-color: #c6c6c6;
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
  
  /* Estilos adicionais */
  #navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 8%;
    position: fixed;
    width: 100%;
    background-color: #fff;
    z-index: 1000;
  }
  
  #nav_list {
    list-style: none;
    display: flex;
    gap: 20px;
  }
  
  .nav-item a {
    text-decoration: none;
    color: #333;
  }
  
  .nav-item.active a {
    color: #ff8e3c;
  }
  
  #mobile_menu {
    display: none;
  }
  
  #mobile_menu.active {
    display: block;
  }
  
  /* Continue com os estilos conforme necessário */
  </style>
  