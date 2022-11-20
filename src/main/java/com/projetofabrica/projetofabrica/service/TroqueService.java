package com.projetofabrica.projetofabrica.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projetofabrica.projetofabrica.model.Email;
import com.projetofabrica.projetofabrica.model.Pedido;
import com.projetofabrica.projetofabrica.model.Produto;
import com.projetofabrica.projetofabrica.model.Usuario;
import com.projetofabrica.projetofabrica.repository.PedidoRepo;
import com.projetofabrica.projetofabrica.repository.ProdutoRepo;

@Service
public class TroqueService {
    @Autowired
    ProdutoRepo produtoRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    PedidoRepo pedidoRepo;

    public Model getListProduct(Model model){
        

      List<Produto> naturaProduto = produtoRepo.listProdutoByLoja("Natura");
      List<Produto> americanasProduto = produtoRepo.listProdutoByLoja("Americanas");
      List<Produto> carrefourProduto = produtoRepo.listProdutoByLoja("Carrefour");
      List<Produto> ifoodProduto = produtoRepo.listProdutoByLoja("Ifood");
      
      model.addAttribute("productNatura", naturaProduto);
      model.addAttribute("productAmericanas", americanasProduto);
      model.addAttribute("productCarrefour", carrefourProduto);
      model.addAttribute("productIfood", ifoodProduto);
      

      return model;
        
    }


    public boolean savePedido(Pedido pedido, Usuario userInfo, Produto produto){
        boolean ok = false;
        try{
            Email email = new Email();
          
            email.setTo(pedido.getEmail());
            email.setSubject("Pedido Novo");
            Map<String, Object> model = new HashMap<>();  
            model.put("titulo", "Novo pedido criado");
            model.put("message", "Agradecemos a solicitação, iremos tratar a sua solicitação e entrar em contato em breve. <br><b>Produto:</b> " + produto.getNome() +"<br> <b>Preco:</b> " + produto.getPreco() + "Pontos" + "<br> <b>Nome:</b> " + pedido.getNome() + " " + pedido.getSobrenome());
            email.setModel(model);
            emailService.sendEmailHTML(email);
            Pedido pedidoSave = pedido;
            pedidoSave.setNome(userInfo.getNome());
            pedidoSave.setIdUsuario(userInfo.getId());
            pedidoSave.setIdProduto(produto.getId());
            
         
            pedidoRepo.save(pedidoSave);

            ok = true;
        }catch(Exception ex){
            ok = false;
        }
        

        return ok;
    }
}
