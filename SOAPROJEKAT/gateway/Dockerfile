FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /workspace/app
COPY . .
RUN mvn install -DskipTests

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-slim as final
WORKDIR /workspace/app
COPY --from=build workspace/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]