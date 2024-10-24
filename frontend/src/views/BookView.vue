<template>
    <main>
        <section id="book-details">
            <div>
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
                </div>
                    <h1>Description:</h1>
                    <p>{{ this.book.synopsis }}</p>
            </div>
        </section>

        <h2 class="section-subtitle">Reviews</h2>
            
        <section id="review-container">
            <div class="review-wrapper">
                <div class="review-list">
                    <div class="review-card" v-for="review in reviewList" :key="review.id">
                        <h3>{{ review.title }}</h3>
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

<script lang="js">
export default {
    data() {
        return {
            book: '',
            author: '',
            bookImage: 'src/images/books.jpg',
            reviewList: [],
            usersList: [],
            reviewerImage: 'src/images/profile.jpg',
        }
    },
    methods: {
        getBookFromDom(){
            const book = this.$route.params.book;
            if (book) {
                this.book = book;
            }
        },
        listReviews() {
            fetch("http://localhost:8080/api/books/" + this.book.id + "/reviews")
                .then(response => response.json())
                .then(data => {
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
            fetch("http://localhost:8080/api/books/" + this.book.id + "/author")
                .then(response => response.json())
                .then(data => {
                    this.author = data;
                }
            )
        },
        goToAuthorView() {
            this.$router.push({ 
                name: 'AuthorView', 
                params: { author: this.author } 
            });
        },
        getUsers(){
            fetch("http://localhost:8080/api/users")
                .then(response => response.json())
                .then(data => {
                    this.usersList = data;
                }
            )
        },
        findUserById(user_id){
            return this.usersList.find(user => user.id === user_id);
        },
        goToProfileView(user_id){
            this.$router.push({ 
                name: 'ProfileView', 
                params: { user_id: user_id } 
            });
        }
    },
    created() {
        this.getBookFromDom();
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

.book-info{
    display: flex;
    flex-direction: column;
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
    margin-bottom: 30px;
}

.book-info i{
    color:#f6e553;
    margin-bottom: 50px;
    font-size: 25px;
}

.description h1 {
    font-size: 20px;
    margin-bottom: 10px;
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