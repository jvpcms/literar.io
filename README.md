# LITERAR.io

Bem-vindo ao **Literar.io** - uma rede social para amantes de livros! Este projeto faz parte da disciplina **Laboratório de Programação II** e está sendo desenvolvido por com o objetivo de criar uma plataforma onde os usuários possam compartilhar suas leituras, resenhas e interagir com outros leitores.

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Ferramenta de Build**: Gradle
- **Banco de Dados**: PostgreSQL
- **Frontend**: HTML, CSS, JavaScript e Vue (Framework utilizado)

## Descrição do Projeto

O **Literar.io** tem como objetivos que os usuários:

- Criem perfis personalizados com informações sobre seus gostos literários.
- Escrevam e compartilhem resenhas de livros.
- Sigam outros usuários e acompanhem suas atividades na plataforma.

Por enquanto, os perfis criados são simples e ainda não foram implementadas as funções de seguir outros usuários. Porém, no backend, toda a parte de CRUD de perfis, resenhas, livros e autores está feita.

## Estrutura do Projeto

O projeto está dividido em tres partes principais:

1. **API Backend**: Desenvolvida em Java utilizando Spring Boot, a API será responsável por gerenciar todas as operações de backend, incluindo autenticação, gerenciamento de usuários, livros, resenhas, entre outros.

2. **Frontend**: Desenvolvido utilizando Vue.js, o frontend é responsável por fornecer uma interface interativa e amigável para os usuários. Ele se comunica com a API backend para exibir e manipular dados em tempo real. Para hospedar a interface, foi configurado o servidor web Nginx, garantindo alta performance e confiabilidade na entrega de conteúdo estático.

## Setup

Para realizar os testes da aplicação serão utilizados ambientes configurados em `Docker`. Por default, as portas configuradas no arquivo `docker-compose.yml` são:
- 9090: Serviço de servidor web
- 8080: Serviço da API
- 5432: Serviço do banco de dados (postgres v12)

Para inicializar o docker, tenha Docker Desktop em sua maquina e execute o seguinte comando no terminal:
```
docker-compose up --build
```
Para encerrar o docker, execute o seguinte comando no terminal:
```
docker-compose down -v
```
Para viabilizar a comunicação entre o banco de dados e a api, serão necessárias a adição e configuração do arquivo `application.properties` na pasta `api\src\main\resources\` (caso não exista, crie a pasta).
O usuário e a senha padrão esperados pelo Docker são a string `postgres`. Assim, adicione os seguintes campos no arquivo caso quera viabilizar o uso nas definições padrão: 
```
spring.application.name=api
spring.datasource.url=${POSTGRES_URL:jdbc:postgresql://db:5432/master}
spring.datasource.username=${POSTGRES_USER:postgres}
spring.datasource.password=${POSTGRES_PASSWORD:postgres}
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
```
Para inserir os dados de teste para a API, vá até a pasta 'sample_data', acesse os bancos de dados Postgres do docker pelo comando:
'''
docker exec -it literario-db-1 psql -U postgres -d master 
'''
Depois, insira os dados manualmente utilizando comandos SQL adequados para tanto. Logo após, sinta-se à vontade para testar todos os endpoints de nosso backend e as funcionalidades que já foram adaptadas para o frontend. 

