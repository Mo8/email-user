FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17
COPY --from=build /app/target/user-email-0.0.1-SNAPSHOT.jar /app/user-email.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/user-email.jar"]