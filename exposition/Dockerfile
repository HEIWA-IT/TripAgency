FROM openjdk:11-jdk-slim
MAINTAINER Dan MAGIER <dan@heiwa-it.com>

ARG BUILD=build
COPY ${BUILD}/libs /app/lib
COPY ${BUILD}/classes /app

RUN groupadd -r spring && useradd -r -g spring spring \
&& mkdir -p /var/log/yellowproject && chown spring: /var/log/yellowproject && chmod a+rwx /var/log/yellowproject \
&& chown spring: /app && chmod -R a+rwx /app
USER spring:spring

ENTRYPOINT ["java","-cp","app:app/lib/*","com.bnpparibas.hackathon.yellowteam.yellowproject.driver.exposition.ExpositionApplication"]