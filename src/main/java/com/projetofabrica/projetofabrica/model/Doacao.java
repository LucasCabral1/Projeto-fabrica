package com.projetofabrica.projetofabrica.model;

import javax.persistence.*;

@Entity
@Table(name = "Doação")
public class Doacao {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Descricao")
    private String descricao;
    
    @Column(name = "Email")
    private String email;  

    @Column(name = "Peso")
    private String peso; 

    @Column(name = "QtdItem")
    private String qtdItem; 

    @Column(name = "Status")
    private String status; 
    
    @Column(name = "Ong")
    private String ong;

    @Column(name = "IdDoador")
    private int idDoador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(String qtdItem) {
        this.qtdItem = qtdItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

   
   

    
}
