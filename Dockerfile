FROM openjdk:17-oracle
MAINTAINER jordi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]

# Establecer la imagen base
#FROM openjdk:17-oracle
#
## Establecer el directorio de trabajo dentro del contenedor
#WORKDIR /app
#
## Copiar el archivo JAR de tu aplicación al contenedor
#COPY target/backendDevTest-0.0.1-SNAPSHOT.jar app.jar
#
## Expone el puerto en el que se ejecutará tu aplicación
#EXPOSE 5000
#
## Comando para ejecutar tu aplicación cuando el contenedor se inicie
#CMD ["java", "-jar", "-Dserver.port=5000", "app.jar"]