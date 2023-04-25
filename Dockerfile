FROM gradle:7.6-jdk17-alpine

WORKDIR /builder
ADD . /builder

RUN gradle build

FROM openjdk:17.0-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/conquer-0.0.1.jar ./conquer.jar
CMD ["java", "-jar", "conquer.jar"]