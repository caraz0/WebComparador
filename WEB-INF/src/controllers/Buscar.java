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


@WebServlet("/buscar")
public class Buscar extends HttpServlet {

    protected static int count = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Obtiene la lista de portátiles seleccionados desde la sesión. Lo crea si no existe.
        HttpSession session = request.getSession();
        List<Ordenador> seleccionados = (List<Ordenador>) session.getAttribute("seleccionados");
        if (seleccionados == null) {
            seleccionados = new ArrayList<Ordenador>();
            session.setAttribute("seleccionados", seleccionados);
        }

        String marca, memoria, capacidad, pantalla, tipo_mem, disco, procesador;
        marca = memoria = capacidad = pantalla = tipo_mem = disco = procesador = "null";
        int todoNull = 0;

        if((request.getParameter("marca") !=null) && (request.getParameter("memoria") !=null) && (request.getParameter("capacidad") !=null) && (request.getParameter("pantalla") !=null) 
            && (request.getParameter("tipo_mem") !=null) && (request.getParameter("disco") !=null) && (request.getParameter("procesador") !=null)){

            marca = request.getParameter("marca");
            memoria = request.getParameter("memoria");
            capacidad = request.getParameter("capacidad");
            pantalla = request.getParameter("pantalla");
            tipo_mem = request.getParameter("tipo_mem");
            disco = request.getParameter("disco");
            procesador = request.getParameter("procesador");
            todoNull = 1;

        } 

        System.out.println(marca + " " + memoria + " " + capacidad + " " + pantalla + " " + tipo_mem + " " + disco + " " + procesador);

        try (DBManager db = new DBManager()) {
            
            List<Ordenador> pcs = db.listOrdenadoresCaracteristicas(marca, memoria, capacidad, pantalla, 
                tipo_mem, disco, procesador);
            if(pcs != null){
                System.out.println(pcs.size());
            } 

            List<Marca> marcas = db.listMarcasCantidad();

            System.out.println("Catálogo: se han leído " + marcas.size() + " marcas.");
            // Reenvía la petición a una plantilla JSP, pasando el catálogo como atributo
            request.setAttribute("marcas", marcas);

            List<String> memoriaL = db.listMemoria();
            request.setAttribute("memoria", memoriaL);

            List<String> capacidadL = db.listCapacidad();
            request.setAttribute("capacidad", capacidadL);          
        
            List<String> pantallasL = db.listPantalla();
            request.setAttribute("pantalla", pantallasL);

            List<String> tipo_memL = db.listTipoMemoria();
            request.setAttribute("tipo_mem", tipo_memL);  
            
            List<String> discoL = db.listTipoDisco();
            request.setAttribute("disco", discoL); 

            List<String> procesadorL = db.listProcesadores();
            request.setAttribute("procesador", procesadorL); 

            request.setAttribute("pcs", pcs);

            if(request.getParameter("count") != null){
                if((request.getParameter("count")).equals("0")){
                    count = 0;
                } 
            } 
            
            request.setAttribute("count",++count);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inicio.jsp");
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
    /*
     *
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String marca = request.getParameter("marca");
        String memoria = request.getParameter("memoria");
        String capacidad = request.getParameter("capacidad");
        String pantalla = request.getParameter("pantalla");
        String tipo_mem = request.getParameter("tipo_mem");
        String disco = request.getParameter("disco");

		doGet(request, response);
	}
     */

     
}
