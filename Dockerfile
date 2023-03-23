<<<<<<< HEAD
FROM openjdk:11.0-jdk
WORKDIR /usr/app
VOLUME /opt
COPY ./target/assurance-* ./
EXPOSE 8087
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "assurance-0.0.1-SNAPSHOT.jar"]
=======
FROM openjdk:8u111-jdk-alpine
RUN apk add --update ttf-dejavu && rm -rf /var/cache/apk/*
WORKDIR /usr/app
VOLUME /usr/data_collect
COPY target/mcd-0.0.1-SNAPSHOT.jar ./
EXPOSE 8099
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "mcd-0.0.1-SNAPSHOT.jar"] 
>>>>>>> 05c41268510efbd40db99cb0a6dd1f7d6abd0995
