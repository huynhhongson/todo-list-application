-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 21, 2025 lúc 08:04 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `todolistapplication`
--
CREATE DATABASE todolistapplication;
USE todolistapplication;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` varchar(255) DEFAULT NULL,
  `priority` bit(1) NOT NULL,
  `status` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tasks`
--

INSERT INTO `tasks` (`id`, `description`, `due_date`, `priority`, `status`, `title`) VALUES
(1, 'viec phai lam 1', '20-03-2025 10:30:00', b'1', b'1', 'Viec 1'),
(2, 'viec phai lam 2', '20-03-2025 10:30:00', b'1', b'0', 'Viec 2'),
(3, 'viec phai lam 3', '20-03-2025 10:30:00', b'1', b'0', 'Viec 3'),
(4, 'viec phai lam 4', '21-03-2025 18:30:00', b'0', b'1', 'Viec can 4'),
(5, 'viec phai lam 5', '20-03-2025 17:30:00', b'0', b'0', 'Viec can 5'),
(6, 'viec phai lam 6', '20-03-2025 17:30:00', b'0', b'0', 'Viec can 6'),
(7, 'viec phai lam 7', '20-03-2025 17:30:00', b'0', b'0', 'Viec can 7'),
(8, 'viec phai lam 8', '20-03-2025 17:30:00', b'0', b'0', 'Viec can 8'),
(9, 'xem phim marval', '20-03-2025 17:30:00', b'0', b'0', 'Viec can 9');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `task_dependencies`
--

CREATE TABLE `task_dependencies` (
  `task_id` bigint(20) NOT NULL,
  `dependencies` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `task_dependencies`
--

INSERT INTO `task_dependencies` (`task_id`, `dependencies`) VALUES
(5, 1),
(3, 1),
(3, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `task_dependencies`
--
ALTER TABLE `task_dependencies`
  ADD KEY `FKerlktvi2bud6uauih348u0loj` (`task_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `task_dependencies`
--
ALTER TABLE `task_dependencies`
  ADD CONSTRAINT `FKerlktvi2bud6uauih348u0loj` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
