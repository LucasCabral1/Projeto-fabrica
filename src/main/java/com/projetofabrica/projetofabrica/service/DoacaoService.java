package com.projetofabrica.projetofabrica.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofabrica.projetofabrica.model.Doacao;
import com.projetofabrica.projetofabrica.model.Email;
import com.projetofabrica.projetofabrica.model.Usuario;
import com.projetofabrica.projetofabrica.repository.DoacaoRepo;

@Service
public class DoacaoService {
  @Autowired
  private DoacaoRepo doacaoRepo;
  @Autowired
  private EmailService emailService;
    
    public boolean saveDoacao(Doacao doacao, Usuario userInfo){
        boolean ok = false;
        try{

            Email email = new Email();
          
            email.setTo(doacao.getEmail());
            email.setSubject("Nova Doação");
            Map<String, Object> model = new HashMap<>();  
            model.put("titulo", "Novo pedido criado");
            model.put("message", "Solicitação de criação de doação <br> <b>Descrição</b>: " + doacao.getDescricao().trim() + "<br> <b>Peso:</b>" + doacao.getPeso()+ "g <br> <b>Quantidade de Itens:</b>" + doacao.getQtdItem());
            email.setModel(model);
            emailService.sendEmailHTML(email);


            Doacao doacaoSave = doacao;
            doacaoSave.setDescricao(doacao.getDescricao().trim());
            doacaoSave.setIdDoador(userInfo.getId());
            doacaoSave.setStatus("Em análise");
            doacaoRepo.save(doacaoSave);


            // email.SendEmail(doacao.getEmail(), "Doação Criada", "Solicitação de criação de doação \n\n Descrição: " + doacao.getDescricao() + "\n\n Peso:" + doacao.getPeso()+ "g\n\n Quantidade de Itens:" + doacao.getQtdItem());
            ok = true;
        }catch(Exception ex){
            ok = false;
        }
        

        return ok;
    }

    
}
