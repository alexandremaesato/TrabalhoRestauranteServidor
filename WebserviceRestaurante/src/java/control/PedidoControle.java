/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Pedido;
import models.PedidoDAO;
import models.Produto;
import models.ProdutoDAO;
import models.ProdutosList;
import models.Usuario;
import models.UsuarioDao;
import net.sf.json.JSONObject;

/**
 *
 * @author alexandre
 */
@WebServlet(name = "PedidoControle", urlPatterns = {"/PedidoControle"})
public class PedidoControle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
        int id;
        String status;
        String forma;
        PedidoDAO pedidoDao;
        UsuarioDao usuarioDao;
        Pedido pedido= new Pedido();
        JSONObject json;
        
         String opcao = request.getParameter("opcao");
            switch(opcao){
                case "getPedido":
                    HashMap<String, Pedido> produtosHM = new HashMap<String, Pedido>();
                    id = Integer.parseInt(request.getParameter("id"));
                    pedidoDao = new PedidoDAO();
                    //pedido 
                    pedido = pedidoDao.getPedido(id); 
                    
                    produtosHM.put("pedido", pedido);
                    
                    json = JSONObject.fromObject(produtosHM);
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                }
                    /*
                    HashMap<String, ProdutosList> produtosHM = new HashMap<String, ProdutosList>();
                    id = Integer.parseInt(request.getParameter("id"));
                    pedidoDao = new PedidoDAO();
                    pedido = new Pedido();
                    pedido = pedidoDao.getPedido(id); 
                    if (pedido.hasProduto()){
                        ProdutosList produtosList = new ProdutosList();
                        produtosList.setProdutos(pedido.getProdutos());
                       produtosHM.put("produtos", produtosList);
                    }else{
                    }
                    json = JSONObject.fromObject(produtosHM);
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                }
                    */
                break;
                
                case "pagar":
                    HashMap<String, String> pedidoHM = new HashMap<String, String>();
                    id = Integer.parseInt(request.getParameter("id"));
                    status = request.getParameter("status");
                    forma = request.getParameter("forma");
                    pedidoDao = new PedidoDAO();
                    Pedido p = new Pedido();
                    
                    p = pedidoDao.getPedido(id);
                    pedidoDao.setPagamento(p.getPedidoid(), forma);
                    pedidoDao.zeraPedido(p.getPedidoid());
                break;
            }
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
