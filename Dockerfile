FROM amazoncorretto:17-alpine-jdk

COPY target/seguimiento-gastos-0.0.1-SNAPSHOT.jar api.jar

ENTRYPOINT ["java", "-jar", "/api.jar", "--spring.profiles.active=prod"]
