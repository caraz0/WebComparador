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

@WebServlet("/marcas")
public class Marcas extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("marcasDel")!=null){
                String[] ids = request.getParameterValues("marcasDel");
                List<String> idsList = new ArrayList<String>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsList.add((ids[i]));
                } 

                String res = db.deleteMarca(idsList);
                if(res != ""){
                    request.setAttribute("notDel",res);
                } 

            } else if(request.getParameter("add")!=null){

                if((request.getParameter("marca")!=null)){
                    
                    db.addMarca(request.getParameter("marca"));

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 


            } else if(request.getParameter("modify")!=null){

                if((request.getParameter("marca")!=null) && (request.getParameter("id")!=null)){

                    String marcaActual = request.getParameter("id");
                    String marcaNueva = request.getParameter("marca");
                    
                    db.modifyMarca(marcaActual, marcaNueva);

                } else{
                    request.setAttribute("InvalidModify",1);
                }   

            } 

            List<String> marcas = db.listMarcas();
            request.setAttribute("marcas", marcas);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/marcas.jsp");
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
