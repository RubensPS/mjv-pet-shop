FROM openjdk:17

COPY target/mjv-pet-shop-0.0.1-SNAPSHOT.jar mjv-pet-shop-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "mjv-pet-shop-0.0.1-SNAPSHOT.jar"]