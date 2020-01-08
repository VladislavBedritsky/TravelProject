FROM openjdk:latest
COPY ./target/ex.jar app.jar
CMD ["java","-jar","/app.jar"]
