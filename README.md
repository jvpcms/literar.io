# App Android

Esta branch corresponde à adaptação de métodos HTTP do aplicativo Android, com foco na integração com o backend de uma rede social de livros. A API utilizada foi desenvolvida em Java com Spring Boot, e a integração no Android foi realizada com [Retrofit](https://square.github.io/retrofit/), uma biblioteca usada para simplificar requisições HTTP.

## Funcionalidades Implementadas

### 1. **Login**

A página de login permite ao usuário autenticar-se no aplicativo, gerando um token JWT (JSON Web Token) que é armazenado localmente para uso em requisições futuras. A autenticação garante que as operações do usuário sejam realizadas com segurança e verificadas pelo backend.

- **Requisição**: O login utiliza o endpoint `/auth/login` do backend, enviando as credenciais do usuário.
- **Armazenamento do Token**: Após o sucesso do login, o token JWT é salvo no `SharedPreferences` para uso posterior nas chamadas autenticadas.

### 2. **Listagem de Livros**

A página de listagem de livros consome o endpoint `/books` para buscar os livros disponíveis na rede social. Essa página é exibida após o login bem-sucedido.

- **Autenticação**: O token JWT é automaticamente enviado no cabeçalho da requisição para validar a sessão do usuário.
- **Exibição**: Os livros são exibidos em uma lista simples com informações básicas, neste caso, título e sinopse.

### 3. **Postagem de Reviews**

A página de criação de reviews permite ao usuário enviar uma avaliação para um livro. Essa funcionalidade consome o endpoint `/reviews/post`.

- **Configuração do Token**: O token JWT obtido no login é utilizado para autenticar a requisição.
- **Envio dos Dados**: O usuário seleciona um livro e insere uma avaliação (nota e texto). Os dados enviados incluem o ID do livro (que vem é passado pela página anterior), o ID do usuário (extraído do token JWT) e a avaliação.
- **Persistência**: A review é salva no backend e associada ao usuário e ao livro.

## Sobre o Retrofit

O *Retrofit* é uma biblioteca de código aberto que facilita a integração de aplicativos Android com APIs RESTful. Ele permite a definição de interfaces de serviço, simplificando o envio de requisições HTTP (GET, POST, PUT, DELETE, etc.) e a manipulação das respostas.

No contexto deste app, o Retrofit foi utilizado para:

1. Configurar os endpoints `/auth/login`, `/books`, e `/reviews/post`.
2. Adicionar cabeçalhos e tokens de autenticação nas requisições.
3. Facilitar a serialização e deserialização de objetos JSON.

## Uso da Chave Secreta

A chave secreta é usada para gerar e validar tokens JWT no backend e no app. Para garantir que os tokens sejam confiáveis:

- A chave secreta no backend deve ser **idêntica** à utilizada para decodificar os tokens no app.
- Tokens gerados pelo login no app ou por ferramentas externas, como *Postman*, devem usar a mesma chave configurada no backend.
- Alterações na chave exigem atualizações tanto no backend quanto no app.

A assinatura do token é baseada na chave e no algoritmo HS256 (HMAC-SHA256). Qualquer diferença na chave secreta resultará em erros de autenticação.

Por isso, certifique-se de sincronizar as configurações do backend com o app.

No backend, a chave geralmente é definida no arquivo `.env`, já no app, ela é definida na classe [MainActivity.java](app/src/main/java/com/example/agoravai/MainActivity.java), no seu método `getUserIdFromToken(String token)`.

## Fluxo de Navegação

1. **Login**: O usuário acessa a página de login e insere suas credenciais. Após autenticação, o token JWT é armazenado e o usuário é redirecionado para a listagem de livros.
2. **Listagem de Livros**: O usuário visualiza os livros disponíveis e pode selecionar um para criar uma review.
3. **Postagem de Reviews**: O usuário acessa a página de criação de reviews, preenche sua avaliação e a envia. Após a postagem bem-sucedida, o app exibe uma mensagem de sucesso.

## Conclusão

Esta branch demonstra a integração de um app Android com uma API RESTful utilizando Retrofit e autenticação JWT. As páginas de login, listagem de livros e postagem de reviews oferecem uma experiência inicial funcional e segura, pronta para expansão com novas funcionalidades. Certifique-se de sincronizar o backend e o app para evitar problemas de autenticação e garantir o funcionamento correto das funcionalidades.


