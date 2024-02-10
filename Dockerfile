FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN  chmod +x gradlew && ./gradlew build -x test

CMD ["java", "-jar", "build/libs/Luxee-1.jar"]
