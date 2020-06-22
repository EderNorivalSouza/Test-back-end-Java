FROM maven:3.6.3-jdk-11 AS build
MAINTAINER Eder Souza
COPY src /var/poi/src
COPY pom.xml /var/poi
RUN mvn -f /var/poi/pom.xml clean package

FROM openjdk:11
COPY --from=build /var/poi/target/poi-0.0.1-SNAPSHOT.jar /usr/local/lib/poi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test","usr/local/lib/poi.jar"]

