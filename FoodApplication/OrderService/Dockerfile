FROM openjdk
WORKDIR usr/lib
ADD ./target/OrderService-0.0.1-SNAPSHOT.jar /usr/lib/OrderService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","OrderService-0.0.1-SNAPSHOT.jar"]