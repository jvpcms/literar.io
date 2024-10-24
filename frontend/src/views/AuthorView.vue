<template>
    <main>
        <section id="author-details">
            <div>
                <a href="" class="image-placeholder"> <img :src="authorImage" alt="Book image placeholder"></a>
            </div>
            <div class="author-info">
                <a href="" class="author-name"><b>{{ author.name }}</b></a>
                <div class="description">
                    <h1> Biography: </h1>
                    <p>{{ author.description }}</p>
                </div>
            </div>
        </section>

        <section id="book-container">
            <h1>Bibliography</h1>
            <div class="book-wrapper">
                <div class="book-list">
                    <div class="book-card" v-for="book in bookList" :key="book.id">
                        <img class="book-image" :src="bookImage" alt="">
                            <span class="book-title">{{ book.name }}</span>
                            <p class="book-desc">{{ book.synopsis }}</p>
                            <a @click.prevent="goToBookView(book)">
                                <button class="book-button">Read more</button>
                            </a>
                    </div>
                </div>
                <i class="fas fa-chevron-right arrow"></i>
            </div>
        </section>
    </main>
</template>

<script>
export default {
    data() {
        return {
            author: '',
            authorImage: 'src/images/author.jpg',
            bookImage: 'src/images/books.jpg',
            bookList: [],
        }
    },
    methods: {
        getAuthorFromDom(){
            const author = this.$route.params.author;
            if (author) {
                this.author = author;
            }
        },
        listBooks() {
            fetch("http://localhost:8080/api/authors/" + this.authorId + "/books")
                .then(response => response.json())
                .then(data => {
                    this.bookList = data;
                })
                .catch(error => console.error(error));
        },
        goToBookView(book) {
            this.$router.push({ name: 'BookView', params: { book: book } });
        }
    },
    created() {
        this.getAuthorFromDom();
        this.listBooks();
    }
}
</script>

<style>
#author-details {
    display: flex;
    width: auto;
    height: calc(100vh - 90px);
    margin-top: 3%;
    gap: 50px;
}

.image-placeholder {
    width: 300px;
    height: 440px;
    object-fit: cover;
    background-color: #e0e0e0;
    display: flex;
    overflow: hidden;
    border-radius: 10%;
    box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
}

.author-info{
    display: flex;
    flex-direction: column;
}

.author-name{
    font-size: xx-large;
    margin-bottom: 60px;
    text-decoration: none;
    color: #0d0d0d;
}

.description h1 {
    font-size: 20px;
    margin-bottom: 10px;
}

#book-container {
    width: auto;
    height: 100vh;
    margin-top: 3%;
    justify-content: center;
    align-items: center;

}

#book-container h1{
    font-size: xx-large;
    text-decoration: none;
    color: #0d0d0d;
}

.book-wrapper {
    position: relative;
    overflow: hidden;
}
  
.book-list {
    display: flex;
    align-items: center;
    height: 600px;
    transform: translateX(0);
    transition: all 1s ease-in-out;
}
  
.book-card {
    margin-right: 50px;
    position: relative;
    justify-content: center;
    align-items: center;
}
  
.book-card:hover .book-image {
    transform: scale(1.05);
    margin: 0 30px;
    opacity: 0.3;
}
  
.book-card:hover .book-title,
.book-card:hover .book-desc,
.book-card:hover .book-button {
    opacity: 1;
    align-items: center;
    justify-content: center;
    display: flex;
    flex-direction: column;
}
  
.book-image {
    transition: all 1s ease-in-out;
    width: 270px;
    height: 400px;
    object-fit: cover;
    overflow: hidden;
    border-radius: 10%;
  }
  
.book-title {
    background-color: transparent;
    padding: 0 10px;
    font-size: 32px;
    font-weight: bold;
    position: absolute;
    top: 10%;
    left: 50px;
    opacity: 0;
    transition: 1s all ease-in-out;
}
  
.book-desc {
    background-color: transparent;
    padding: 10px;
    font-size: 14px;
    position: absolute;
    top: 30%;
    left: 50px;
    width: 230px;
    opacity: 0;
    transition: 1s all ease-in-out;
}
  
.book-button {
    padding: 10px;
    background-color: #ff8e3c;
    color: white;
    border-radius: 10px;
    outline: none;
    border: none;
    cursor: pointer;
    position: absolute;
    bottom: 20px;
    left: 120px;
    opacity: 0;
    transition: 1s all ease-in-out;
}

.arrow {
    font-size: 60px;
    position: absolute;
    top: 250px;
    right: 0;
    color: rgb(61, 61, 61);
    opacity: 0.5;
    cursor: pointer;
}

.arrow.left {
    left: 50px; /* Posição para a seta da esquerda */
}

.arrow.right {
    right: 50px; /* Posição para a seta da direita */
}

.container.active {
    background-color: white;
  }
  
.book-title.active {
    color: black;
  }

  
</style>