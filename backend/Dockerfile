FROM openjdk:17

WORKDIR /home/ubuntu/backend
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} backend.jar
EXPOSE 8080
ENV USE_PROFILE local
ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "backend.jar"]
