package com.projetofabrica.projetofabrica.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projetofabrica.projetofabrica.model.Doacao;



public interface DoacaoRepo extends CrudRepository<Doacao, Integer>{
    @Query(value = "SELECT * from Doação where id_doador = ?1", nativeQuery = true)
    public Collection<Doacao> listDoacao(int id);


}
