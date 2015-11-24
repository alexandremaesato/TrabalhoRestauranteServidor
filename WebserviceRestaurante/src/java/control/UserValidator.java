package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import models.PedidoProduto;
import models.PedidoProdutoDao;
import models.ProdutoDAO;
import models.Usuario;
import models.Produto;
import models.ProdutosList;
import models.UsuarioDao;
import net.sf.json.JSONObject;


/**
 *
 * @author alexandre
 */
@WebServlet(urlPatterns = {"/UserValidator"})
public class UserValidator extends HttpServlet {

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
                case "login":   
                    HashMap<String, String> hm = new HashMap<String, String>();
                    HashMap<String, Usuario> userHm = new HashMap<String, Usuario>();
                    String usuario = request.getParameter("usuario");
                    String senha = request.getParameter("senha");
                    String msg;
                    
                    UsuarioDao usuarioDao = new UsuarioDao();
                    Usuario user = new Usuario();
                    
                    user = usuarioDao.login(usuario, senha);
                    userHm.put("usuario", user);
                    
                    
                    JSONObject json = JSONObject.fromObject(userHm);
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
                break;
                    
                case "listaProdutos":
                    
                    HashMap<String, ProdutosList> hmp = new HashMap<String, ProdutosList>();
                    ProdutoDAO produtodao = new ProdutoDAO();
                    List<Produto> produtos = produtodao.getProdutos();
                    ProdutosList pds = new ProdutosList();
                    pds.setProdutos(produtos);
                    hmp.put("produtos", pds);
                    JSONObject jsonp = JSONObject.fromObject(hmp);
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(jsonp);
                        out.flush();
                   }
                    break;
                   
                case "inserePedido":
                    HashMap<String, String> hmid = new HashMap<String, String>();
                    
                /*pegar o usuarioid, produtoid, quantidade*/
                   String usuarioid     = request.getParameter("usuario");                  
                   String produtoid     = request.getParameter("produto");                  
                   String quantidade    = request.getParameter("quantidade");
                   String pedido        = request.getParameter("pedido");
                   int userid           = Integer.parseInt(usuarioid);
                   int productid        = Integer.parseInt(produtoid);
                   int quantity         = Integer.parseInt(quantidade);
                   int pedidoid         = Integer.parseInt(pedido);
                   
                   
                   if(pedidoid != 0){                                          
                        pedidoid = Integer.parseInt(pedido);
                        
                        PedidoProduto pedpro = new PedidoProduto();
                        pedpro.setPedidoid(pedidoid);
                        pedpro.setProdutoid(productid);
                        pedpro.setQuantidade(quantity);                        
                        PedidoProdutoDao pedprodao = new PedidoProdutoDao();
                        pedprodao.inserePedidoProduto(pedpro);
                     //   out2.print(pedidoid);
                        
                        hmid.put("pedidoid", pedido);
                        JSONObject jsonid = JSONObject.fromObject(hmid);
                        response.setContentType("application/json");
                         try (PrintWriter out = response.getWriter()) {
                            out.print(jsonid);
                            out.flush();
                        }
                   }
                   else{                                                              
                        Pedido ped = new Pedido();
                        ped.setUsuarioid(userid);
                        ped.setStatus("aguardando");
                        PedidoDAO peddao = new PedidoDAO();                       
                        pedidoid = peddao.inserePedido(ped);
                        
                        PedidoProduto pedpro = new PedidoProduto();
                        pedpro.setPedidoid(pedidoid);
                        pedpro.setProdutoid(productid);
                        pedpro.setQuantidade(quantity);                        
                        PedidoProdutoDao pedprodao = new PedidoProdutoDao();
                        pedprodao.inserePedidoProduto(pedpro);
                       // out2.print(pedidoid);
                        
                        hmid.put("pedidoid", Integer.toString(pedidoid));
                        JSONObject jsonid = JSONObject.fromObject(hmid);
                        response.setContentType("application/json");
                         try (PrintWriter out = response.getWriter()) {
                            out.print(jsonid);
                            out.flush();
                        }
                   }
                    break;
                
                case "buscaPedido":
                    
                    HashMap<String, Pedido> hmpedido = new HashMap<String, Pedido>();
                    PedidoDAO pdao = new PedidoDAO();
                    Pedido pd = new Pedido();
                    String iduser = request.getParameter("usuarioid");
                    pd = pdao.getNovoPedido(Integer.parseInt(iduser));
                    
                    if(pd.getPedidoid() == 0){
                        pdao.setNovoPedido(Integer.parseInt(iduser));
                        pd = pdao.getNovoPedido(Integer.parseInt(iduser));
                    } 
                    
                    hmpedido.put("pedido", pd);
                    JSONObject jsonbp = JSONObject.fromObject(hmpedido);
                    response.setContentType("application/json");
                     try (PrintWriter out = response.getWriter()) {
                        out.print(jsonbp);
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
            Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
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
