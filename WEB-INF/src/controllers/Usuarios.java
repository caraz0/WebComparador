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
import at.favre.lib.crypto.bcrypt.BCrypt;

import model.*;
import util.*;

@WebServlet("/usuarios")
public class Usuarios extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
            if(request.getParameter("userAdmin")!=null){
                request.setAttribute("userAdmin","1");
            } 

        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("usuariosDel")!=null){
                String[] ids = request.getParameterValues("usuariosDel");
                List<Integer> idsList = new ArrayList<Integer>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsList.add(Integer.parseInt(ids[i]));
                } 

                db.deleteUsuarios(idsList);
        
            } else if(request.getParameter("add")!=null){

                if((request.getParameter("email")!=null) && (request.getParameter("contrasena")!=null) && (request.getParameter("nombre")!=null)){
                    
                    Usuario user = new Usuario();
                    String password = request.getParameter("contrasena");
                    String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

                    user.setName(request.getParameter("nombre"));
                    user.setContrasena(hashedPassword);
                    user.setEmail(request.getParameter("email"));
                    if(request.getAttribute("userAdmin")!=null){
                        user.setIsAdmin(true);
                    } else {
                        user.setIsAdmin(false);
                    } 
                    
                    db.addUser(user);

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 


            } else if(request.getParameter("modify")!=null){

                if((request.getParameter("email")!=null) && (request.getParameter("contrasena")!=null) && (request.getParameter("nombre")!=null) && 
                (request.getParameter("id")!=null)){

                    Usuario user = new Usuario();

                    String password2 = request.getParameter("contrasena");
                    String hashedPassword2 = BCrypt.withDefaults().hashToString(12, password2.toCharArray());

                    user.setName(request.getParameter("nombre"));
                    user.setContrasena(hashedPassword2);
                    user.setEmail(request.getParameter("email"));
                    user.setId(Integer.parseInt(request.getParameter("id")));
                    if(request.getAttribute("userAdmin")!=null){
                        user.setIsAdmin(true);
                    } else {
                        user.setIsAdmin(false);
                    } 
                    
                    db.modifyUsuarios(user);

                } else{
                    request.setAttribute("InvalidModify",1);
                }   

            } 

            List<Usuario> usuarios = db.listUsuarios();
            request.setAttribute("usuarios", usuarios);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/usuarios.jsp");
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
