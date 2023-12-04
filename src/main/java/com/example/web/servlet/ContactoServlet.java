package com.example.web.servlet;

import com.example.dao.DaoContacto;
import com.example.dao.impl.DaoContactoImpl;
import com.example.entidades.Contacto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactoServlet extends HttpServlet {

    private DaoContacto daoContacto;

    @Override
    public void init() throws ServletException {
        super.init();
        daoContacto = new DaoContactoImpl();
    }

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String accion = request.getParameter("accion");
    accion = (accion == null) ? "" : accion;
    String target = "";
    if (accion.equals("REGISTRAR_CONTACTO")) {
        int idDetallesTienda = Integer.parseInt(request.getParameter("id_detalles_tienda"));
        String nombreComprador = request.getParameter("nombre_comprador");
        String emailComprador = request.getParameter("email_comprador");
        String mensaje = request.getParameter("mensaje");

        Contacto contacto = new Contacto();
        contacto.setId_detalles_tienda(idDetallesTienda);
        contacto.setNombre_comprador(nombreComprador);
        contacto.setEmail_comprador(emailComprador);
        contacto.setMensaje(mensaje);
        String resultado = daoContacto.registrarContacto(contacto);
        
        request.setAttribute("mensajeExito", "El contacto fue realizado");

        response.sendRedirect("Detalles?id=" + idDetallesTienda);
        return;
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
