FROM openjdk:8-jdk-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN mkdir -p /app/logs
RUN chown appuser /app/logs && chgrp appgroup /app/logs

COPY build/libs/restapi-0.0.1-SNAPSHOT.jar /app/app.jar
RUN chown appuser /app/app.jar && chgrp appgroup /app/app.jar

USER appuser
EXPOSE 9001
WORKDIR /app

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]