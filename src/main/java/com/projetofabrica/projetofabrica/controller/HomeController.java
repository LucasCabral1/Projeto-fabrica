package com.projetofabrica.projetofabrica.controller;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetofabrica.projetofabrica.model.Contato;
import com.projetofabrica.projetofabrica.model.Doacao;
import com.projetofabrica.projetofabrica.model.Ong;
import com.projetofabrica.projetofabrica.model.Pedido;
import com.projetofabrica.projetofabrica.model.Produto;
import com.projetofabrica.projetofabrica.model.Usuario;
import com.projetofabrica.projetofabrica.repository.DoacaoRepo;
import com.projetofabrica.projetofabrica.repository.OngRepo;
import com.projetofabrica.projetofabrica.repository.ProdutoRepo;
import com.projetofabrica.projetofabrica.repository.UsuarioRepo;
import com.projetofabrica.projetofabrica.service.ContatoService;
import com.projetofabrica.projetofabrica.service.DoacaoService;
import com.projetofabrica.projetofabrica.service.EmailService;
import com.projetofabrica.projetofabrica.service.TroqueService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
public class HomeController {
  @Autowired
  private TroqueService troqueService;
  @Autowired
  private UsuarioRepo repo;
  @Autowired
  private OngRepo ongRepo;
  @Autowired
  private DoacaoRepo doacaoRepo;
  @Autowired
  private ProdutoRepo produtoRepo;
  @Autowired
  private DoacaoService doacaoService;
  @Autowired
  private EmailService email;
  @Autowired
  private ContatoService contatoService;
  public Usuario userLogin;
  private int PontosRestantes;
  private Produto produtoSelecionado;

    @GetMapping("/")
     public String index(Model model){
      model.addAttribute("user", userLogin);
        return "home/index";
     }

    @GetMapping("/login")
     public String index(){
      
        return "login/index";
     }

     @GetMapping("/login/novo")
    public String novo(){
                return "usuario/novo";
    }

     @PostMapping("/logar")
     public String logar(Model model, Usuario user){
      try {
        userLogin = repo.login(user.getEmail(), user.getSenha());
       
        if(userLogin == null){
          throw new Exception();
        }
        model.addAttribute("user", userLogin);
        return "home/index";
        
      }catch(Exception ex){
        model.addAttribute("erro", "Usuario ou senha inválidos");
        
        return "login/index";
      }
        
        
     }

    @PostMapping("/login/criar")
    public String criar(Usuario user, Model model) {
        Usuario userNovo = repo.cadastro(user.getEmail());
        if(userNovo != null){
          model.addAttribute("erro", "Usuário já existe");
          return "usuario/novo";
        }else {
            repo.save(user);
            email.SendEmail(user.getEmail(), "Conta Criada", "Sua conta foi criado com sucesso");
            model.addAttribute("sucesso", "Usuário criado com sucesso");
            return "usuario/novo";
        }

    }

    @GetMapping("/perfil")
     public String perfil(Model model){
        int pontos  = userLogin.getPontos();
    
        int pontosPercent = (int)(pontos * 100 / 500);
        int pontosPercentInter = (int)(pontos * 100 / 1000);
        int pontosPercentExpert = (int)(pontos * 100 / 2000);
        String percent = pontosPercent + "%";
        String percentInter = pontosPercentInter + "%";
        String percentExpert = pontosPercentExpert + "%";


        String iniciante = "Pontos: "+userLogin.getPontos()+"/500";
        String intermediario = "Pontos: "+userLogin.getPontos()+"/1000";
        String expert = "Pontos: "+userLogin.getPontos()+"/2000";

          model.addAttribute("user", userLogin);
          model.addAttribute("iniciante", iniciante);
          model.addAttribute("intermediario", intermediario);
          model.addAttribute("expert", expert);
          model.addAttribute("iniciantePerc", percent);
          model.addAttribute("intermediarioPerc", percentInter);
          model.addAttribute("expertPerc", percentExpert);
        
        return "perfil/index";
     }


    @GetMapping("/doacao")
    public String doacao(Model model){
      model.addAttribute("user", userLogin);
      List<Doacao> doacoes = (List<Doacao>)doacaoRepo.listDoacao(userLogin.getId());
      if(doacoes.size() == 0){
        model.addAttribute("semDados", "sem registros");
      }else {
        
        model.addAttribute("doacoes", doacoes);
    } 
        return "doacao/index";
    }


    @GetMapping("/doacao/novo")
      
    public String novoDoacao(Model model, @ModelAttribute("user") Doacao user){
      model.addAttribute("user", userLogin);
      List<Ong> ongs =  (List<Ong>)ongRepo.listOng();
      model.addAttribute("ongs", ongs);
                return "doacao/novo";
    }

    @GetMapping("/doacao/sobre")
    public String sobreDoacao(Model model){
      model.addAttribute("user", userLogin);
                return "doacao/sobre";
    }

    @GetMapping("/contato")
    public String contato(Model model){
      model.addAttribute("user", userLogin);
                return "contato/index";
    }

    @PostMapping("/doacao/criar")
    public String criar(Doacao doacao) {
      boolean status;
      status = doacaoService.saveDoacao(doacao, userLogin);
      if(status == true){
        return "redirect:/doacao";

      }else {
        return "redirect:/doacao";
      }
    }

    @PostMapping("/contato/criar")
    public String criarContato(Contato contato, Model model) {
      String sucesso = "";
      String error = "";
      boolean status = contatoService.contatoEmail(contato);
    
      if(status == true){
        sucesso = "Sua mensagem foi enviada com sucesso";
        model.addAttribute("sucesso", sucesso);
        return "contato/index";

      }else {
        error = "Ocorreu um erro no envio da mensagem";
        model.addAttribute("erro", error);
        return "contato/index";
      }
    }


    @GetMapping("/doacao/excluir/{id}")
    public String excluir(@PathVariable int id) {
        doacaoRepo.deleteById(id);
        return "redirect:/doacao";
    }

    @GetMapping("/doacao/alterar/{id}")
    public String buscaDoacao(@PathVariable int id, Model model) {
        Usuario user =  repo.findById(id).get();
        try {

            model.addAttribute("usuario", user);
        }catch(Exception ex) {
            return "redirect:/doacao";
        }
        return "doacao/editar";
    }

    @PostMapping("/doacao/alterar/{id}")
    public String alterar(@PathVariable int id, Usuario user) {
        //!repo.exist(id) Custom method
        if(repo.existsById(id)){ 
            repo.save(user);
        }else {
            
            return "redirect:/doacao";
        }
        
        return "doacao/editar";
    }

    @GetMapping("/sobre")
    public String sobre(Model model){
      
        return "sobre/index";
    }



    @GetMapping("/ong")
    public String Ong(Model model){
      model.addAttribute("user", userLogin);
        return "ong/index";
    }

    @GetMapping("/troque")
    public String Troque(Model model){

      model = troqueService.getListProduct(model);
      model.addAttribute("user", userLogin);
      
        return "troque/index";
    }

   

    @RequestMapping(value="/troque/resgatar/{id}", method=RequestMethod.GET)
    public String contactForm(@PathVariable int id, Model model) {
        
       produtoSelecionado =  produtoRepo.findById(id).get();
        PontosRestantes = userLogin.getPontos() - produtoSelecionado.getPreco();
        Pedido pedido = new Pedido();
        
        
        try {
            
            model.addAttribute("produto", produtoSelecionado);
            model.addAttribute("user", userLogin);
            model.addAttribute("pontosRestantes", PontosRestantes);
            model.addAttribute("pedido", pedido);
        }catch(Exception ex) {
            return "redirect:/troque";
        }
        return "troque/resgatar";
    
    }

   

    @RequestMapping(value = "/troque/resgatar/criar", method = RequestMethod.POST)
    public String contactSubmit(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model) {
      pedido.setPontosRestantes(PontosRestantes);
      String error = "";
      model.addAttribute("produto", produtoSelecionado);
      model.addAttribute("user", userLogin);
      model.addAttribute("pontosRestantes", PontosRestantes);
      model.addAttribute("pedido", pedido);

      try {
        
        if(PontosRestantes < 0){
           error = "Pontos insuficientes";
           throw new Exception();
        }else {
          troqueService.savePedido(pedido, userLogin, produtoSelecionado);
          userLogin.setPontos(PontosRestantes);
          repo.save(userLogin);
          model.addAttribute("sucesso", "Pedido realizado com sucesso");
         
          return "troque/resgatar";
        }
       
      } catch (Exception e) {
        model.addAttribute("erro", error);
      
        return "troque/resgatar";
      }
      
        
    }

    @GetMapping("/sair")
     public String sair(Model model){
      userLogin = null;
      return "redirect:/";
     }


     @GetMapping("/politicas")
     public String Politica(Model model){
      
      return "politicas/index";
     }

     @GetMapping("/termos")
     public String Termos(Model model){
      
      return "politicas/termos";
     }


    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String errorPage = "error"; // default
         
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
             
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorPage = "error/404";
                 
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
                errorPage = "error/403";
                 
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // handle HTTP 500 Internal Server error
                errorPage = "error/500";
                 
            }
        }
         
        return errorPage;
    }


}
