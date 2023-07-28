FROM openjdk:8u111-jdk-alpine

EXPOSE 8099
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "target/mcd-0.0.1-SNAPSHOT.jar"]
