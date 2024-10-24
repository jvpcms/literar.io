<template>
    <div>
      <header>
        <nav id="navbar">
          <a href="#"><i class="fa-solid fa-book"><span id="nav_logo">Literar.io</span></i></a>
          <ul id="nav_list">
            <li class="nav-item active"><a href="#home">Home</a></li>
            <li class="nav-item"><a href="#catalog">Catalog</a></li>
            <li class="nav-item"><a href="#rating">Reviews</a></li>
          </ul>
          <nav id="nav-account">
            <form action="login.html">
              <button class="btn">Sign in</button>
            </form>
            <form action="register.html">
              <button class="btn">Register</button>
            </form>
          </nav>
          <button id="mobile_btn"><i class="fa-solid fa-bars"></i></button>
        </nav>
        <div id="mobile_menu">
          <ul id="mobile_nav_list">
            <li class="nav-item active"><a href="#home">Home</a></li>
            <li class="nav-item"><a href="#catalog">Catalog</a></li>
            <li class="nav-item"><a href="#rating">Reviews</a></li>
          </ul>
        </div>
      </header>
  
      <main id="content">
        <!-- Home Section -->
        <section id="home">
          <div id="cta">
            <h1 class="title">Literar.io</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
          </div>
          <div id="cta-button">
            <input placeholder="Books or Authors" type="text" name="text" class="input" />
            <button class="btn">Search</button>
          </div>
        </section>
  
        <!-- Catalog Section -->
        <section id="book-container">
          <h2 class="section-title">Catalog</h2>
          <h3 class="section-subtitle">Trending books</h3>
          <div class="book-wrapper">
            <div class="book-list">
              <!-- Repetindo os livros -->
              <div class="book" v-for="book in books" :key="book.id">
                <a :href="book.link"><img :src="book.image" class="book-image" /></a>
                <a :href="book.link">{{ book.title }}</a>
                <span class="book-description">{{ book.description }}</span>
                <div class="book-rating">
                  <i class="fa-solid fa-star" v-for="n in 5" :key="n"></i>
                  <span>(500+)</span>
                </div>
              </div>
            </div>
          </div>
        </section>
  
        <!-- Reviews Section -->
        <section id="rating">
          <img src="/images/rating.jpg" alt="" class="rating-image" />
          <div id="rating_content">
            <h2 class="section-title">Reviews</h2>
            <h3 class="section-subtitle">Popular reviews</h3>
            <div id="reviews">
              <div class="review" v-for="review in reviews" :key="review.id">
                <a href="#"><img src="/images/books.jpg" class="book-image" /></a>
                <div class="review-content">
                  <a class="book-title" href="#">{{ review.bookTitle }}</a>
                  <div class="profile">
                    <div class="profile-review">
                      <a href="profile.html" class="profile-info">
                        <img src="/images/avatar-image.avif" class="avatar-image" />
                        Fulano Sicrano
                      </a>
                      <span>
                        <i class="fa-solid fa-star" v-for="n in 5" :key="n"></i>
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
  
      <footer>
        <img src="/images/wave.svg" alt="Wave" />
      </footer>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        books: [
          {
            id: 1,
            title: "Lorem Ipsum",
            description: "Lorem ipsum dolor sit amet consectetur...",
            image: "/images/books.jpg",
            link: "#",
          },
          // Adicionar mais livros aqui...
        ],
        reviews: [
          {
            id: 1,
            bookTitle: "Book Title",
            text: "Lorem ipsum dolor sit amet...",
          },
          // Adicionar mais reviews aqui...
        ],
      };
    },

    methods(){
      // Fazer uma request para endpoint 'http://localhost:8080/api/books/' e colocar no headers meu token, para funcionar a request
      fetch('http://localhost:8080/api/books/', {
        headers: {
          'Authorization': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJGcmFuIiwidXNlcl9pZCI6ImNlNDJkYTU3LWZiZDAtNGYzMi04ODNjLWY2OWRmNWI3NGQyMCIsImlhdCI6MTcyOTc0MTEyMSwiZXhwIjoxNzMwMzQ1OTIxfQ.a0_6UZdHfS_F6iESdcTlUPmyt8sHskYldqGBcODY8bk'
        }
      })
      .then(response => response.json())
      .then(data => {
        data.forEach(book => {
          this.books.push({
            id: book.id,
            title: book.title,
            description: book.description,
            image: "/images/books.jpg",
            link: "#"
            });
          });
        });
        // Dar um for ( elemento em data ) para cada elemento, fazer 
        // data é uma lista de livros, pegar cada elemento dele e extrair os campos.
        // Precisamos que essa função seja rodada assim que a página seja inicializada. Depois, construri
    },
    mounted() {
      // Lógica para o ScrollReveal e funcionalidade mobile
      document.querySelector("#mobile_btn").addEventListener("click", function () {
        document.querySelector("#mobile_menu").classList.toggle("active");
        this.querySelector("i").classList.toggle("fa-x");
      });
      ScrollReveal().reveal("#navbar", { duration: 2000 });
      ScrollReveal().reveal("#cta", { origin: "top", duration: 2000, distance: "20%" });
      ScrollReveal().reveal(".book", { origin: "right", duration: 2000, distance: "20%" });
    },
  };
  </script>
  
  <style scoped>
  /* Estilos gerais do catálogo */
  #book-container {
    width: 100%;
    height: 100vh;
    justify-content: center;
    align-items: center;
  }
  
  .book-wrapper {
    position: relative;
    overflow: hidden;
    width: 100%;
  }
  
  .book-list {
    display: flex;
    align-items: center;
    gap: 22px;
    margin-top: 32px;
    transition: transform 1s ease-in-out;
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
  
  .arrow {
    font-size: 60px;
    position: absolute;
    top: 330px;
    right: 50px;
    color: rgb(61, 61, 61);
    opacity: 0.5;
    cursor: pointer;
  }
  
  .book-wrapper:hover .book-list {
    transform: translateX(-400px);
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
  
  /* Estilos da seção home */
  #home {
    text-align: center;
    min-height: calc(100vh - 88.8px);
    background: linear-gradient(
        to top left,
        rgba(239, 240, 243, 0),
        rgba(239, 240, 243, 0.6)
      ),
      linear-gradient(to top right, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
      linear-gradient(to bottom left, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
      linear-gradient(to bottom right, rgba(239, 240, 243, 0), rgba(239, 240, 243, 0.6)),
      url('https://img.freepik.com/fotos-gratis/arranjo-de-livros-de-vista-acima_23-2148882757.jpg?t=st=1729189342~exp=1729192942~hmac=d7d25804d0d57df5310b9ae738c2fc9487f3824b7bf27a491b73ceec81666baf&w=1060');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: calc(100vh - 85px);
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
  
  /* Estilos da seção de avaliações */
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
  