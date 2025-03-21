# To-Do List Application

## 1. Giới thiệu
To-Do List Application là một RESTful API giúp người dùng quản lý công việc, bao gồm các tính năng tạo, cập nhật, xóa và xem danh sách công việc.

## 2. Công nghệ sử dụng
- **Spring Boot** - Framework chính để xây dựng backend.
- **Spring Data JPA** - Quản lý truy vấn cơ sở dữ liệu.
- **MySQL** - Hệ quản trị cơ sở dữ liệu.
- **Lombok** - Giảm boilerplate code.
- **Spring Validation** - Xác thực dữ liệu đầu vào.

## 3. Cài đặt
### 3.1. Yêu cầu hệ thống
- JDK 17 trở lên
- MySQL 8.0
- Maven 3.8+

### 3.2. Cấu hình database
Cần load file todolistapplication.sql để có database cho việc test các API
Cập nhật thông tin kết nối MySQL trong `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoListApplication
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

### 3.3. Chạy ứng dụng
```sh
mvn spring-boot:run
```
Ứng dụng sẽ chạy tại `http://localhost:8080`.

## 4. API Endpoints

### 4.1. Task Management
#### 4.1.1. Lấy danh sách công việc
```http
GET /api/tasks
```
- **Mô tả**: Lấy danh sách tất cả các công việc.
- **Phản hồi**:
  ```json
  [
    {
      "title": "Viec can 9",
      "description": "xem phim marval",
      "dueDate": "20-03-2025 17:30:00",
      "priority": false,
      "status": false,
      "dependencies": []
    }
  ]
  ```

#### 4.1.2. Lấy thông tin công việc theo ID
```http
GET /api/tasks/{id}
```
- **Mô tả**: Lấy chi tiết một công việc theo ID.
- **Phản hồi**:
- ![Homepage](https://github.com/huynhhongson/img-todo-list-application/blob/main/get-task.png)
  ```json
  {
    "id": 1,
    "title": "Hoàn thành báo cáo",
    "description": "Báo cáo quý 1",
    "dueDate": "2025-03-21T10:00:00"
  }
  ```

#### 4.1.3. Tạo công việc mới
```http
POST /api/tasks
```
- **Yêu cầu**:
  ```json
  {
    "title": "Họp nhóm",
    "description": "Họp dự án lúc 14h",
    "dueDate": "2025-03-22T14:00:00"
  }
  ```
- **Phản hồi**:
  ```json
  {
    "id": 2,
    "title": "Họp nhóm",
    "description": "Họp dự án lúc 14h",
    "dueDate": "2025-03-22T14:00:00"
  }
  ```

#### 4.1.4. Cập nhật công việc
```http
PUT /api/tasks/{id}
```
- **Yêu cầu**:
  ```json
  {
    "title": "Họp nhóm sửa đổi",
    "description": "Dời lịch họp sang 15h",
    "dueDate": "2025-03-22T15:00:00"
  }
  ```
- **Phản hồi**:
  ```json
  {
    "id": 2,
    "title": "Họp nhóm sửa đổi",
    "description": "Dời lịch họp sang 15h",
    "dueDate": "2025-03-22T15:00:00"
  }
  ```

#### 4.1.5. Xóa công việc
```http
DELETE /api/tasks/{id}
```
- **Mô tả**: Xóa một công việc theo ID.
- **Phản hồi**:
  ```json
  {
    "message": "Task deleted successfully"
  }
  ```

## 5. Liên hệ
Nếu có bất kỳ câu hỏi hoặc vấn đề nào, vui lòng liên hệ qua email: `hhongson5011@gmail.com`.

