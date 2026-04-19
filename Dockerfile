FROM tomcat:9.0

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy entire webapp folder
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT

# Copy compiled classes
COPY build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

EXPOSE 8080

CMD ["catalina.sh", "run"]