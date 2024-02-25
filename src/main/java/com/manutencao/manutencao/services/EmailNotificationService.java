package com.manutencao.manutencao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manutencao.manutencao.entities.Ticket;

@Service
public class EmailNotificationService {
	
	@Autowired
	private EmailService emailService;
	
	 public void notifyNewTicketCreated(Ticket ticket) {
	        String destinatario = "levysonic@hotmail.com";
	        String assunto = "Novo ticket criado!";
	        String texto = "Um novo ticket foi criado com o ID: " + ticket.getId();
	        emailService.sendMail(destinatario, assunto, texto);
	    }

}
