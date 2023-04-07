
@RestController
public class DoacaoController {
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

}