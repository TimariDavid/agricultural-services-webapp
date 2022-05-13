FROM openjdk:17

COPY "./target/agricultural_services.jar" "/application/agricultural_services.jar"

CMD ["java", "-jar", "/application/agricultural_services.jar"]
