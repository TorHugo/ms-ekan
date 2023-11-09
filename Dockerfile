FROM openjdk:17

WORKDIR /app

COPY target/ekan-rest-0.0.9.jar /app/ekan-rest-0.0.9.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ekan-rest-0.0.9.jar"]