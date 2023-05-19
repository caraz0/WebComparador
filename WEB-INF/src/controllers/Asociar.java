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

@WebServlet("/asociar")
public class Asociar extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("tiendasDel")!=null){
                String[] ids = request.getParameterValues("tiendasDel");
                List<String> idsList = new ArrayList<String>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsList.add(ids[i]);
                } 

                db.deleteVentas(idsList);

            } else if(request.getParameter("add")!=null){

                if((request.getParameter("precio")!=null) && (request.getParameter("url")!=null)){

                    int pc = Integer.parseInt(request.getParameter("id_pc"));
                    int tienda = Integer.parseInt(request.getParameter("id_tienda"));
                    double precio = Double.parseDouble((request.getParameter("precio")).replace(',','.'));
                    String url = request.getParameter("url");
                    
                    int res = db.addPuntoVenta(pc,tienda,precio,url);
                    if(res == 0){
                        request.setAttribute("isAlready",res);
                    } 

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 

            } 
            
            List<Ordenador> pcs = db.listOrdenadores();
            request.setAttribute("pcs", pcs);

            List<Tienda> tiendas = db.listTiendas();
            request.setAttribute("tiendas", tiendas);

            List<Venta> ventas = db.listVentas();
            request.setAttribute("ventas", ventas);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/asociar.jsp");
            rd.forward(request, response);
            
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request,response);
    } 
}
