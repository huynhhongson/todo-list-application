# Sử dụng JDK 17 làm base image
FROM openjdk:17-jdk-slim

# Đặt biến môi trường cho ứng dụng
ENV SPRING_PROFILES_ACTIVE=prod

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR vào container
COPY target/todo-list-application-0.0.1-SNAPSHOT.jar app.jar

# Expose cổng ứng dụng
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]