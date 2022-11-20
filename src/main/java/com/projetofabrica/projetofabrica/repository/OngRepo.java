package com.projetofabrica.projetofabrica.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projetofabrica.projetofabrica.model.Doacao;
import com.projetofabrica.projetofabrica.model.Ong;



public interface OngRepo extends CrudRepository<Ong, Integer>{
    @Query(value = "SELECT * from Ong", nativeQuery = true)
    public Collection<Ong> listOng();


}
