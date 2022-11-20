package com.projetofabrica.projetofabrica.model;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
  
    @Column(name = "Nome")
    private String nome;

    @Column(name = "Email", nullable = false)
    private String email;  

    @Column(name = "Senha", nullable = false)
    private String senha;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "Adm")
    private int adm;

    @Column(name = "Pontos", nullable = true)
    private int pontos = 0;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    public int getAdm() {
        return adm;
    }

    public void setAdm(int adm) {
        this.adm = adm;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
