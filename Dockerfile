#start with a base image containing java runtime
FROM openjdk:8-jdk-alpin

#add maintainer info
LABEL maintainer="bniass@gmail.com"

#add a volume pointing to /tmp
VOLUME /tmp

#make port 8080 available to the world outside this container
EXPOSE 8080

#the apllication jar file
ARG JAR_FILE=target/scolariteapi-0.0.1-SNAPSHOT.jar

#add the application jar to the container
ADD ${JAR_FILE} scolariteapi.jar

#run the jar file
ENTRYPOINT ["java", "-jar", "/scolariteapi.jar"]