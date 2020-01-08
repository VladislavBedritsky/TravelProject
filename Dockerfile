FROM openjdk:latest
ADD target/ex.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080

