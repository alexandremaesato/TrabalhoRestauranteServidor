/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;

/**
 *
 * @author alexandre
 */
public class teste {
    public static void main(String args[]) throws SQLException{
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = usuarioDao.getUsuario(1);
        System.out.println(u.getNome());
        
    }
    
}
