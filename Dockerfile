FROM openjdk:latest
ADD target/users-mysql.jar users-mysql.jar
ENTRYPOINT ["java","-jar","users-mysql.jar" ]
EXPOSE 8080