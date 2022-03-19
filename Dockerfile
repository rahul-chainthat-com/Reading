FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} /project/ReadingIsGood.jar
ENTRYPOINT ["java","-jar","project/ReadingIsGood.jar"]
