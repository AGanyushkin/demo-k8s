FROM openjdk:18.0.1.1-jdk as build

ARG APPLICATION_VERSION=0.0.1
WORKDIR /workspace/

COPY gradlew build.gradle settings.gradle ./
COPY gradle gradle
COPY src src

RUN echo $APPLICATION_VERSION > build.version

RUN chmod +x ./gradlew

RUN microdnf install findutils

RUN ./gradlew clean build -x test

FROM openjdk:18.0.1.1-jdk

ARG APPLICATION_VERSION=0.0.1
ENV JAVA_OPTS="-Xms1G -Xmx1G -server"

WORKDIR /workspace/
COPY --from=build /workspace/build/libs/apigateway-service-$APPLICATION_VERSION.jar artifact.jar

ENV SPRING_PROFILES_ACTIVE="k8s"

EXPOSE 8080
ENTRYPOINT exec java ${JAVA_OPTS} -jar artifact.jar
