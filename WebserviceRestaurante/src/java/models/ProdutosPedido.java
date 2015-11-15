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
public class ProdutosPedido implements Serializable{
    private long id;
    private long idPedido;
    private long idProduto;
    
    private List<Produto> produtos;
    
    
}
