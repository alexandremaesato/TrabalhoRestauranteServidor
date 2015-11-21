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
public class PedidoDAO {
    private Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet    = null;
    
    public Pedido getPedido(int idUsuario) throws SQLException{
        String query = "SELECT * FROM Pedido WHERE idUsuario = ?";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, idUsuario);
            resultSet = ptmt.executeQuery();
            Pedido pedido = new Pedido();
            ProdutoDAO produtoDao = new ProdutoDAO();
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                pedido.setPedidoid(resultSet.getInt("idPedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setUsuarioid(resultSet.getInt("idUsuario"));
                pedido.setFormapgto(resultSet.getString("formaPagamento"));
            }
            pedido.setProdutos(getProdutos(pedido.getPedidoid()));
            return pedido;
            
        } finally {
            ptmt.close();
        }
    }
    
    public ArrayList<Produto> getProdutos(int idPedido) throws SQLException{
        String query = "SELECT * FROM Produto, Produtos_pedido WHERE idPedido = ?";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, idPedido);
            resultSet = ptmt.executeQuery();
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                Produto produto = new Produto();
                produto.setProdutoid(resultSet.getInt("idPedido"));
                produto.setNome(resultSet.getString("nome"));
                produto.setCategoria(resultSet.getString("categoria"));
                produto.setImagem(resultSet.getString("foto"));
                produto.setValor(resultSet.getDouble("valor"));
                produtos.add(produto);
            }
            return produtos;
            
        } finally {
            ptmt.close();
        }
    }
    
}
