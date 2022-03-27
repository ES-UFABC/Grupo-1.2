# syntax=docker/dockerfile:1
 
FROM openjdk:16-alpine3.13

WORKDIR /agricultura-familiar

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN apk update \
    apk upgrade \
    apk add dos2unix \
    chmod +x mvnw \
    dos2unix mvnw
    
COPY src ./src

CMD ["dos2unix", "/mvnw", "spring-boot:run"]
