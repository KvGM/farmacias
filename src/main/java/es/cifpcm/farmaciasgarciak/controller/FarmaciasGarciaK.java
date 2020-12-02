/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.farmaciasgarciak.controller;

import es.cifpcm.farmaciasgarciak.data.ImplementsPersistence;
import es.cifpcm.farmaciasgarciak.data.Persistence;
import es.cifpcm.farmaciasgarciak.model.Farmacia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FarmaciasGarciaK extends HttpServlet {

    
    private static final Persistence pst = new ImplementsPersistence();
    private static List<Farmacia> lista = new ArrayList<>();
    
    String ds[] = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"};
    
    @Override
    public void init() throws ServletException {
        try{
            super.init();
            pst.openJSON();
        }catch (Exception ex){
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            pst.openJSON();
            lista = pst.list();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FarmaciasGarciaK</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FarmaciasGarciaK at " + request.getContextPath() + "</h1>");
            lista.forEach(x -> out.println(x.toString()));
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void destroy(){
        try{
            pst.closeJSON();
        }catch(Exception ex){
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
     
            String diaSemana = request.getParameter("diaSemana");
            
            lista = pst.list();
            
            int diaSem = Integer.parseInt(diaSemana);
            String dd = ds[diaSem - 1];
            
            if(diaSemana != null){
                StringBuilder sb = new StringBuilder();
                sb.append("<!DOCTYPE html>");
                sb.append("<html>");
                sb.append("<head>");
                sb.append("<title>Servlet FarmaciasGarciaK</title>");
                sb.append("</head>");
                sb.append("<body>");
                int i = 1;
                try(PrintWriter out = response.getWriter()){
                    sb.append("Estas son las farmacias que abren los " + dd + ": <br>");
                    for(Farmacia farmacia : lista){
                        try{
                            String Horario = (String) Farmacia.class.getField(dd).get(farmacia); //No encuentra el campo de día
                            if(!Horario.equals(", , ,")){
                                sb.append("<br> [" + (i++) + "]");
                                sb.append(farmacia.toString());
                            }
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                    sb.append("</body>");
                    sb.append("</html>");
                    out.println(sb.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        float utmx = Float.parseFloat(request.getParameter("utmx"));
        float utmy = Float.parseFloat(request.getParameter("utmy"));
        Farmacia farmacia = new Farmacia(request.getParameter("nombre"), request.getParameter("web"), utmx, utmy);
        pst.add(farmacia);
        //destroy(); //Preguntar esto
        try{
            processRequest(request, response);
        }catch (Exception ex){
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
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
