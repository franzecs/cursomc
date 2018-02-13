package com.ikytus.mc.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.ikytus.mc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage mimeMessage);

	
	
}
