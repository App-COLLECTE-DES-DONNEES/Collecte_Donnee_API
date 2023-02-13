FROM openjdk:11.0-jdk
WORKDIR /usr/app
VOLUME /opt
COPY ./target/assurance-* ./
EXPOSE 8087
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "assurance-0.0.1-SNAPSHOT.jar"]
