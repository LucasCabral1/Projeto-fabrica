package com.projetofabrica.projetofabrica.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projetofabrica.projetofabrica.model.Doacao;
import com.projetofabrica.projetofabrica.model.Pedido;



public interface PedidoRepo extends CrudRepository<Pedido, Integer>{
    


}
