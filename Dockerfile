FROM openjdk:17-jdk-alpine
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar employee-api.jar
ENTRYPOINT ["java","-jar","/employee-api.jar"]