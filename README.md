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


## 4. Create a RESTful API for a to-do list application:
### 4.1. Tạo một task mới
```http
POST /api/tasks
```
- **Mô tả**: Lấy danh sách tất cả các task.
- **Phản hồi**:
- ![create-a-new-task](https://github.com/huynhhongson/img-todo-list-application/blob/main/create-new-task.png)
### 4.2. Lấy danh sách task
```http
GET /api/tasks
```
- **Mô tả**: Lấy danh sách tất cả các task.
- **Phản hồi**:
- ![get-all-task](https://github.com/huynhhongson/img-todo-list-application/blob/main/get-all-task.png)
### 4.3. Lấy thông tin task theo ID
```http
GET /api/tasks/{id}
```
- **Mô tả**: Lấy chi tiết một task theo ID.
- **Phản hồi**:
- ![get-task](https://github.com/huynhhongson/img-todo-list-application/blob/main/get-task.png)

### 4.4. Sửa task theo ID
```http
PUT /api/tasks/{id}
```
- **Mô tả**: Sửa thông tin task theo ID.
- **Phản hồi**:
- ![update-task-detail](https://github.com/huynhhongson/img-todo-list-application/blob/main/update-task-detail.png)
### 4.5. Sửa trạng thái task
```http
PATCH /api/tasks/{id}/status
```
- **Mô tả**: Sửa thông tin task theo ID.
- **Phản hồi**:
- ![update-task-status](https://github.com/huynhhongson/img-todo-list-application/blob/main/update-task-status.png)
### 4.6. xóa task
```http
DELETE /api/tasks/{id}
```
- **Mô tả**: Sửa thông tin task theo ID.
- **Phản hồi**:
- ![delete-task](https://github.com/huynhhongson/img-todo-list-application/blob/main/delete-task.png)


## 5. Task Dependencies:

### 5.1. Tạo phụ thuộc vào các task khác
```http
POST /api/tasks//{taskId}/dependencies/{dependencyId}
```
- **Mô tả**:Tạo sự phụ thuộc giữa task này với task khác.
- **Phản hồi**:
- ![add-dependency](https://github.com/huynhhongson/img-todo-list-application/blob/main/add-dependency.png)

### 5.2. Xóa phụ thuộc giữa task này các task khác
```http
DELETE /api/tasks//{taskId}/dependencies/{dependencyId}
```
- **Mô tả**:Xóa sự phụ thuộc giữa các task với nhau.
- **Phản hồi**:
- ![remove-dependency](https://github.com/huynhhongson/img-todo-list-application/blob/main/remove-dependency.png)

### 5.3. Phụ thuộc vòng
```http
DELETE /api/tasks//{taskId}/dependencies/{dependencyId}
```
- **Mô tả**:Lỗi khi có sự phụ thuộc vòng.
- **Phản hồi**:
- ![error-messages-when-circular-dependencies](https://github.com/huynhhongson/img-todo-list-application/blob/main/error-messages-when-circular-dependencies.png)

## 6. Liên hệ
Nếu có bất kỳ câu hỏi hoặc vấn đề nào, vui lòng liên hệ qua email: `hhongson5011@gmail.com`.

