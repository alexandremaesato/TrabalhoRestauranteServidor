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
        
         String opcao = request.getParameter("opcao");
            switch(opcao){
                case "getPedido":
                    HashMap<String, List<Produto>> produtosHM = new HashMap<String, List<Produto>>();
                    int id = Integer.parseInt(request.getParameter("id"));
                    PedidoDAO pedidoDao = new PedidoDAO();
                    Pedido pedido = new Pedido();
                    pedido = pedidoDao.getPedido(id); 
                    if (pedido.hasProduto()){
                       produtosHM.put("produtos", pedido.getProdutos());
                    }else{
                    }
                    JSONObject json = JSONObject.fromObject(produtosHM);
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                }
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