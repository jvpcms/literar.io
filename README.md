Esta branch corresponde à adaptação de um método ***POST*** para um aplicativo android, contendo um design relativamente simples. 
Para a integração com a API feita em Java e SpringBoot, foi usado o [*Retrofit*](https://square.github.io/retrofit/) uma API de conexão HTTP para Android.
A página representada nessa branch já leva em conta que o usuário está logado no app, isto é, não há um acesso prévio a uma página de login.
Assim, foi adaptado de nossa API a postagem de uma review por um usuário.
Sendo assim, no arquivo ['RetrofitService.java'](app/src/main/java/com/example/agoravai/services/RetrofitService.java), você deve substituir o token por um token gerado após um login realizado,
por exemplo, no *Postman*. Segue o código com a parte citada:
'''java
// Substitua pelo token real
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJnYWJyaWVsIiwidXNlcl9pZCI6IjdiMTBhMDRlLTA5ODctNGZkZC1hNmU5LTBmMzQyOTE1ZmNmMSIsImlhdCI6MTczMTI4ODAyNiwiZXhwIjoxNzMxODkyODI2fQ.x0Cl4pKNZ7xPomWPKFK0g1xnAhdrTL7Nnt2Qv2tEwd8";
'''

E, para suprir a falta de uma página de login, deve ser adicionado ao header da requisição feita a API dados contendo o id do usuário e do livro sobre o qual ele fará a review. 
Isso deve ser alterado no arquivo ['MainActivity.java'](app/src/main/java/com/example/agoravai/MainActivity.java), na seguinte parte:
'''java
// Usando um ID de usuário e livro de exemplo (substitua conforme necessário)
                UUID userId = UUID.fromString("7b10a04e-0987-4fdd-a6e9-0f342915fcf1");  // ID do usuário
                UUID bookId = UUID.fromString("15f67fc2-000d-4399-b759-537efc43cc19");  // ID do livro
'''

