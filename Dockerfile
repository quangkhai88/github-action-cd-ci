#Multistage build docker file

FROM maven:3.6.3-jdk-8-slim as BUILDER
RUN mkdir -p /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests


FROM openjdk:8-jdk-alpine
COPY --from=BUILDER /project/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
