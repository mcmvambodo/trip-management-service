# Build stage
FROM maven:3.8.7-openjdk-18 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


# Running stage
FROM amazoncorretto:21
ARG PROFILE=dev
ARG APP_VERSION=1.0.0
WORKDIR /app
COPY --from=build /build/target/trip-service-*.jar /app/
EXPOSE 8083
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAVA_VERSION=${APP_VERSION}
ENV DB_URL='jdbc:mysql://'mysql':3306/trip_db?createDatabaseIfNotExist=true'
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} trip-service-${JAVA_VERSION}.jar
