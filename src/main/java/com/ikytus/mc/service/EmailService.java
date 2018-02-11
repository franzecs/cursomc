package com.ikytus.mc.service;

import org.springframework.mail.SimpleMailMessage;

import com.ikytus.mc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
