package com.ikytus.mc.util.email;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.domain.ItemPedido;
import com.ikytus.mc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("default.sender")
	private String sender;
		
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj){
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareHtmlMailMessageFromPedido(obj);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareNewPasswordEmail(cliente, newPass);
			sendHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm =  new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	@Profile("dev")
	private MimeMessage prepareHtmlMailMessageFromPedido(Pedido message) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(sender);
			helper.setTo(message.getCliente().getEmail());
			helper.setSubject("Pedido confirmado! Codigo: " + message.getId());
			helper.setSentDate(new Date());
			
			StringBuilder builder = new StringBuilder();
				builder.append("<html>");
				builder.append("<body>");
				builder.append("<h1>" + "Pedido confirmado! Codigo: " + message.getId() + "</h1>");
				builder.append("<span><strong> Data: <strong> </span><span>"+sdf.format(message.getInstante()) +"</span><br>");
				builder.append("<span><strong> Cliente:<strong> </span><span> "+message.getCliente().getNome() +"</span><br>");
				builder.append("<span><strong> Situação Pagamento:<strong> </span><span>"+message.getPagamento()
															.getEstadoPagamento().getDescricao() +"</span><br>");
				builder.append("<strong>Detalhes: </strong><br>");
				for (ItemPedido ip : message.getItens()) {
					builder.append("<li>" + ip.toString()+"</li>");
				}
				builder.append("<span><strong>Valor total: </span><span>"+ nf.format(message.getValorTotal())+ "</span>");
				builder.append("</body>");
				builder.append("</html>");

			helper.setText(builder.toString(), true);
		
		return mimeMessage;
	}
	
	@Profile("dev")
	private MimeMessage prepareNewPasswordEmail(Cliente cliente, String newPass) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
	
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(sender);
			helper.setTo(cliente.getEmail());
			helper.setSubject("Solicitação de nova senha");
			helper.setSentDate(new Date());
			
			StringBuilder builder = new StringBuilder();
				builder.append("<html>");
				builder.append("<body>");
				builder.append("<h1>" + "Nova Senha: " + newPass + "</h1>");
				builder.append("</body>");
				builder.append("</html>");
			helper.setText(builder.toString(), true);
		return mimeMessage;
	}
	
}
