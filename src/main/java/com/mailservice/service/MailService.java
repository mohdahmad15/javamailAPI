package com.mailservice.service;

public interface MailService {

	public void sendEmail(String to, String subject, String body);
}
