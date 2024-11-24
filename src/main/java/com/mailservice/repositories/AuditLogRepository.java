package com.mailservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailservice.entities.AuditLog;
import java.time.LocalDateTime;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{

	List<AuditLog> findByCreatedAtAfter(LocalDateTime time);
}
