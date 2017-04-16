    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.oracle.jrockit.jfr.RequestDelegate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Products;

/**
 *
 * @author KATHY KIEU DIEM
 */
public class ControllerProducts extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            String action = request.getParameter("action");
            if(action.equals("Search")){
                String tensp = request.getParameter("txtTenSP");
                 Products sp =  new Products ();
                 List<Product> list = new ArrayList<Product>();
                 list = sp.showProduct(tensp);
                 request.setAttribute  ("listSP", list);
                 RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
                 rd.forward(request,response);
            }else if(action.equals("Delete")){
                String code = request.getParameter("txtCode");
                Products sp = new Products();
                sp.delete(code);
                String url = "ControllerProducts?action=Search&txtTenSP=";
                RequestDispatcher rd =request.getRequestDispatcher(url);
                rd.forward(request,response);
            }else    if(action.equals("Edit")){
                String code = request.getParameter("txtCode");
                String name = request.getParameter("txtName");
                String pri = request.getParameter("txtPrice");
                float fpri = Float.parseFloat(pri);
                Product sp = new Product(code,name,fpri);
                request.setAttribute("SP",sp);
                String url = "editProduct.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request,response);
            }else if(action.equals("Update")){
                String code = request.getParameter("txtCode");
                String name = request.getParameter("txtName");
                String pri = request.getParameter("txtPrice");
                float fpri= Float.parseFloat(pri);
                Products sp = new Products() ;
                sp.update(code, name, fpri);
                String url = "ControllerProducts?action=Search&txtTenSP=";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else if(action.equals("New")){
                String url="newProducts.jsp";
                RequestDispatcher rd= request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else if (action.equals("Insert")){
                String code = request.getParameter("txtCode");
                String name = request.getParameter("txtName");
                String pri = request.getParameter("txtPrice");
                float fpri = Float.parseFloat(pri);
                Products sp = new Products();
                sp.insert(code, name, fpri);
                String url ="ControllerProducts?action=Search&txtTenSP=";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
           
        }catch (Exception e){
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
