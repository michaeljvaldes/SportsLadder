# SportsLadder
Repo for the ping pong ladder in the office

Used the following tutorials to get started:

Spring boot: http://javabeat.net/spring-data-jpa/

Spring & angular integration: https://dzone.com/articles/angular-2-and-spring-boot-development-environment

How to run backendcode:

1. cd backend-sports-ladder
2. mvn spring-boot:run
3. Server will be running on localhost:8080/api

How to run frontend code:
1. Download and Install node from https://nodejs.org/en/
2. cd frontend-sports-ladder
3. npm start
4. Code will be running on localhost:4200
5. All requests going to localhost:4200/api will be redirected to localhost:8080/api See proxy.conf.json