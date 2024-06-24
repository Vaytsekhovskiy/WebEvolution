# Используем официальный образ Tomcat
FROM tomcat:9.0

# Копируем war файл в директорию webapps в Tomcat
COPY target/WebEvolution.war /usr/local/tomcat/webapps/