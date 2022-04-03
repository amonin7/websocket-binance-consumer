FROM gradle:jdk17
ARG JAR_FILE=build/libs/main-api-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xms2G","-Xmx2G","-jar","app.jar"]

