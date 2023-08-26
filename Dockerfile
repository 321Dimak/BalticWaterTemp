FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY gradle/wrapper/gradle-wrapper.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080