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
                            <span class="book-title">{{ book.title }}</span>
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

<script lang="ts">
export default {
    data(this: any): {
        author: {
            id: string;
            name: string;
            description: string;
        };
        authorImage: string;
        bookImage: string;
        bookList: Array<{
            id: string; 
            title: string; 
            year: string; 
            synopsis: string; 
            authorId: string 
        }>;
    } {
        return {
            author: {
                id: '',
                name: '',
                description: ''
            },
            authorImage: 'src/images/author.jpg',
            bookImage: 'src/images/books.jpg',
            bookList: [],
        }
    },
    methods: {
        getAuthor(){
            fetch("http://localhost:8080/authors/" + this.$route.query.id)
                .then(response => response.json())
                .then(data => {
                    this.author = data;
                    this.listBooks();
                })
        },
        listBooks() {
            fetch("http://localhost:8080/authors/" + this.author.id + "/books")
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    this.bookList = data;
                })
                .catch(error => console.error(error));
        },
        goToBookView(book: {
            id: string; 
            title: string; 
            year: string; 
            synopsis: string; 
            authorId: string
         }) {
            this.$router.push({ 
                name: 'book', 
                query: { 
                    id: book.id,}
            });
        }
    },
    created() {
        this.getAuthor();
    }
}
</script>

<style>
 
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');
 
* {
    font-family: Roobert,-apple-system,BlinkMacSystemFont,"Segoe UI",Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
 
html {
    scroll-behavior: smooth;
}
 
body{
    background-color: #eff0f3;
}
section {
    padding: 28px 8%;
}
 
footer {
    background-color:  #c6c6c6;
}
 
.section-title{
    color: #ff8e3c;
    font-size: 1.56rem;
}
 
.section-subtitle{
    font-size: 2.8175rem;
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
    color: #0d0d0d;
}
 
.description p{
    color: #0d0d0d;
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
 
.container.active {
    background-color: white;
  }
 
.book-title.active {
    color: black;
  }
 
 
</style>