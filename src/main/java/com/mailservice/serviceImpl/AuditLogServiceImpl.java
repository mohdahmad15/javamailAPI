package com.mailservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mailservice.entities.AuditLog;
import com.mailservice.repositories.AuditLogRepository;
import com.mailservice.repositories.AuditLogService;
import com.mailservice.service.MailService;

@Service
public class AuditLogServiceImpl implements AuditLogService{
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	@Autowired
	private MailService mailService;
	
	private LocalDateTime lastCheck=LocalDateTime.now().minusMinutes(1/12);
	
	@Scheduled(fixedRate = 5000)
	public void checkAuditLogs() {
		
		List<AuditLog> newLogs=auditLogRepository.findByCreatedAtAfter(lastCheck);
		
		for(AuditLog log: newLogs) {
			mailService.sendEmail("ahmad436334@gmail.com", "Database change Detected:"+log.getOperationType(), "Details: "+log.getDetails());
		}
		
		if(!newLogs.isEmpty()) {
			lastCheck=LocalDateTime.now();
		}
	}

}
