FROM adoptopenjdk:11-jdk

RUN apt update && apt install maven -y
