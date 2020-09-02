FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jdk-11.0.1.13-slim as build
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /code
ADD pom.xml /code/pom.xml
ADD src /code/src
RUN mvn spring-boot:run

FROM rabbitmq:3.8.3-management
COPY --from=build /code/definitions.json .
COPY --from=build /code/rabbitmq.conf .
RUN ls -la
COPY definitions.json /etc/rabbitmq/
COPY rabbitmq.conf /etc/rabbitmq/
CMD ["rabbitmq-server"]

