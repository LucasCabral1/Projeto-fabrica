package com.projetofabrica.projetofabrica.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofabrica.projetofabrica.model.Contato;
import com.projetofabrica.projetofabrica.model.Email;
import com.projetofabrica.projetofabrica.repository.ContatoRepo;

@Service
public class ContatoService {
  @Autowired
  private ContatoRepo contatoRepo;

  @Autowired
  private EmailService emailService;

  public boolean contatoEmail(Contato contato){
    boolean status;
    
    try{
      Email email = new Email();
      email.setTo("doamaisapp@gmail.com");
      email.setSubject("Solicitação de Contato");
      Map<String, Object> model = new HashMap<>();  
      model.put("titulo", "Contato");
      model.put("message", "<b>Nome:</b> " + contato.getNome() + "<br> <b>Email:</b> "+ contato.getEmail() + "<br> <b>Assunto:</b> " + contato.getAssunto() + "<br> <b>Mensagem:</b> " + contato.getMensagem());
      email.setModel(model);
      emailService.sendEmailHTML(email);
      contatoRepo.save(contato);
      return status = true;
    }catch(Exception ex){
      return status = false;
    }
    
    
  }
}
