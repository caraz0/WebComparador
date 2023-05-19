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

@WebServlet("/opiniones")
public class Opiniones extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("opinionesDel")!=null){
                String[] ids = request.getParameterValues("opinionesDel");
                List<Integer> idsInt = new ArrayList<Integer>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsInt.add(Integer.parseInt(ids[i]));
                } 

                db.deleteOpiniones(idsInt);
        

            } else if(request.getParameter("add")!=null){

                if((request.getParameter("idPc")!=null) && (request.getParameter("valor")!=null) && (request.getParameter("mensaje")!=null)){
                    
                    Opinion op = new Opinion();

                    Ordenador pc = db.searchOrdenador(Integer.parseInt(request.getParameter("idPc")));

                    op.setIdPc(Integer.parseInt(request.getParameter("idPc")));
                    op.setMarcaPc(pc.getMarca());
                    op.setModeloPc(pc.getModelo());
                    op.setValor(Double.parseDouble(request.getParameter("valor")));
                    op.setMensaje(request.getParameter("mensaje"));

                    db.addOpiniones(op);

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 

            } 

            List<Opinion> opiniones = db.listOpinionesTotales();
            for(Opinion opFinal: opiniones){
                opFinal.setMarcaPc(db.searchMarca(Integer.parseInt(opFinal.getMarcaPc())));
            } 
            request.setAttribute("opiniones", opiniones);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/opiniones.jsp");
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
