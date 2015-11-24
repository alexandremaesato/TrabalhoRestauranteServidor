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
        String query = "SELECT * FROM Pedido WHERE usuarioid = ? and status = 'aberto'";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, idUsuario);
            resultSet = ptmt.executeQuery();
            Pedido pedido = new Pedido();
            ProdutoDAO produtoDao = new ProdutoDAO();
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                pedido.setPedidoid(resultSet.getInt("pedidoid"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setUsuarioid(resultSet.getInt("usuarioid"));
                pedido.setFormapgto(resultSet.getString("forma_pgto"));
            }
            pedido.setProdutos(getProdutos(pedido.getPedidoid()));
            return pedido;
            
        } finally {
            ptmt.close();
        }
    }
    
    public List<Produto> getProdutos(int idPedido) throws SQLException{
        String query = "SELECT * FROM Produto, Produtos_pedido WHERE pedidoid = ? and Produto.produtoid = Produtos_pedido.produtoid";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, idPedido);
            resultSet = ptmt.executeQuery();
            List<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                Produto produto = new Produto();
                produto.setProdutoid(resultSet.getInt("pedidoid"));
                produto.setNome(resultSet.getString("nome"));
                //produto.setCategoria(resultSet.getString("categoria"));
                produto.setImagem(resultSet.getString("imagem"));
                produto.setValor(resultSet.getDouble("valor"));
                produtos.add(produto);
            }
            return produtos;
            
        } finally {
            ptmt.close();
        }
    }

    public void setPagamento(int pedidoid, String formaPagamento) throws SQLException{
        String query = "UPDATE pedido set status=?, forma_pgto=? where pedidoid=?";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, "pago");
            ptmt.setString(2, formaPagamento);
            ptmt.setInt(3, pedidoid);
            ptmt.executeUpdate();
            
            
        } finally {
            ptmt.close();
        }
    }

    public void setNovoPedido(int id) throws SQLException{
        String query = "INSERT INTO pedido(usuarioid,status) values(?,?)";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, id);
            ptmt.setString(2, "aberto");
            
            ptmt.executeUpdate();
            
            
            
        } finally {
            ptmt.close();
        }
    }
    
     public void zeraPedido(int pedidoid) throws SQLException{
        String query = "DELETE FROM produtos_pedido WHERE pedidoid=?";
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, pedidoid);
            ptmt.executeUpdate();
        } finally {
            ptmt.close();
        }
     }
        public void zeraTabela(int pedidoid) throws SQLException{
        String query = "UPDATE pedido SET status=?,forma_pgto=? WHERE pedidoid=?";
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, "aberto");
            ptmt.setString(2, "");
            ptmt.setInt(3, pedidoid);
            ptmt.executeUpdate();
        } finally {
            ptmt.close();
        }
       
        
    }
    
   
}
