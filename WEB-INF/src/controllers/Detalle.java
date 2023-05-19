package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import model.*;
import util.*;

@WebServlet("/detalle")
public class Detalle extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Obtiene la lista de portátiles seleccionados desde la sesión. Lo crea si no existe.
        HttpSession session = request.getSession();
        List<Ordenador> seleccionados = (List<Ordenador>) session.getAttribute("seleccionados");
        if (seleccionados == null) {
            seleccionados = new ArrayList<Ordenador>();
            session.setAttribute("seleccionados", seleccionados);
        }

        String id = request.getParameter("opinion");
        System.out.println("ID: " + id);


        try (DBManager db = new DBManager()) {

            if(request.getParameter("detallePc")!=null || request.getParameter("opinion")!=null ){
                int detallePc;
                if(id != null){
                    detallePc = Integer.parseInt(id);
                    System.out.println(detallePc);
                    db.setOpinion(detallePc,request.getParameter("mensaje"), Integer.parseInt(request.getParameter("valoracion")));
                } else {
                    detallePc = Integer.parseInt(request.getParameter("detallePc"));
                } 
                
                Ordenador pc = db.searchOrdenador(detallePc);
                Ordenador pcCompleto = db.getOrdenadorCompleto(pc);
                List<String> opiniones = db.listOpiniones(pcCompleto.getId_producto());
                

                System.out.println(pc.getMarca());
                request.setAttribute("opiniones",opiniones);
                request.setAttribute("detallePc",pcCompleto);
            } 
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detalle.jsp");
            rd.forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    } 
}
