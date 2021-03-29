FROM openjdk:11
EXPOSE 8080
COPY ./out/production/GuessGenderDetection/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","/GuessGenderDetection.jar"]
