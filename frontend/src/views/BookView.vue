<template>
    <main>
        <section id="book-details">
            <div class="image-box">
                <a href="" class="image-placeholder"> <img :src="bookImage" alt="Book image placeholder"></a>
            </div>
            <div class="book-info">
                <a href="" class="book-title"><b>{{ this.book.name }}</b></a>
                <a @click.prevent="goToAuthorView" class="book-author">{{ author.name }}</a>
                <div class="description">
                    <span v-for="n in 5" :key="n">
                        <i :class="n <= getAvaregeRating() ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                    </span>
                    ({{ reviewList.length }}+)
                    <h1 class>Description:</h1>
                    <p class="book-synopsis">{{ this.book.synopsis }}</p>
                </div>
            </div>
        </section>

        <h2 class="section-subtitle">Reviews</h2>
            
        <section id="review-container">
            <div class="review-wrapper">
                <div class="review-list">
                    <div class="review-card" v-for="review in reviewList" :key="review.id">
                        <div class="reviewer-info">
                            <a @click.prevent="goToProfileView(review.user_id)" class="reviewer-info">
                                <img :src="reviewerImage" alt="Reviewer image">
                                <p>{{ findUserById(review.user_id).name }}</p>
                            </a>
                            <span v-for="n in 5" :key="n">
                            <i :class="n <= review.rating ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                        </span>
                        </div>
                        <p>{{ review.review }}</p>
                    </div>
                </div>
            </div>
        </section>
    </main>
</template>

<script lang="ts">
export default {
    data(): {
         book: {
             id: string; 
             name: string; 
             year: string; 
             synopsis: string; 
             author_id: string 
        }; 
        author: { 
            id: string; 
            name: string; 
            description: string 
        }; 
        bookImage: string; 
        reviewList: Array<{ 
            id: string; 
            title: string; 
            rating: number; 
            review: string; 
            user_id: string 
        }>; 
        usersList: Array<{ 
            id: string; 
            name: string 
        }>; 
        reviewerImage: string 
    } {
        return {
            book: {
                id: '',
                name: '',
                year: '',
                synopsis: '',
                author_id: ''
            },
            author: {
                id: '21312321321312',
                name: 'Gracialiano Ramos',
                description: 'dsajhdshasdhasahdsdass'
            },
            bookImage: 'src/images/books.jpg',
            reviewList: [{
                id: '98y2398y2uihwdijasid',
                title: 'Great book!',
                rating: 5,
                review: 'This is a great book, I loved it!',
                user_id: 'e87753e4-90ae-4150-b930-05bc465a317d'
            }],
            usersList: [{
                id: 'e87753e4-90ae-4150-b930-05bc465a317d',
                name: 'Bruno'
            }],
            reviewerImage: 'src/images/avatar-image.avif',
        }
    },
    methods: {
        getBookFromQuery(){
            this.book = {
                id: this.$route.query.id as string || '',
                name: this.$route.query.name as string || '',
                year: this.$route.query.year as string || '',
                synopsis: this.$route.query.synopsis as string || '',
                author_id: this.$route.query.author_id as string || ''
            };
            console.log(this.book);
        },
        listReviews() {
            fetch("http://localhost:8080/books/" + this.book.id + "/reviews", {
                headers: {
                    'authentication': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJibGEiLCJ1c2VyX2lkIjoiZTg3NzUzZTQtOTBhZS00MTUwLWI5MzAtMDViYzQ2NWEzMTdkIiwiaWF0IjoxNzI5ODE4MTA0LCJleHAiOjE3MzA0MjI5MDR9.VgcLkbvyeLToz2tIm50SOBlR2JfHyL1m6MqRkmnxy0M"
                }
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.reviewList = data;
            }
            )
        },
        getAvaregeRating() {
            let sum = 0;
            this.reviewList.forEach(review => {
                sum += review.rating;
            });
            return sum / this.reviewList.length;
        },
        getBookAuthor(){
            fetch("http://localhost:8080/books/" + this.book.id + "/author")
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    this.author = data;
                }
            )
        },
        goToAuthorView() {
            this.$router.push({ 
                name: 'AuthorView', 
                query: { author: this.author } 
            });
        },
        getUsers(){
            fetch("http://localhost:8080/users")
                .then(response => response.json())
                .then(data => {
                    this.usersList = data;
                }
            )
        },
        findUserById(user_id: string){
            return this.usersList.find(user => user.id === user_id);
        },
        goToProfileView(user_id: string){
            this.$router.push({ 
                name: 'ProfileView', 
                query: { user_id: user_id } 
            });
        }
    },
    created() {
        this.getBookFromQuery();
        this.listReviews();
        this.getBookAuthor();
        this.getUsers();
    }
}
</script>

<style>
#book-details {
    display: flex;
    width: auto;
    padding: 28px 8%;
    height: calc(100vh - 90px);
    margin-top: 3%;
    gap: 50px;
    justify-content: center;
}

.image-placeholder {
    width: 400px;
    height: 600px;
    object-fit: cover;
    background-color: #e0e0e0;
    display: flex;
    overflow: hidden;
    border-radius: 10%;
    box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
    margin: 0 auto;
    justify-content: center;
}

.book-info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    text-align: left;
    width: 50%;
}

.book-title{
    font-size: xx-large;
    margin-bottom: 10px;
    text-decoration: none;
    color: #0d0d0d;
}

.book-author{
    color: #464646;
    text-decoration: none;
    font-size: larger;
    margin-bottom: 40px;
}

.book-info i{
    color:#f6e553;
    margin-bottom: 50px;
    font-size: 30px;
    max-width: 40px;
}

.book-synopsis{
    font-size: 20px;
    margin-top: 20px;
    color: #0d0d0d;
}
.description h1 {
    color: #0d0d0d;
    font-size: 30px;
    margin-bottom: 10px;
}

.section-subtitle{
    font-size: 50px;
    margin-top: 50px;
    margin-bottom: 20px;
    color: #0d0d0d;
    text-align: center;
}

#review-container {
    width: 100%;
    height: 55vh;
    justify-content: center;
    align-items: center;
    position: relative;
}

.review-wrapper {
    position: relative;
    overflow: hidden;
    width: 100%;
}

.review-list {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-top: 32px;
    transform: translateX(0);
    transition: all 1s ease-in-out;
}

.review-card {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 30px;
    min-width: 410px;
    max-width: 410px;
    max-height: 300px;
    box-shadow: 0px 0px 12px 4px rgba(0, 0, 0, 0.1);
}

.review-card h3 {
    font-size: 18px;
    text-align: center;
}

.review-card p{
    padding: 5px;
}

.reviewer-info {
    display: flex;
    gap: 10px;
    align-items: center;
    margin-top: 5px;
    color: #0d0d0d;
    text-decoration: none;
}

.reviewer-info img {
    object-fit: center;
    overflow: hidden;
    width: 40px;
    height: 40px;
    background-color: #ffffff;
    border-radius: 50%;
}


.reviewer-info i{
    color:#f6e553;
    font-size: 15px;
}

.review-btn{
    appearance: none;
    background-color: #0d0d0d;
    border: 0.125em solid #0d0d0d;
    border-radius: 0.9375em;
    box-sizing: border-box;
    color: #ffffff;
    cursor: pointer;
    display: inline-block;
    font-family: Roobert,-apple-system,BlinkMacSystemFont,"Segoe UI",Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    font-size: 16px;
    font-weight: 600;
    margin: 0;
    height: 45px;
    min-width: 0;
    width: 30%;
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

.review-btn:disabled{
    pointer-events: none;
}

.review-btn:hover{
    color: #0d0d0d;
    background-color: #fff;
    box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
    transform: translateY(-2px);
}

.review-btn:active{
    box-shadow: none;
    transform: translateY(0);
}

.arrow_review {
    font-size: 60px;
    position: absolute;
    top: 150px;
    right: 50px;
    color: rgb(61, 61, 61);
    opacity: 0.5;
    cursor: pointer;
}
</style>