FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE
ADD target/dockertemplate-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java","-jar","/usr/share/app.jar"]