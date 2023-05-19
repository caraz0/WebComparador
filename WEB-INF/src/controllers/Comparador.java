package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import java.lang.Math;

import model.*;
import util.*;

@WebServlet("/comparador")
public class Comparador extends HttpServlet {
    
    protected static int count = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Obtiene la lista de portátiles seleccionados desde la sesión. Lo crea si no existe.
        HttpSession session = request.getSession();
        List<Ordenador> seleccionados = (List<Ordenador>) session.getAttribute("seleccionados");
        if (seleccionados == null) {
            seleccionados = new ArrayList<Ordenador>();
            session.setAttribute("seleccionados", seleccionados);
        }

        if(session.getAttribute("user") != null){
            try (DBManager db = new DBManager()) {
            
                List<Ordenador> pcTiendas = db.getTiendasOrdenadores((List<Ordenador>) session.getAttribute("seleccionados"));
                session.setAttribute("seleccionados", pcTiendas);

                List<String> valoraciones = new ArrayList<String>();

                for(Ordenador pc: pcTiendas){ 
                    int countOp = 1;
                    double media = 0;
                    List<String> opiniones = db.listOpiniones(pc.getId_producto());

                    if(opiniones!=null){ 

                        for(String val: opiniones){
                            if(countOp % 2 != 0){
                                media = media + Integer.parseInt(val);
                            } 
                            countOp++;
                        } 

                        if(media!=0){
                            media = Math.round((media/(opiniones.size()/2))*100.0)/100.0;
                            valoraciones.add(media+"★");
                        } else{
                            valoraciones.add("-");
                        } 
                        
                    } else {
                        valoraciones.add("-");
                    }  
                    

                } 

                request.setAttribute("valoraciones", valoraciones);

            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
            }
        } 

        // Paso de parámetros a la vista
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/comparador.jsp");
        rd.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }  
}
