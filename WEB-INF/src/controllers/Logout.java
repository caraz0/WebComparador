package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

                HttpSession session = request.getSession();

                session.invalidate();
                
                request.setAttribute("count","0");
                response.sendRedirect("inicio");
            }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
        doPost(request, response);
    } 
}