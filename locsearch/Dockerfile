FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} locsearch.jar
EXPOSE 2020
ENTRYPOINT ["java","-jar","/locsearch.jar"]