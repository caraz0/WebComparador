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

@WebServlet("/inicio")
public class Inicio extends HttpServlet {
    
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

        try (DBManager db = new DBManager()) {
            if(request.getParameter("pcSeleccionado") != null){
                count--; //Para que mantenga la muestra de portátiles en el estado que estuviera antes de seleccionar y no aumente el número
                int idPcSelec = Integer.parseInt(request.getParameter("pcSeleccionado"));
                int isSelec= 0;
                for(Ordenador selec: seleccionados){
                    if(selec.getId_producto() == idPcSelec){
                        isSelec = 1;
                    } 
                } 
                if(isSelec == 0){
                    seleccionados.add(db.searchOrdenador(idPcSelec));
                } 
            } 

            if(request.getParameter("eliminarPc") != null){
                if((request.getParameter("eliminarPc")).equals("todos")){
                    //System.out.println();
                    seleccionados.removeAll(seleccionados);
                } else{
                    int idPcElim = Integer.parseInt(request.getParameter("eliminarPc"));
                    for(int i = 0; i < seleccionados.size(); i++){
                        if((seleccionados.get(i)).getId_producto() == idPcElim){
                            seleccionados.remove(i);
                        } 
                    } 
                } 
            } 

            // Obtiene las marcas del catálogo
            List<Marca> marcas = db.listMarcasCantidad();
            List<Ordenador> pcs = db.listOrdenadores();

            System.out.println("Catálogo: se han leído " + marcas.size() + " marcas.");
            // Reenvía la petición a una plantilla JSP, pasando el catálogo como atributo
            request.setAttribute("marcas", marcas);
            request.setAttribute("pcs", pcs);

            List<String> memoria = db.listMemoria();
            request.setAttribute("memoria", memoria);

            List<String> capacidad = db.listCapacidad();
            request.setAttribute("capacidad", capacidad);          
        
            List<String> pantallas = db.listPantalla();
            request.setAttribute("pantalla", pantallas);

            List<String> tipo_mem = db.listTipoMemoria();
            request.setAttribute("tipo_mem", tipo_mem);  
            
            List<String> disco = db.listTipoDisco();
            request.setAttribute("disco", disco);  

            List<String> procesador = db.listProcesadores();
            request.setAttribute("procesador", procesador); 

            if(request.getParameter("count") != null){
                if((request.getParameter("count")).equals("0")){
                    count = 0;
                } else if((request.getParameter("count")).equals("-1")){
                    count = 10000;
                } 
            } else { 
                count = 0;
            } 
            
            request.setAttribute("count",++count);
            // Paso de parámetros a la vista
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inicio.jsp");
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
