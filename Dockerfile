FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/*.jar ./

EXPOSE 8080

ENTRYPOINT ["java","-jar","./Luxee-1.jar"]