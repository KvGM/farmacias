package es.cifpcm.farmaciasgarciak.controller;

import es.cifpcm.farmaciasgarciak.data.ImplementsPersistence;
import es.cifpcm.farmaciasgarciak.data.Persistence;
import es.cifpcm.farmaciasgarciak.model.Farmacia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        try {
            super.init();
            pst.openJSON();
        } catch (Exception ex) {
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
        try {
            pst.closeJSON();
        } catch (Exception ex) {
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            lista = pst.list();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String diaSemana = request.getParameter("diaSemana");

            if (diaSemana != null) {
                int diaSem = Integer.parseInt(diaSemana);
                String dd = ds[diaSem - 1];
                StringBuilder sb = new StringBuilder();
                sb.append("<!DOCTYPE html>");
                sb.append("<html>");
                sb.append("<head>");
                sb.append("<title>Servlet FarmaciasGarciaK</title>");
                sb.append("</head>");
                sb.append("<body>");
                sb.append("<h1>Servlet FarmaciasKevin at " + request.getContextPath() + "</h1>");
                try ( PrintWriter out = response.getWriter()) {
                    sb = imprimirFarmacias(dd, sb);
                    out.println(sb.toString());
                }
            } else {
                try {
                    String opcionTxt = request.getParameter("contesta");
                    int opcion = 0;
                    if (opcionTxt != null) {
                        opcion = Integer.parseInt(opcionTxt);
                    }
                    if (opcion == 1) {//Si
                        Date fechaHoy = new Date();
                        int nDia = 0;
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaHoy);
                        nDia = calendar.get(Calendar.DAY_OF_WEEK);
                        String dd = ds[nDia - 1];
                        StringBuilder sb = new StringBuilder();
                        sb.append("<!DOCTYPE html>");
                        sb.append("<html>");
                        sb.append("<head>");
                        sb.append("<title>Servlet FarmaciasGarciaK</title>");
                        sb.append("</head>");
                        sb.append("<body>");
                        sb.append("<h1>Servlet FarmaciasKevin at " + request.getContextPath() + "</h1>");
                        try ( PrintWriter out = response.getWriter()) {
                            sb = imprimirFarmacias(dd, sb);
                            out.println(sb.toString());
                        }
                    } else if (opcion == 0) {
                        try {
                            processRequest(request, response); //Si la opción es No Lista completa
                        }catch(Exception ex){
                            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        processRequest(request, response); //Lista completa si no hay Dia ni Si ni No
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FarmaciasGarciaK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public StringBuilder imprimirFarmacias(String dd, StringBuilder sb) {
        int i = 1;
        lista = pst.list();
        sb.append("Estas son las farmacias que abren los " + dd + ": <br>");
        for (Farmacia farmacia : lista) {
            try {
                String Horario = (String) Farmacia.class.getField(dd).get(farmacia);
                if (!Horario.equals(", , ,")) {
                    sb.append("<br> [" + (i++) + "]");
                    sb.append(farmacia.toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb;
    }
}
