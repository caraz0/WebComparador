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
import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import java.lang.StringBuilder;
import java.util.List;

import model.*;
import util.*;

@WebServlet("/mail")
public class Mail extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {   

        HttpSession session = request.getSession();

        Usuario user = (Usuario) session.getAttribute("user");
        String name = user.getName();
        String mailTo = user.getEmail();
        String mailFrom = "comparaportatiles@gmail.com";
        List<Ordenador> pcs = (List<Ordenador>) session.getAttribute("seleccionados");

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("El resultado de la comparación es el siguiente:\n");
        for(Ordenador pc: pcs){ 

            mensaje.append("Marca: " + pc.getMarca() + " Modelo: " + pc.getModelo() + " RAM (GB): " + pc.getMemoria() + " Capacidad (GB): " + pc.getCapacidad() + " Pantalla(inch): " + pc.getPantalla() + " Tipo RAM: " + pc.getTipoMemoria() + " Tipo Disco: " + pc.getDisco() + " Procesador: " + pc.getProcesador()+"\n");                
                            
        } 
        mensaje.append("\n");
        mensaje.append("Gracias por confiar en nosotros.\n");
        mensaje.append("Un saludo.");

        String msg = mensaje.toString();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        MailUtil.send(mailTo, "Resultado de Comparacion", msg);
        
        request.setAttribute("correo", "Correo enviado al mail asociado a su cuenta.");
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/comparador.jsp");
        rd.forward(request, response);

    } 

    

    
}