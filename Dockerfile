# Sử dụng một hình ảnh Java cơ bản
FROM openjdk:17-alpine

# Đặt thư mục làm việc mặc định của ứng dụng
WORKDIR /app

# Sao chép tất cả các tệp JAR từ thư mục target vào thư mục làm việc của Docker
COPY target/*.jar app.jar

# Mở cổng 8080 để lắng nghe các yêu cầu HTTP
EXPOSE 8080

# Lệnh để chạy ứng dụng khi container được khởi chạy
CMD ["java", "-jar", "app.jar"]
