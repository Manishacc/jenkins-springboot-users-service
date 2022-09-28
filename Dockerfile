FROM amazoncorretto:17-alpine3.13
WORKDIR /manisha/workspace
COPY target/users-service-0.0.1-SNAPSHOT.JAR ./app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
