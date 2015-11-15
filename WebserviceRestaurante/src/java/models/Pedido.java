/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author alexandre
 */
public class Pedido {
    private long id;
    private Usuario usuario;
    private ProdutosPedido produtosPedido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ProdutosPedido getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(ProdutosPedido produtosPedido) {
        this.produtosPedido = produtosPedido;
    }
    
}
