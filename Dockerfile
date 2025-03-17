FROM alpine:3.21@sha256:a8560b36e8b8210634f77d9f7f9efd7ffa463e380b75e2e74aff4511df3ef88c AS build

WORKDIR /app

RUN wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.39/bin/apache-tomcat-10.1.39.tar.gz  \
    && tar -xvzf apache-tomcat-10.1.39.tar.gz  \
    && rm apache-tomcat-10.1.39.tar.gz

FROM alpine:3.21@sha256:a8560b36e8b8210634f77d9f7f9efd7ffa463e380b75e2e74aff4511df3ef88c AS final

WORKDIR /app

RUN apk add --no-cache openjdk17

COPY --from=build /app/apache-tomcat-10.1.39 /app/apache-tomcat-10.1.39
COPY build/libs/weatherApp-1.0-SNAPSHOT.war /app/apache-tomcat-10.1.39/webapps/ROOT.war

EXPOSE 8080

ENTRYPOINT ["/app/apache-tomcat-10.1.39/bin/catalina.sh"]

CMD ["run"]
