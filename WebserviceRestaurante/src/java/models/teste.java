/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class teste {
    public static void main(String args[]) throws SQLException{
//        UsuarioDao usuarioDao = new UsuarioDao();
//        Usuario u = usuarioDao.getUsuario(1);
//        System.out.println(u.getNome());
        
//         HashMap<String, List<Produto>> produtosHM = new HashMap<String, List<Produto>>();
//                    int id = 2;
//                    PedidoDAO pedidoDao = new PedidoDAO();
//                    Pedido pedido = new Pedido();
//                    
//                    
//                    pedido = pedidoDao.getPedido(id);
//                    if (pedido.hasProduto()){
//                       produtosHM.put("produtos", pedido.getProdutos());
//                    }
//        UsuarioDao usuarioDao = new UsuarioDao();
//        PedidoDAO pdao = new PedidoDAO();            
//        Pedido pd = pdao.getPedido(Integer.parseInt("1"));
//                    System.out.println(pd.getUsuarioid());
//                    System.out.println(pd.getPedidoid());
//                    
//    }
        PedidoDAO pDao = new PedidoDAO();
        ArrayList<PedidoProduto> pd = new ArrayList<PedidoProduto>();
        pd = pDao.getPedidoProdutos(1);
        
        System.out.println(pd.get(0).getProduto().getNome());
    
}
}
