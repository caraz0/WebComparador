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

@WebServlet("/portatiles")
public class Portatiles extends HttpServlet {
    
    protected static int count = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
                
        try (DBManager db = new DBManager()) {

            if(request.getParameterValues("pc")!=null){
                String[] ids = request.getParameterValues("pc");
                List<Integer> idsInt = new ArrayList<Integer>();

                for(int i=0;i<ids.length;i++){
                    System.out.println(ids[i]);
                    idsInt.add(Integer.parseInt(ids[i]));
                } 

                db.deletePcs(idsInt);

            } else if(request.getParameter("add")!=null){

                if((request.getParameter("marca")!=null) && (request.getParameter("modelo")!=null) && (request.getParameter("memoria")!=null) && 
                (request.getParameter("capacidad")!=null) && (request.getParameter("pantalla")!=null) && (request.getParameter("tipo_mem")!=null) && 
                (request.getParameter("disco")!=null) && (request.getParameter("procesador")!=null)){
                    
                    Ordenador pc = new Ordenador();

                    pc.setMarca(request.getParameter("marca"));
                    pc.setModelo(request.getParameter("modelo"));
                    pc.setMemoria(Integer.parseInt(request.getParameter("memoria")));
                    pc.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
                    pc.setPantalla(Double.parseDouble((request.getParameter("pantalla")).replace(',','.')));
                    pc.setTipoMemoria(request.getParameter("tipo_mem"));
                    pc.setDisco(request.getParameter("disco"));
                    pc.setProcesador(request.getParameter("procesador"));
                    
                    db.addPcs(pc);

                } else{
                    request.setAttribute("InvalidAdd",1);
                } 


            } else if(request.getParameter("modify")!=null){

                if((request.getParameter("marca")!=null) && (request.getParameter("modelo")!=null) && (request.getParameter("memoria")!=null) && 
                (request.getParameter("capacidad")!=null) && (request.getParameter("pantalla")!=null) && (request.getParameter("tipo_mem")!=null) && 
                (request.getParameter("disco")!=null) && (request.getParameter("procesador")!=null) && (request.getParameter("id")!=null)){

                    Ordenador pc = new Ordenador();

                    pc.setId_producto(Integer.parseInt(request.getParameter("id")));
                    pc.setMarca(request.getParameter("marca"));
                    pc.setModelo(request.getParameter("modelo"));
                    pc.setMemoria(Integer.parseInt(request.getParameter("memoria")));
                    pc.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
                    pc.setPantalla(Double.parseDouble((request.getParameter("pantalla")).replace(',','.')));
                    pc.setTipoMemoria(request.getParameter("tipo_mem"));
                    pc.setDisco(request.getParameter("disco"));
                    pc.setProcesador(request.getParameter("procesador"));
                    
                    db.modifyPcs(pc);

                } else{
                    request.setAttribute("InvalidModify",1);
                }   

            } 

            List<Ordenador> pcs;
            if((request.getParameter("searchId") != null)){
                pcs = new ArrayList<Ordenador>();
                pcs.add(db.searchOrdenador(Integer.parseInt(request.getParameter("searchId"))));
            } else{
                pcs = db.listOrdenadores();
            } 
            request.setAttribute("pcs", pcs);

            List<String> marcas = db.listMarcas();
            request.setAttribute("marcas", marcas);

            List<String> tipo_mem = db.listTipoMemoria();
            request.setAttribute("tipo_mem", tipo_mem);  

            List<String> disco = db.listTipoDisco();
            request.setAttribute("disco", disco);

            List<String> procesador = db.listProcesadores();  
            request.setAttribute("procesador", procesador); 



            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/portatiles.jsp");
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
