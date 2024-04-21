FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY ./target/phonebooking-service-0.0.1.jar ./phonebooking.jar

CMD ["java", "-jar", "/app/phonebooking.jar"]