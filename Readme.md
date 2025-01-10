# [<img src="src/main/resources/assets/images/vault.png" height="50" alt="Vault"/>](https://github.com/githiomi/SentriVault) Sentri Vault

<a id="readme-top"></a>

> Developed by <a href="httpS://github.com/githiomi">Daniel Githiomi</a>

## Table of contents

> <details>
><summary>Quick Find</summary>
><ol>
><li><a href="#description">Description</a></li>
><li><a href="#preview">Preview</a></li>
><li><a href="#authentication">Authentication</a></li>
><li><a href="#technologies">Technologies</a></li>
><li><a href="#justification">Justification</a></li>
><li><a href="#requirements">Requirements</a></li>
><li><a href="#installation">Installation</a></li>
><li><a href="#maintainers">Maintainers</a></li>
><li><a href="#contact">Contact</a></li>
><li><a href="#licenses">Licenses</a></li>
></ol>
></details>

## DESCRIPTION

This is a Full Stack Application built using Java and Angular. The REST API has been developed using Java SpringBoot
Framework and the Frontend application developed using Angular.

This application allows users login or create accounts and then user their accounts to read, create, edit, and delete
blog posts. Readers can also like, leave comments, and filter content by tags or categories. The application will also
support user roles (like Admin and Reader) and provide features like search, pagination, and a rich-text editor for blog
posts.

## PREVIEW

The application can be tested using the following resources:

- Postman
- Web Browser (Chrome, Firefox, etc...)
- [Swagger UI (Local)](http://localhost:9000/swagger-ui/index.html)
- [Swagger UI (Docker)](http://localhost:8080/swagger-ui/index.html)

Preview Below...

![Coming Soon](src/main/resources/static/preview.png)

## AUTHENTICATION

The API requires that the client application sends valid credentials for authentication.

The authentication process uses token-based authentication (JWT) to verify users and manage session states.

The application was developed with the use of **Spring Security** and therefore requires authentication.

This Security layer protects the API but allows access to the following:

- [H2 Database Console](http://localhost:8080/h2)
- [Swagger UI Documentation](http://localhost:8080/swagger-ui/index.html)

#### Valid credentials:

Where prompted, user the following credentials:

**This should only be used for testing purposes**

| Username | Password |
|----------|----------|
| admin    | admin123 |

## TECHNOLOGIES

Below are some of the technologies used in the development:

- Java v21
- SpringBoot v3.4.1
- H2 Database
- JPA Repository
- Spring Security
- Lombok
- Swagger UI

## JUSTIFICATION

- `PostgreSQL Database`
    - It is cross-platform and can therefore be used in any operating system.
    - It is high performant - Efficient indexing, parallel query processing, and optimization techniques.
    - Has a lot of community support. Extensive documentation, forums, and active contributors
- `Java`
    - Java is a stable programming language with a lot of frameworks and libraries that make the development easier and
      faster.
    - Third party libraries make development easier and reduce the amount of code required.
- `JPA Repository`
    - This was used because it provides the database functionality of quickly adding records to a database.
    - Reduces the amount of boilerplate code needed for database connectivity.

## REQUIREMENTS

The machine to be used requires the following to be installed and set up:

* Java (Version 17 or higher)
* Docker
* Maven (Optional)
* Browser

## INSTALLATION

Installation can be done in 2 ways:

- Docker
    - Build the DockerFile to create an image
      ```shell
      docker build -t inkvibe:1.0 .
      ```
    - Run the Docker Image
      ```shell
      docker run -p 8080:9000 inkvibe:latest
      ```
    - Access the application using [Docker Local Host](http://localhost:8080/api/v1/blog)

    - Local
        - Git clone [this](https://github.com/githiomi/SentriVault) repository
        - Open your preferred IDE (__Intellij__ is recommended).
        - Run __maven__ to install all dependencies.
        - Run __maven install__ to create a jar file
        - Launch the application
            - Press the play button at Intellij toolbar
            - Or run the following in CMD in the application root directory
          ```shell
          java -jar InkVibe-0.0.1-SNAPSHOT.jar
          ```

## MAINTAINERS

- [Daniel Githiomi (Dhosio)](https://github.com/githiomi)

## CONTACT

Contact me through any of the following channels:

* Website: [portfolio](https://danielgithiomi.com)
* GitHub: [githiomi](https://github.com/githiomi)
* LinkedIn: [danielgithiomi](https://linkedin.com/in/daniel-githiomi/)
* Email: [gmail](danielgithiomi@gmail.com)

## LICENSES

Click the following to access my license page: [License](https://githiomi.github.io/Privacy-Policy/)

> Copyright (c) {2024} DhosioLux.

> <p align="right">(<a href="#readme-top">back to top</a>)</p>