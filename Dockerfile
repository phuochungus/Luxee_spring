FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build

CMD ["java", "-jar", "build/libs/Luxee-1.jar"]
