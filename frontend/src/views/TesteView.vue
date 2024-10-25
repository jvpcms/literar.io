<template>
    <div>
      <input
        type="text"
        v-model="searchQuery"
        placeholder="Procure pelo nome do livro"
      />
  
      <ul v-if="filteredBooks.length">
        <li v-for="book in filteredBooks" :key="book.id">
          <h3>{{ book.title }}</h3>
          <p>{{ book.synopsis }}</p>
          <small>Ano: {{ book.year }}</small>
        </li>
      </ul>
  
      <p v-else>Nenhum livro encontrado.</p>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        books: [], // Lista de livros carregada da API
        searchQuery: "", // Termo de pesquisa
      };
    },
    computed: {
      filteredBooks() {
        // Filtra os livros com base na pesquisa do usuário
        return this.books.filter(book =>
          book.title.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      },
    },
    methods: {
      async fetchBooks() {
        try {
          const response = await fetch("http://localhost:8080/books");
          if (response.ok) {
            this.books = await response.json();
          } else {
            console.error("Erro ao buscar livros:", response.statusText);
          }
        } catch (error) {
          console.error("Erro na requisição:", error);
        }
      },
    },
    created() {
      // Carrega os livros assim que o componente é criado
      this.fetchBooks();
    },
  };
  </script>
  <style scoped>
  input {
    margin-bottom: 20px;
    padding: 10px;
    width: 100%;
    box-sizing: border-box;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin-bottom: 15px;
    padding: 10px;
    border: 1px solid #ddd;
  }
  
  h3 {
    margin: 0 0 5px 0;
  }
  
  p {
    margin: 0;
  }
  </style>
  