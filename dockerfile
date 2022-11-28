FROM eclipse-temurin:19-jdk-alpine as build
WORKDIR /app
COPY mvnw .
COPY .mvn  ./.mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:19-jre-alpine as prod
COPY --from=build /app/target/*.jar /app/diaryapp.jar
EXPOSE 5555
ENTRYPOINT ["java", "-jar", "/app/diaryapp.jar"]