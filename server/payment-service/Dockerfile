FROM openjdk:17

ENV APP_HOME=/usr/app/

COPY build/libs/*.jar payment-system.jar

EXPOSE 8180

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "payment-system.jar"]