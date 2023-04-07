
@RestController
public class UsuarioController {
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
}