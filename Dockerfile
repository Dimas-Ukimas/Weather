FROM tomcat:10-jdk21

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh"]

CMD ["jpda", "run"]
