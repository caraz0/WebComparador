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

import model.*;
import util.*;

@WebServlet("/admin")
public class Admin extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try (DBManager db = new DBManager()) {

            /*List<Marca> marcas = db.listMarcasCantidad();
            List<Ordenador> pcs = db.listOrdenadores();
            request.setAttribute("marcas", marcas);
            request.setAttribute("pcs", pcs);

            List<String> memoria = db.listMemoria();
            request.setAttribute("memoria", memoria);

            List<String> capacidad = db.listCapacidad();
            request.setAttribute("capacidad", capacidad);          
        
            List<String> pantallas = db.listPantalla();
            request.setAttribute("pantalla", pantallas);

            List<String> tipo_mem = db.listTipoMemoria();
            request.setAttribute("tipo_mem", tipo_mem);  
            
            List<String> disco = db.listTipoDisco();
            request.setAttribute("disco", disco);  

            List<Opinion> opiniones = db.listOpinionesTotales();
            request.setAttribute("opiniones", opiniones);*/

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
            
            rd.forward(request, response);  
            
        } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
        }
        

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    } 
} 