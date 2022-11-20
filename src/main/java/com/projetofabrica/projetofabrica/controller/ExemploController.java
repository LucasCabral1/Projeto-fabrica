package com.projetofabrica.projetofabrica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetofabrica.projetofabrica.model.Usuario;
import com.projetofabrica.projetofabrica.repository.UsuarioRepo;


@Controller
public class ExemploController {

    @Autowired
    private UsuarioRepo repo;

    @GetMapping("/usuario")
    public String index (Model model){
        List<Usuario> usuarios = (List<Usuario>)repo.findAll();
        if(usuarios.size() == 0){
            model.addAttribute("semDados", "sem registros");
        }else {

            model.addAttribute("usuarios", usuarios);
        }
        return "usuario/index";
    }

    @GetMapping("/usuario/novo")
    public String novo(){
                return "usuario/novo";
    }

    @PostMapping("/usuario/criar")
    public String criar(Usuario user) {
        repo.save(user);
        return "redirect:/usuario";
    }


    @GetMapping("/usuario/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/usuario";
    }

    @GetMapping("/usuario/{id}")
    public String busca(@PathVariable int id, Model model) {
        Optional<Usuario> user =  repo.findById(id);
        try {

            model.addAttribute("usuario", user.get());
        }catch(Exception ex) {
            return "redirect:/usuario";
        }
        return "usuario/editar";
    }

    @PostMapping("/usuario/alterar/{id}")
    public String alterar(@PathVariable int id, Usuario user) {
        //!repo.exist(id) Custom method
        if(repo.existsById(id)){ 
            repo.save(user);
        }else {
            
            return "redirect:/usuario";
        }
        
        return "usuario/editar";
    }
}
