# MJV Pet Shop API

API destinada a manipular os recursos de uma loja de venda de produtos para pets.
A API foi baseada na arquitetura REST, com protocolo HTTP e requisições e respostas com o corpo no formato JSON.
Também foi seguido o pattern controller-service-repository para segregação das responsabilidades.

### Tecnologias utilizadas

* Jdk 17
* Maven
* Spring Boot
* Spring Data JPA
* Spring Web
* MySQL
* Open API
* JUnit5
* Mockito
* Jacoco
* Lombok
* Docker
* H2(para testes)

### Pacotes

| Pacote     | Definição                                                                     |
|------------|-------------------------------------------------------------------------------|
| model      | classes de entidades e dtos relacionadas ao negócio                           |
| utilities  | classes de apoio, como de validação                                           |
| exception  | classes relacionadas a exceções e ao seu gerenciamento                        |
| controller | endpoints para consumo da API utilizando protocolo HTTP                       |
| service    | classes contendo as regras de negócio da API                                  |
| repository | pacote das interfaces de acesso ao bando de dados. Utilizam o spring data jpa |

### Funcionalidades principais

* Adição de clientes;
* Adição de produtos;
* Adição de itens no carrinho do cliente;
* Geração de ordem de compra
* Busca de produtos com paginação
* Busca de ordens de compra por cliente com paginação

### Instalação

1. Sem containerização
    * Faça o clone do projeto no github;
    * Crie uma database de nome mjv_pet_shop utilizando o MySQL;
    * Rode o projeto na IDE escolhida;
    * Acesse o swagger para consumir a a API ou verificar a estrutura das requisições 
   utilize um http client como Insomnia ou Postman
2. Com containerização (Docker)
   * Faça o clone do projeto nogithub;
   * Abra o projeto na IDE escolhida;
   * Navegue para a pasta do projeto;
   * Execute o comando docker-compose up -d;
   * Verifique se ambos os containers, do mysql e da aplicação estão rodando;
   * O container do mysql pode demorar mais que o da API para ser criado. Caso isso ocorra 
   e apenas o container do mysql esteja rodando. Execute o comando
    docker start 'container da API'.
   * Acesse o swagger para consumir a a API ou verificar a estrutura das requisições
     utilize um http client como Insomnia ou Postman.

### Requisições
Todas as requisições estão documentadas no swagger.
<br>Após a inicialização da API, acesse o link:
<br> http://localhost:8080/swagger-ui.html

### Testes/Cobertura
Para acessar o relatório do java code coverage (Jacoco):
* Comando 'mvn package' para fazer o build da aplicação e rodar os testes ou 'mvn test' apenas para gerar os testes;
* Abrir no navegador o seguinte relatório gerado após os testes: ~target/site/jacoco/index.html


