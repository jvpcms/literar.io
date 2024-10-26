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
                        <div class="image-container">
                            <img class="book-image" :src="bookImage" alt="Book image">
                            <div class="book-info">
                                <span class="book-title">{{ book.title }}</span>
                                <p class="book-desc">{{ book.synopsis }}</p>
                            </div>
                        </div>
                        <a @click.prevent="goToBookView(book)">
                            <button class="book-button">Read more</button>
                        </a>
                    </div>
                </div>
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
                    id: book.id }
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

.author-info {
    display: flex;
    flex-direction: column;
}

.author-name {
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

.description p {
    color: #0d0d0d;
}

#book-container {
    width: auto;
    margin-top: 3%;
    justify-content: center;
    align-items: center;
}

#book-container h1 {
    font-size: xx-large;
    color: #0d0d0d;
    margin-bottom: 40px;
}

.book-wrapper {
    position: relative;
    overflow: hidden;
}

.book-list {
    display: flex;
    gap: 40px; /* Aumenta o espaçamento entre os cards */
}

.book-card {
    position: relative;
    flex-direction: column;
}

.image-container {
    position: relative;
    width: 270px;
    height: 400px;
    overflow: hidden;
    border-radius: 10%;
    margin-bottom: 20px; /* Adiciona espaço entre a imagem e o botão */
}

.book-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: all 0.3s ease;
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

.image-container:hover .book-info {
    opacity: 1;
}

.book-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 8px;
}

.book-desc {
    font-size: 14px;
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
</style>
