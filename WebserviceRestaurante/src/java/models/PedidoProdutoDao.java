/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class PedidoProdutoDao {
    private Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet    = null;
    
    public PedidoProduto getPedidoProduto(int id) throws SQLException{
        String query = "SELECT * FROM PedidoProduto WHERE idPedido = ?";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            PedidoProduto pedidoProduto = new PedidoProduto();
            
            while (resultSet.next()) { 
                pedidoProduto.setPedido_produto_id(resultSet.getInt("idProdutos_pedido"));
                pedidoProduto.setPedidoid(resultSet.getInt("idPedido"));
                pedidoProduto.setProdutoid(resultSet.getInt("idProduto"));
                pedidoProduto.setQuantidade(resultSet.getInt("quantidade"));
            }
            return pedidoProduto;
            
        } finally {
            ptmt.close();
        }
    }
    
    public void inserePedidoProduto(PedidoProduto pedpro) throws SQLException {
       String query = "insert into produtos_pedido (pedidoid, produtoid, quantidade) values (?, ?, ?);";
       int chavegerada = 0;

        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
             
            ptmt.setInt(1, pedpro.getPedidoid());
            ptmt.setInt(2, pedpro.getProdutoid());
            ptmt.setInt(3, pedpro.getQuantidade());
            ptmt.executeUpdate();         
        } finally {
            ptmt.close();
        }
    }
}
