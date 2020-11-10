FROM openjdk:11-jdk-slim
MAINTAINER Dan MAGIER <dan@heiwa-it.com>

ARG BUILD=build
COPY ${BUILD}/lib /app/lib
COPY ${BUILD}/classes /app

RUN groupadd -r spring && useradd -r -g spring spring \
&& mkdir -p /var/log/tripagency && chown spring: /var/log/tripagency && chmod a+rwx /var/log/tripagency \
&& chown spring: /app && chmod -R a+rwx /app
USER spring:spring

ENTRYPOINT ["java","-cp","app:app/lib/*","com.heiwait.tripagency.driver.exposition.ExpositionApplication"]