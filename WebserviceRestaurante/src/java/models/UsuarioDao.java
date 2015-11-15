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

/**
 *
 * @author alexandre
 */
public class UsuarioDao {
     private Connection con = null;
    PreparedStatement ptmt = null;
    private Statement comando;
    ResultSet resultSet = null;
    
    public Usuario getUsuario(int idUsuario) throws SQLException {
        String query = "SELECT * FROM Usuario WHERE idUsuario=?";

        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setInt(1, idUsuario);
            resultSet = ptmt.executeQuery();
            
            while (resultSet.next()) { 
                Usuario usuario = new Usuario();
                Pedido pedido = new Pedido();
                usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                usuario.setNome(resultSet.getString("nome"));
                return usuario;
            }
        } finally {
            ptmt.close();
        }
        return null;

    }
    
    public Usuario login(String usuario, String senha) throws SQLException {
        String query = "SELECT * FROM Usuario WHERE usuario=? and senha=?";

        try {
            con = ConnectionFactory.getConnection();
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, usuario);
            ptmt.setString(2, senha);
            resultSet = ptmt.executeQuery();
            
            while (resultSet.next()) { 
                Usuario user = new Usuario();
                Pedido pedido = new Pedido();
                user.setIdUsuario(resultSet.getInt("idUsuario"));
                user.setNome(resultSet.getString("nome"));
                return user;
            }
        } finally {
            ptmt.close();
        }
        return null;

    }
}

