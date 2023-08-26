FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/BalticWaterTemp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-cp", "app.jar", "lv.startup.BalticWaterTemp.BalticWaterTempApplication"]
