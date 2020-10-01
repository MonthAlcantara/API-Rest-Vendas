# API-Rest-Vendas 

Projeto de API Rest, criado com Spring Boot para Cadastro e gerenciamento de produtos, ítens e clientes.

## Documentação da API
https://monthalcantara-apivendas.herokuapp.com/swagger-ui.html

## 📦 Desenvolvimento

Utilizado Spring Boot seguindo os padrões Rest de Clean code e S.O.L.I.D.. Utilizado Spring Security com JWT para autenticação e autorização e Swagger para Documentação.  

# Setup da aplicação (local)

## Pré-requisito

Para construir e executar o aplicativo, você precisa ter as seguintes dependências estejam corretamente instaladas:

- [JDK 1.8.x](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.x.x](https://maven.apache.org)
- [Redis](https://redis.io/download)
- [Lombok](https://projectlombok.org/download) (Habilite o plugin na sua IDE)
- MySql - 5.xx

## Passos para a configuração 

**1. Clone o aplicativo **

```bash
git clone https://github.com/MonthAlcantara/API-Rest-Vendas.git
```

**2. Escolha o Application.properties que será utilizado (Development ou Production), para isso: **

+ Abra o `src/main/resources/application.properties`

+ Mude `spring.profiles.active` para `production` ou `development`


**2.1. Caso você escolha o develompent, este perfil usará o banco de dados em Memoria, H2 **

+ Você pode acessar a url, senha e login em `src/main/resources/application-development.properties` 


**2.2. Caso escolha o production, este perfil usará o MySql **

+ Crie um banco de dados Mysql 
```bash
create database vendas
```

**2.2.1 Altere o nome de usuário e a senha do mysql de acordo com sua instalação **

+ Abra o `src/main/resources/application-production.properties`

+ Mude `spring.datasource.username` e `spring.datasource.password` de acordo com sua instalação do mysql 


## Executando o aplicativo localmente

Existem várias maneiras de executar um aplicativo Spring Boot na sua máquina local. Uma maneira é executar o método `main` na classe `io.github.monthalcantara.VendasApplication` na sua IDE.

Como alternativa, você pode usar também o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da seguinte maneira:

```shell
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```
## 🛠️ Construído com

Ferramentas que utilizadas para criar o projeto

* [Intellij](https://www.jetbrains.com/pt-br/idea/) - IDE utilizada
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework Java
* [Swagger](https://swagger.io/) - Documentação da API
* [Heroku](https://dashboard.heroku.com/) 

## ✒️ Autor

Montival Junior

* **Sobre mim** -  [Montival Junior](https://monthalcantara.github.io/)


## 📄 Licença

Este projeto está sob licença pública.



---
⌨️ com ❤️ por [Montival Junior](/https://github.com/MonthAlcantara) 😊

