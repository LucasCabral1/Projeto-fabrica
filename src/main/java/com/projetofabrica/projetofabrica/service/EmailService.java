package com.projetofabrica.projetofabrica.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.projetofabrica.projetofabrica.model.Email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {
       @Autowired
       private JavaMailSender sender;
       @Autowired   
       private Configuration config;   

       public void SendEmail(String destinatario,String assunto, String mensagem){
              MimeMessage mail = sender.createMimeMessage();
              try {
                  MimeMessageHelper helper = new MimeMessageHelper(mail, true);            
                  helper.setTo(destinatario);
                  helper.setFrom("doamaisapp@gmail.com");
                  helper.setSubject(assunto);
                  helper.setText(mensagem);
              } catch (MessagingException e) {
                  e.printStackTrace();
              } finally {}
              sender.send(mail);
       }

      
    public boolean sendEmailHTML(Email email) {   
       boolean response;  
       MimeMessage message = sender.createMimeMessage();   
       try {   
         // set mediaType   
         MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,   
              StandardCharsets.UTF_8.name());   
         // add attachment   
            
         Template t = config.getTemplate("email-pedido.ftl");   
         String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, email.getModel());   
         helper.setTo(email.getTo());  
        
         helper.setText(html, true);   
         helper.setSubject(email.getSubject());   
         helper.setFrom("doamaisapp@gmail.com");   
         sender.send(message);   
         response = true;
       } catch (MessagingException | IOException | TemplateException e) {   
         response = false;  
       }   
       return response;   
    }   
}
