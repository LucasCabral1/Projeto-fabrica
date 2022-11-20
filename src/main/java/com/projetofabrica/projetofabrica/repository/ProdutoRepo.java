package com.projetofabrica.projetofabrica.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.projetofabrica.projetofabrica.model.Produto;



public interface ProdutoRepo extends CrudRepository<Produto, Integer>{
    @Query(value = "SELECT * from produtos where loja = ?1", nativeQuery = true)
    public List<Produto> listProdutoByLoja(String nome);


}
