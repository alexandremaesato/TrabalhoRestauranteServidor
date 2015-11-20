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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class ProdutoDAO {
    
    private Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet    = null;
    
    public List<Produto> getProdutos() throws SQLException{
        String query = "SELECT * FROM produto";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            resultSet = ptmt.executeQuery();
            List<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                Produto produto = new Produto();
                produto.setProdutoid(resultSet.getInt("produtoid"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getDouble("valor"));
                produto.setImagem(resultSet.getString("imagem"));
                produtos.add(produto);
            }
            return produtos;
            
        } finally {
            ptmt.close();
        }
    }
    
    //Pega uma lista de Produtos pelo id do PedidoProduto
    public List<Produto> getProdutosById(Long id) throws SQLException{
        String query = "SELECT * FROM produto";
        
        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            resultSet = ptmt.executeQuery();
            List<Produto> produtos = new ArrayList<Produto>();
            
            while (resultSet.next()) { 
                Produto produto = new Produto();
                produto.setProdutoid(resultSet.getInt("produtoid"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getDouble("valor"));
                produto.setImagem(resultSet.getString("imagem"));
                produtos.add(produto);
            }
            return produtos;
            
        } finally {
            ptmt.close();
        }
    }
    
}
