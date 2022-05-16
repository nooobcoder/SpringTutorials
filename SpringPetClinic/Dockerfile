# JDK base image
FROM openjdk:latest

# Copy the jar files from the target directory to the /usr/local/bin directory as 1.jar
COPY release/target/pet-clinic-data-0.0.1.jar /usr/local/bin/data.jar
COPY release/target/pet-clinic-web-0.0.1.jar /usr/local/bin/web.jar

# Execute the jar files "java -jar /usr/local/bin/data.jar" and "java -jar /usr/local/bin/web.jar"
EXPOSE 280

CMD ["java", "-jar", "/usr/local/bin/data.jar"]
CMD ["java", "-jar", "/usr/local/bin/web.jar"]
