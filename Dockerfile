FROM tomcat:10-jdk21

COPY build/libs/weatherApp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh"]

CMD ["jpda", "run"]
