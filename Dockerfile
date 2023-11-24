FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17
COPY --from=build /app/target/email-user-0.0.1-SNAPSHOT.jar /app/email-user.jar

CMD ["java", "-jar", "/app/email-user.jar"]