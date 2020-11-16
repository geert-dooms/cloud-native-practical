#FROM openjdk:11-jdk
#VOLUME /tmp
#ADD target/*.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

FROM maven:3.6.1-jdk-11-slim AS builder
COPY . /source
WORKDIR /source
RUN mvn -B package -DskipTests

FROM azul/zulu-openjdk-alpine:11-jre
WORKDIR /app
COPY --from=builder /source/target/shopping-list-*.jar /app/application.jar
ENTRYPOINT [ "java", "-jar", "/app/application.jar", "--spring.profiles.active=hsqldb" ]