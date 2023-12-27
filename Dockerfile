FROM openjdk:21
WORKDIR /usr/src/rinha
COPY . /usr/src/rinha
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/rinha-0.0.1-SNAPSHOT.jar"]