package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/LoginForm")
public class LoginUser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {   

        String name = request.getParameter("name");
        String password = request.getParameter("Password");

        
        try (DBManager db = new DBManager()) {
                
            Usuario user = db.getUserByName(name);
            HttpSession session = request.getSession();
            boolean checkPassword = false;

            if(user != null){
                
                checkPassword = BCrypt.verifyer()
                    .verify(password.toCharArray(), user.getContrasena()).verified;
                //System.out.println(user);
            } 

            if ((user != null) && (checkPassword)) {


                session.setAttribute("user", user);

                if(!user.getIsAdmin()){

                    response.sendRedirect("inicio");
                
                } else{

                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
                    dispatcher.forward(request, response);

                }  

            } else {

                request.setAttribute("error", "Usuario no encontrado. Por favor, inténtalo de nuevo.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                dispatcher.forward(request, response);

            }
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
