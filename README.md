# CryptoTracker API

## Read Me (short story)

This repository is **the api source code** for in store price checking

It's writen as a website, involving

- an **API** : Java, Spring Boot REST API
- a **database** : Postgres

and, yeah, it's [MonolithFirst](https://www.martinfowler.com/bliki/MonolithFirst.html)

## Running the application locally

**Requirements**:

- docker (for install postgres)
- postgres 18
- java 18

### Install docker and docker-compose

- Download it [here](https://download.docker.com/mac/stable/Docker.dmg)
- Follow instructions [here](https://docs.docker.com/v17.12/docker-for-mac/install/#install-and-run-docker-for-mac)

**Note** : Using Windows ? Rather follow instructions [here](https://docs.docker.com/v17.12/docker-for-windows/install/)

**Note 2** : Linux ? [here](https://docs.docker.com/v17.12/install/linux/docker-ce/ubuntu/)

### Install and run postgres

#### - Launch your docker-compose

- Launch : **docker-compose -f src/main/resources/docker/localstack-dev.yaml up -d**

#### - Launch pgadmin

- Launch your favourite internet browser and go to **http://localhost:8080**

#### - Delete all data

- Launch : **docker stop crypto-tracker-db pgadmin && docker rm crypto-tracker-db pgadmin && docker volume rm
  crypto-tracker-db_data crypto-tracker-db_dump crypto-tracker-db_pgadmin**

- You can do **docker stop crypto-tracker-db pgadmin && docker system prune -a** to delete all data and docker images

### Run project

**IntelliJ Ultimate**

Create New SpringBoot application configuration
For local development with local DB use spring profiles : local, debug
And set VM options : **-DcoinMarket.api.key="YOUR_COINMARKET_API_KEY"**

**IntelliJ Community**

Create New java application configuration
Set VM options : **-DcoinMarket.api.key="YOUR_COINMARKET_API_KEY" -Dspring.profiles.active=local,debug**

# Contribute

Use Intellij IDE.

Is it recommended to install Lombok Plugin.

We will use Architecture Decision Records,
as [described by Michael Nygard](http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions).

## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

"[Package by feature](https://lkrnac.net/blog/2018/02/package-by-layer-obsolete/)" ("Folder by feature" in non Java
world)

This approach on the other hand groups together files belonging to certain feature within the system

### Database migration / Liquibase

You must use database migration script (see *src/main/resources/db/*)
For convenience, you can use the maven command to generate new liquibase migration script :

```
mvn liquibase:diff
```

### Swagger

You can access to swagger-ui with the following uri : http://localhost:8700/swagger-ui/index.html

### Actuator

**TODO**

To monitor and manage your application

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Admin (Server)](https://codecentric.github.io/spring-boot-admin/current/#getting-started)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
