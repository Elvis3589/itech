/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.web.servlet;

import com.example.dao.DaoDetallesTienda;
import com.example.dao.impl.DaoDetallesTiendaImpl;
import com.example.entidades.DetallesTienda;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DetallesTiendaServlet extends HttpServlet {
    
    DetallesTienda dt = new DetallesTienda();
    DaoDetallesTienda dao = new DaoDetallesTiendaImpl();
    List<DetallesTienda> lista = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_tienda = Integer.parseInt(request.getParameter("id"));
        
        try {
            lista = dao.detallesGet(id_tienda);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        request.setAttribute("detalles", lista);
        request.getRequestDispatcher("detallestienda.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
