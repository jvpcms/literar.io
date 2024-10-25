<template>
    <main>
        <section id="book-details">
            <div class="image-box">
                <a href="" class="image-placeholder"> <img :src="bookImage" alt="Book image placeholder"></a>
            </div>
            <div class="book-info">
                <a href="" class="book-title"><b>{{ book.title }}</b></a>
                <a @click.prevent="goToAuthorView" class="book-author">{{ author.name }}</a>
                <div class="description">
                    <span v-for="n in 5" :key="n">
                        <i :class="n <= getAvaregeRating() ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                    </span>
                    ({{ reviewList.length }}+)
                    <h1 class>Description:</h1>
                    <p class="book-synopsis">{{ book.synopsis }}</p>
                </div>
            </div>
        </section>

        <h2 class="section-subtitle">Reviews</h2>
            
        <section id="review-container">
            <div class="review-wrapper">
                <div class="review-list">
                    <div class="review-card" v-for="review in reviewList" :key="review.id">
                        <div class="reviewer-info">
                            <a @click.prevent="goToProfileView(review.userId)" class="reviewer-info">
                                <img :src="reviewerImage" alt="Reviewer image">
                                <p>{{ findUserNameById(review.userId) }}</p>
                            </a>
                            <span v-for="n in 5" :key="n">
                            <i :class="n <= review.rate ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                        </span>
                        </div>
                        <p class="review-text">{{ review.review }}</p>
                    </div>
                </div>
            </div>
        </section>
    </main>
</template>

<script lang="ts">
export default {
    data(this: any): {
        book: {
             id: string; 
             title: string; 
             year: string; 
             synopsis: string; 
             authorId: string 
        }; 
        author: { 
            id: string; 
            name: string; 
            description: string 
        }; 
        bookImage: string; 
        reviewList: Array<{ 
            id: string; 
            rate: number; 
            review: string; 
            userId: string 
        }>;
        userList: Array<{ 
            id: string; 
            name: string 
        }>;
        reviewerImage: string;
    } {
        return {
            book: {
                id: '',
                title: '',
                year: '',
                synopsis: '',
                authorId: ''
            },
            author: {
                id: '',
                name: '',
                description: ''
            },
            bookImage: 'src/images/books.jpg',
            reviewList: [{
                id: '',
                rate: 0,
                review: '',
                userId: ''
            }],
            userList: [{
                id: '',
                name: ''
            }],
            reviewerImage: 'src/images/avatar-image.avif',
        }
    },
    methods: {
        getBook(){
            fetch("http://localhost:8080/books/info/" + this.$route.query.id)
                .then(response => response.json())
                .then(data => {
                    this.book = data;
                }
            )
        },
        listReviews(this: any) {
            fetch("http://localhost:8080/books/info/" + this.book.id + "/reviews")
            .then(response => response.json())
            .then(data => {
                this.reviewList = data;
            }
            )
        },
        getAvaregeRating(this: any) {
            let sum = 0;
            this.reviewList.forEach((review: { id: string; title: string; rate: number; review: string; userId: string }) => {
                sum += review.rate;
            });
            return sum / this.reviewList.length;
        },
        getBookAuthor(this: any){
            fetch("http://localhost:8080/authors/" + this.book.authorId)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    this.author = data;
                }
            )
        },
        goToAuthorView(this: any) {
            this.$router.push({ 
                name: 'author', 
                query: { 
                    id: this.author.id,
                } 
            });
        },
        getUserById(this: any, userId: string){
            return fetch("http://localhost:8080/users/info/" + userId, {
            headers: {
                'authentication': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJibGEiLCJ1c2VyX2lkIjoiZTg3NzUzZTQtOTBhZS00MTUwLWI5MzAtMDViYzQ2NWEzMTdkIiwiaWF0IjoxNzI5ODE4MTA0LCJleHAiOjE3MzA0MjI5MDR9.VgcLkbvyeLToz2tIm50SOBlR2JfHyL1m6MqRkmnxy0M"
            }
            })
            .then(response => response.json())
            .then(data => {
            return {
                id: data.id as string,
                name: data.name as string
            };
            });
        },
        getUsersForReviews(this: any){
            this.reviewList.forEach((review: { id: string; title: string; rating: number; review: string; userId: string }) => {
                this.getUserById(review.userId).then((user: { id: string; name: string }) => {
                    this.userList.push(user);
                })
            });
        },
        findUserNameById(this: any, userId: string){
            return this.userList.find((user: { id: string; name: string }) => user.id === userId)?.name || '';
        },
        goToProfileView(this: any, userId: string){
            const name = this.userList.find((user: { id: string; name: string }) => user.id === userId)?.name || '';
            this.$router.push({ 
                name: 'ProfileView', 
                query: { 
                    userId: userId,
                    name: name
                } 
            });
        }
    },
    created() {
        this.getBook();
        this.getBookAuthor();
        const accessToken = localStorage.getItem('literarioToken');
        this.listReviews();
        this.getUsersForReviews();
    },
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
    justify-content: center;
}

.review-card {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 30px;
    min-width: 410px;
    max-width: 410px;
    min-height: 300px;
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
    width: 50px;
    height: 50px;
    background-color: #ffffff;
    border-radius: 50%;
}


.reviewer-info i{
    color:#f6e553;
    font-size: 20px;
}

.review-text {
    font-size: 20px;
    color: #0d0d0d;
    margin-top: 10px;
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