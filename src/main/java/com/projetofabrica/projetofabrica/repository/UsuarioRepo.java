package com.projetofabrica.projetofabrica.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projetofabrica.projetofabrica.model.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer>{
    @Query(value = "SELECT CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from usuario where id = :id", nativeQuery = true)
    public boolean exist(int id);


    @Query(value = "SELECT * from usuario where email = ?1 and senha = ?2", nativeQuery = true)
    public Usuario login(String email, String senha);

    @Query(value = "SELECT * from usuario where email = ?1", nativeQuery = true)
    public Usuario cadastro(String email);
}
