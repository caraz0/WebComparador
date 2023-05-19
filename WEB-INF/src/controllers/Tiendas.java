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

@WebServlet("/tiendas")
public class Tiendas extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("tiendasDel")!=null){
                String[] ids = request.getParameterValues("tiendasDel");
                List<Integer> idsInt = new ArrayList<Integer>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsInt.add(Integer.parseInt(ids[i]));
                } 

                String res = db.deleteTiendas(idsInt);
                if(res != ""){
                    request.setAttribute("notDel",res);
                } 

            } else if(request.getParameter("add")!=null){

                if((request.getParameter("tienda")!=null) && (request.getParameter("url")!=null)){
                    
                    Tienda t = new Tienda();

                    t.setNombre(request.getParameter("tienda"));
                    t.setUrl(request.getParameter("url"));
                    
                    db.addTiendas(t);

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 


            } else if(request.getParameter("modify")!=null){

                if((request.getParameter("tienda")!=null) && (request.getParameter("url")!=null) && (request.getParameter("id")!=null)){

                    Tienda t = new Tienda();

                    t.setId(Integer.parseInt(request.getParameter("id")));
                    t.setNombre(request.getParameter("tienda"));
                    t.setUrl(request.getParameter("url"));
                    
                    db.modifyTiendas(t);

                } else{
                    request.setAttribute("InvalidModify",1);
                }   

            } 

            List<Tienda> tiendas = db.listTiendas();
            request.setAttribute("tiendas", tiendas);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/tiendas.jsp");
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
