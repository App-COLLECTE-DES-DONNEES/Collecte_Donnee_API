FROM openjdk:8u111-jdk-alpine
RUN apk add --update ttf-dejavu && rm -rf /var/cache/apk/*
WORKDIR /usr/app
VOLUME /usr/data_collect
COPY target/mcd-0.0.1-SNAPSHOT.jar ./
EXPOSE 8099
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "mcd-0.0.1-SNAPSHOT.jar"]
