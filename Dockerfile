FROM tomcat:jre11-temurin-focal
EXPOSE 8080
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]