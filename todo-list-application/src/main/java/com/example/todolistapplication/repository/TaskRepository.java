package com.example.todolistapplication.repository;

import com.example.todolistapplication.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Lọc theo tiêu đề (title) + phân trang
    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Lọc theo ngày (do `dueDate` là String nên dùng LIKE để khớp một phần)
    @Query("SELECT t FROM Task t WHERE t.dueDate LIKE CONCAT(:dueDate, '%')")
    Page<Task> findByDueDate(@Param("dueDate") String dueDate, Pageable pageable);
    //loc theo status có ngày tháng năm
    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(t.dueDate LIKE CONCAT(:dueDate, '%'))")
    Page<Task> findByStatusAndDueDate(
            @Param("status") Boolean status,
            @Param("dueDate") String dueDate,
            Pageable pageable
    );


}
