package com.projetofabrica.projetofabrica.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projetofabrica.projetofabrica.model.Contato;
import com.projetofabrica.projetofabrica.model.Doacao;



public interface ContatoRepo extends CrudRepository<Contato, Integer>{
    


}
