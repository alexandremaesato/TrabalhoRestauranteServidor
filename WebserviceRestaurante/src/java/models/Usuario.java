/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author alexandre
 */
public class Usuario implements Serializable{
    private long usuarioid;
    private String nome;
    private String senha;

    public long getIdUsuario() {
        return usuarioid;
    }

    public void setIdUsuario(long usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
