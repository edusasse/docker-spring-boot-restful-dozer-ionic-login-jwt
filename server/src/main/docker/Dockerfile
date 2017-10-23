FROM java:8
ADD app-app.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,suspend=n","-jar","/app.jar"]