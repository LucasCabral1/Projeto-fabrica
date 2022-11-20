package com.projetofabrica.projetofabrica.model;

import javax.persistence.*;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Sobrenome")
    private String sobrenome;
    
    @Column(name = "Email")
    private String email;  

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "CEP")
    private int cep; 

    @Column(name = "IdProduto")
    private int IdProduto;

   
    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int idProduto) {
        IdProduto = idProduto;
    }


    @Column(name = "IdUsuario")
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

 
    private int pontosRestantes;

    public int getPontosRestantes() {
        return pontosRestantes;
    }

    public void setPontosRestantes(int pontosRestantes) {
        this.pontosRestantes = pontosRestantes;
    }

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    

    

   
   

    
}
