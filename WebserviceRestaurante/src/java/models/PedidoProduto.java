/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class PedidoProduto implements Serializable{
   private int pedido_produto_id;
    private int pedidoid;
    private int produtoid;
    private int quantidade;
    private Produto produto;
    
    

    public int getPedido_produto_id() {
        return pedido_produto_id;
    }

    public void setPedido_produto_id(int pedido_produto_id) {
        this.pedido_produto_id = pedido_produto_id;
    }

    public int getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(int pedidoid) {
        this.pedidoid = pedidoid;
    }

    public int getProdutoid() {
        return produtoid;
    }

    public void setProdutoid(int produtoid) {
        this.produtoid = produtoid;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    

     
}
