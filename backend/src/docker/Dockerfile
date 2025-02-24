FROM openjdk:17-jdk-alpine
WORKDIR /usr/app
COPY ../../target/novo-assessment-1.0-SNAPSHOT.jar /usr/app/novo-assessment-1.0-SNAPSHOT.jar

ENV DOCKERIZE_VERSION v0.7.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

EXPOSE 8080

CMD dockerize -wait tcp://mysql:3306 -timeout 60s java -jar novo-assessment-1.0-SNAPSHOT.jar