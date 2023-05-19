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

@WebServlet("/SignupForm")
public class SignupUser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {    

        String name = request.getParameter("user");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        if(name != null){

            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            System.out.println(hashedPassword);
            

            if(!checkEmail(email)){
                request.setAttribute("EmailNotAccepted", true);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
                rd.forward(request,response);
            } 

            if(!password.equals(confirmPassword)){
                request.setAttribute("PasswordsAreNotTheSame", true);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
                rd.forward(request,response);
            } 
            
            if((password.length()<8) || (!checkPassword(password)) || (password.indexOf(" ") != -1)){
                request.setAttribute("NotStrong", true);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
                rd.forward(request,response);
            }

            Usuario user = new Usuario(name, email, hashedPassword, false);

            try (DBManager db = new DBManager()) {
                if(db.getUserByName(user.getName()) != null){
                    request.setAttribute("UserIsAlreadyUsed", true);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
                    rd.forward(request,response);
                }    
                if(db.getUser(user.getEmail()) != null){
                    request.setAttribute("EmailIsAlreadyUsed", true);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
                    rd.forward(request,response);
                } else {
                    db.addUser(user);
                    HttpSession session = request.getSession();
                
                    session.setAttribute("user", user); 
                    response.sendRedirect("inicio");
                }  
            } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                    response.sendError(500);
            }
        } else {
            response.sendRedirect("inicio");
        }  
    } 

    public boolean checkEmail(String email){

        String[] acceptedEmails = {"gmail.com", "gmail.es", "alumnos.uc3m.es", "hotmail.com", "hotmail.es"};

        int posicionArroba = email.indexOf('@');
        
        String domain = email.substring(posicionArroba + 1);

        boolean isAccepted = false;
        for (String acceptedEmail : acceptedEmails){
            if(domain.equals(acceptedEmail)){
                isAccepted = true;
                break;
            } 
        } 
        return isAccepted;
    } 

    public boolean checkPassword(String password){
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            
        }
        if (hasLowerCase && hasUpperCase) return true;
        else return false; 

    } 

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
        doPost(request, response);
    }  
}
