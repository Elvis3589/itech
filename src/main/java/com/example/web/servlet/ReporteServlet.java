package com.example.web.servlet;

import com.example.dao.DaoEvento;
import com.example.dao.impl.DaoEventoImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class ReporteServlet extends HttpServlet {

    DaoEvento dao = new DaoEventoImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        int id = Integer.parseInt(request.getParameter("id"));
        int user = Integer.parseInt(request.getParameter("user"));

        switch (op) {
            case "view":
                JasperPrint jp = dao.reporteEvento(id,user);
                if (jp != null) {
                    try {
                        exportarPDF(response, jp);
                    } catch (JRException ex) {
                        Logger.getLogger(ReporteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el informe.");
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void exportarPDF(HttpServletResponse response, JasperPrint jp) throws IOException, JRException {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=informe.pdf");

            ServletOutputStream outputStream = response.getOutputStream();

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();

            outputStream.flush();
            outputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al exportar el informe a PDF.");
        }
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
