package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.*;


public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException, NamingException{
        connect();
    }

    /**
     * Abre la conexión con la base de datos 
     *
     */
    private void connect() throws SQLException, NamingException {
        // TODO: program this method
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/Inicio");
		connection = ds.getConnection();
    }

    /**
     * Cierra la conexión con la base de datos si todavía está abierta
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    /* MÉTODOS PARA ORDENADORES */


    /**
     * Buscar ordenador dadas unas características -> FORMULARIO DE BUSCAR
     * 
     */
    public List<Ordenador> listOrdenadoresCaracteristicas(String marca, String memoria, String capacidad, String pantalla, String tipo_mem, 
        String disco, String procesador) throws SQLException {

        ArrayList<Ordenador> pcs = new ArrayList<Ordenador>();

        String query = "SELECT Ordenadores.id, Marcas.marca, modelo, Ordenadores.memoria, capacidad, pantalla, TipoMemoria.memoria, TipoDisco.disco, Procesadores.proc " + 
        "FROM Ordenadores INNER JOIN Marcas ON Ordenadores.marca = Marcas.id INNER JOIN TipoMemoria ON Ordenadores.tipo_mem = TipoMemoria.id INNER JOIN TipoDisco " + 
        "ON Ordenadores.tipo_disco = TipoDisco.id  INNER JOIN Procesadores ON Ordenadores.procesador = Procesadores.id";

        int mar, mem, cap, pant, tmem, dis, proc, primero;
        mar = mem = cap = pant = tmem = dis = proc = primero = 0;
        int count = 1; // Para añadir en la query las características
        

        if(!marca.equals("null")){
            System.out.println(marca);
            mar = 1;
            query = query + " WHERE Marcas.marca = ?";
            primero = 1;
        }
        if(!memoria.equals("null")){
            System.out.println(memoria);
            mem = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "Ordenadores.memoria = ?";
        }     
        if(!capacidad.equals("null")){
            System.out.println(capacidad);
            cap = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "Ordenadores.capacidad = ?";
        }   
        if(!pantalla.equals("null")){
            pant = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "Ordenadores.pantalla = ?";
        }   
        if(!tipo_mem.equals("null")){
            tmem = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "TipoMemoria.memoria = ?";
        }   
        if(!disco.equals("null")){
            dis = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "TipoDisco.disco = ?";
        }   
        if(!procesador.equals("null")){
            proc = 1;
            if(primero == 0){
                primero = 1;
                query = query + " WHERE ";
            } else{
                query = query + " AND ";
            } 
            query = query + "Procesadores.proc = ?";
        }        
        
        System.out.println("List: " + query);
		PreparedStatement stmt = connection.prepareStatement(query);
		if(mar == 1){
            stmt.setString(count, marca);
            count++;
        } 
        if(mem == 1){
            stmt.setString(count, memoria);
            count++;
        } 
        if(cap == 1){
            stmt.setString(count, capacidad);
            count++;
        } 
        if(pant == 1){
            stmt.setString(count, pantalla);
            count++;
        } 
        if(tmem == 1){
            stmt.setString(count, tipo_mem);
            count++;
        } 
        if(dis == 1){
            stmt.setString(count, disco);
            count++;
        } 
        if(proc == 1){
            stmt.setString(count, procesador);
            count++;
        } 
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            Ordenador pc = new Ordenador();

			pc.setId_producto(rs.getInt("Ordenadores.id"));
            pc.setMarca(rs.getString("Marcas.marca"));
            pc.setModelo(rs.getString("modelo"));
            pc.setMemoria(rs.getInt("Ordenadores.memoria"));
            pc.setCapacidad(rs.getInt("capacidad"));
            pc.setPantalla(rs.getDouble("pantalla"));
            pc.setTipoMemoria(rs.getString("TipoMemoria.memoria"));
            pc.setDisco(rs.getString("TipoDisco.disco"));
            pc.setProcesador(rs.getString("Procesadores.proc"));
            
            pcs.add(pc);
 		}
       
        if(primero == 0){
            pcs = null;
        } 
		return pcs;
    }

    /**
     * Mostrar todos los portátiles ordenados por fecha de alta en la tabla
     *
     */
    public List<Ordenador> listOrdenadores() throws SQLException {
        ArrayList<Ordenador> pcs = new ArrayList<Ordenador>();

        String query = "SELECT Ordenadores.id, Marcas.marca, modelo, Ordenadores.memoria, capacidad, pantalla, TipoMemoria.memoria, TipoDisco.disco, Procesadores.proc " + 
        "FROM Ordenadores INNER JOIN Marcas ON Ordenadores.marca = Marcas.id INNER JOIN TipoMemoria ON Ordenadores.tipo_mem = TipoMemoria.id INNER JOIN TipoDisco " + 
        "ON Ordenadores.tipo_disco = TipoDisco.id  INNER JOIN Procesadores ON Ordenadores.procesador = Procesadores.id  ORDER BY fecha_alta DESC";
        
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 
		while (rs.next()){
            Ordenador pc = new Ordenador();

            pc.setId_producto(rs.getInt("Ordenadores.id"));
            pc.setMarca(rs.getString("Marcas.marca"));
            pc.setModelo(rs.getString("modelo"));
            pc.setMemoria(rs.getInt("Ordenadores.memoria"));
            pc.setCapacidad(rs.getInt("capacidad"));
            pc.setPantalla(rs.getDouble("pantalla"));
            pc.setTipoMemoria(rs.getString("TipoMemoria.memoria"));
            pc.setDisco(rs.getString("TipoDisco.disco"));
            pc.setProcesador(rs.getString("Procesadores.proc"));
            
            pcs.add(pc);
            
 		}

        return pcs;
    }

    /**
     * Buscar un ordenador dado una id
     *
     */
    public Ordenador searchOrdenador(int id) throws SQLException {

        String query = "SELECT Ordenadores.id, Marcas.marca, modelo, Ordenadores.memoria, capacidad, pantalla, TipoMemoria.memoria, TipoDisco.disco, Procesadores.proc " + 
        "FROM Ordenadores INNER JOIN Marcas ON Ordenadores.marca = Marcas.id INNER JOIN TipoMemoria ON Ordenadores.tipo_mem = TipoMemoria.id INNER JOIN TipoDisco " + 
        "ON Ordenadores.tipo_disco = TipoDisco.id  INNER JOIN Procesadores ON Ordenadores.procesador = Procesadores.id WHERE Ordenadores.id=?";
       
        PreparedStatement stmt = connection.prepareStatement(query);
        System.out.println(query);
        stmt.setInt(1,id);
		
        ResultSet rs = stmt.executeQuery(); 
        
        Ordenador pc = new Ordenador(); 

		while (rs.next()){
            
            pc.setId_producto(rs.getInt("Ordenadores.id"));
            pc.setMarca(rs.getString("Marcas.marca"));
            pc.setModelo(rs.getString("modelo"));
            pc.setMemoria(rs.getInt("Ordenadores.memoria"));
            pc.setCapacidad(rs.getInt("capacidad"));
            pc.setPantalla(rs.getDouble("pantalla"));
            pc.setTipoMemoria(rs.getString("TipoMemoria.memoria"));
            pc.setDisco(rs.getString("TipoDisco.disco"));
            pc.setProcesador(rs.getString("Procesadores.proc"));
            
 		}

        return pc;
    }

    /**
     * Mostrar las características que faltan de los ordenadores que no salen directamente de la tabla principal (tiendas, precios)
     *
     */
    public Ordenador getOrdenadorCompleto(Ordenador pc) throws SQLException{

        String query = "SELECT id_ordenador,tienda,precio,TiendasOrdenadores.url FROM TiendasOrdenadores INNER JOIN Tiendas ON Tiendas.id = TiendasOrdenadores.id_tienda WHERE id_ordenador=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,pc.getId_producto());
		ResultSet rs = stmt.executeQuery(); 

        List<String> tiendas = new ArrayList<String>();
        List<Double> precios = new ArrayList<Double>(); 
        List<String> urls = new ArrayList<String>();

		while (rs.next()){

            tiendas.add(rs.getString("tienda"));
            precios.add(rs.getDouble("precio"));
            urls.add(rs.getString("TiendasOrdenadores.url"));
            
 		}
        
        pc.setTiendas(tiendas);
        pc.setPrecios(precios);
        pc.setUrls(urls);
        
        return pc;
    
    } 

    /**
     * Borrar una lista de ordenadores dados sus ids correspondientes a la tabla Ordenadores
     *
     */
    public void deletePcs(List<Integer> ids) throws SQLException{

        for(int id: ids){
            String query = "DELETE FROM TiendasOrdenadores WHERE id_ordenador=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            int rowCount = stmt.executeUpdate(); 

            String query3 = "DELETE FROM Opiniones WHERE id_pc=?";
            PreparedStatement stmt3 = connection.prepareStatement(query3);
            stmt3.setInt(1,id);
            rowCount = rowCount + stmt3.executeUpdate(); 

            String query2 = "DELETE FROM Ordenadores WHERE id=?";
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1,id);
            rowCount = rowCount + stmt2.executeUpdate(); 
        } 
    
    } 

    /**
     * Añadir un ordenador con todas sus características a la tabla Ordenadores
     *
     */
    public void addPcs(Ordenador pc) throws SQLException{

        
        String query = "INSERT INTO Ordenadores(marca,modelo,memoria,capacidad,pantalla,tipo_mem,tipo_disco,procesador,fecha_alta) VALUES ((SELECT id " +
        "FROM Marcas WHERE marca=?),?,?,?,?,(SELECT id FROM TipoMemoria WHERE memoria=?),(SELECT id FROM TipoDisco WHERE disco=?), (SELECT id FROM Procesadores WHERE proc=?), now())";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,pc.getMarca());
        stmt.setString(2,pc.getModelo());
        stmt.setInt(3,pc.getMemoria());
        stmt.setInt(4,pc.getCapacidad());
        stmt.setDouble(5,pc.getPantalla());
        stmt.setString(6,pc.getTipoMemoria());
        stmt.setString(7,pc.getDisco());
        stmt.setString(8,pc.getProcesador());


        int rowCount = stmt.executeUpdate(); 
        System.out.println(query + " -> " + rowCount);
    
    } 

    /**
     * Modificar las características de un ordenador dado su id de la tabla Ordenadores
     *
     */
    public void modifyPcs(Ordenador pc) throws SQLException{

        
        String query = "UPDATE Ordenadores SET marca=(SELECT id FROM Marcas WHERE marca=?),modelo=?,memoria=?,capacidad=?,pantalla=?,tipo_mem=(SELECT id FROM TipoMemoria WHERE memoria=?),tipo_disco=(SELECT id FROM TipoDisco WHERE disco=?),procesador=(SELECT id FROM Procesadores WHERE proc=?) WHERE id=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,pc.getMarca());
        stmt.setString(2,pc.getModelo());
        stmt.setInt(3,pc.getMemoria());
        stmt.setInt(4,pc.getCapacidad());
        stmt.setDouble(5,pc.getPantalla());
        stmt.setString(6,pc.getTipoMemoria());
        stmt.setString(7,pc.getDisco());
        stmt.setString(8,pc.getProcesador());
        stmt.setInt(9,pc.getId_producto());


        int rowCount = stmt.executeUpdate(); 
        System.out.println(query + " -> " + rowCount);
    
    } 

    /* MÉTODOS PARA MARCAS */

    /**
     * Mostrar las marcas de la lista
     *
     */
    public List<String> listMarcas() throws SQLException {

        ArrayList<String> marcas = new ArrayList<String>();

        String query = "SELECT marca FROM Marcas";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            marcas.add(rs.getString("marca"));
 		}

        return marcas;
    }

    /**
     * Mostrar las marcas con el número de ordenadores que hay en cada una
     *
     */
    public List<Marca> listMarcasCantidad() throws SQLException {

        ArrayList<Marca> marcas = new ArrayList<Marca>();

        String query = "SELECT Ordenadores.marca, Marcas.marca, COUNT(modelo) FROM Ordenadores INNER JOIN Marcas ON Ordenadores.marca = Marcas.id GROUP BY Ordenadores.marca";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            Marca marca = new Marca();

            marca.setIdMarca(rs.getInt("Ordenadores.marca"));
            marca.setNombre(rs.getString("Marcas.marca"));
            marca.setCantidad(rs.getInt("COUNT(modelo)"));

            marcas.add(marca);
 		}

        return marcas;
    }

    /**
     * Borrar una lista de marcas en función de sus ids de la tabla Marcas
     *
     */
    public String deleteMarca(List<String> ids) throws SQLException{ 

        String res = "";

        for(String id: ids){

            System.out.println(id);
            String query = "SELECT Ordenadores.id FROM Ordenadores INNER JOIN Marcas ON Ordenadores.marca = Marcas.id WHERE Marcas.marca=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                res = res + "" + id + " ";
            } else { 

                String query2 = "DELETE FROM Marcas WHERE marca=?";
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.setString(1,id);
                int rowCount = stmt2.executeUpdate(); 

            } 
        } 

        return res;
    } 

    /**
     * Añadir una marca a la tabla Marcas
     *
     */
    public void addMarca(String marca) throws SQLException{

        String query = "INSERT INTO Marcas(marca) VALUES(?)";
                    
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,marca);

        int rowCount = stmt.executeUpdate(); 
    } 

    /**
     * Modificar una Marca dado su nombre 
     *
     */
    public void modifyMarca(String marcaActual, String marcaNueva) throws SQLException{
        
        String query = "UPDATE Marcas SET marca=? WHERE marca=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,marcaNueva);
        stmt.setString(2,marcaActual);

        int rowCount = stmt.executeUpdate(); 
    } 

    /**
     * Buscar una marca dado su id
     *
     */
    public String searchMarca(int id) throws SQLException {
         
        String query = "SELECT marca FROM Marcas WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();

        String marca = null;

        while(rs.next()){
            marca = rs.getString("marca");
        } 

        return marca;
    } 

    /* MÉTODOS PARA LA MEMORIA */
    
    /**
     * Mostrar las distintas capacidades de memoria RAM de la tabla Ordenadores
     *
     */
    public List<String> listMemoria() throws SQLException {
 
        ArrayList<String> memorias = new ArrayList<String>();

        String query = "SELECT DISTINCT memoria FROM Ordenadores;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String memoria = rs.getString("memoria");

            memorias.add(memoria);
 		}

        return memorias;
    }

    /* MÉTODOS PARA LA CAPACIDAD */

    /**
     * Mostrar las distintas capacidades de la tabla Ordenadores
     *
     */
    public List<String> listCapacidad() throws SQLException {
 
        ArrayList<String> capacidades = new ArrayList<String>();

        String query = "SELECT DISTINCT capacidad FROM Ordenadores;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String capacidad = rs.getString("capacidad");

            capacidades.add(capacidad);
 		}

        return capacidades;
    }

    /* MÉTODOS PARA LA CAPACIDAD */

    /**
     * Mostrar los distintos tamaños de pantalla de la tabla Ordenadores
     *
     */
    public List<String> listPantalla() throws SQLException {
 
        ArrayList<String> pantallas = new ArrayList<String>();

        String query = "SELECT DISTINCT pantalla FROM Ordenadores;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String pantalla = rs.getString("pantalla");

            pantallas.add(pantalla);
 		}

        return pantallas;
    }

    /* MÉTODOS PARA LOS TIPOS DE MEMORIA */

    /**
     * Mostrar los distintos tipos de memoria
     *
     */
    public List<String> listTipoMemoria() throws SQLException {
 
        ArrayList<String> tipoMemorias = new ArrayList<String>();

        String query = "SELECT DISTINCT memoria FROM TipoMemoria;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String tipoMemoria = rs.getString("memoria");

            tipoMemorias.add(tipoMemoria);
 		}

        return tipoMemorias;
    }

    /**
     * Borrar de la tabla TipoMemoria una lista de valores en función de su id
     *
     */
    public String deleteTMem(List<String> ids) throws SQLException{

        String res = "";

        for(String id: ids){

            System.out.println(id);
            String query = "SELECT Ordenadores.id FROM Ordenadores INNER JOIN TipoMemoria ON Ordenadores.tipo_mem = TipoMemoria.id WHERE TipoMemoria.memoria=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                res = res + "" + id + " ";
            } else { 

                String query2 = "DELETE FROM TipoMemoria WHERE memoria=?";
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.setString(1,id);
                int rowCount = stmt2.executeUpdate(); 

            } 
        } 

        return res;
    } 

    /**
     * Añadir a la tabla TipoMemoria una nueva línea
     *
     */
    public void addTMem(String memoria) throws SQLException{

        String query = "INSERT INTO TipoMemoria(memoria) VALUES(?)";
                    
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,memoria);

        int rowCount = stmt.executeUpdate();     
    } 

    /**
     * Modificar de la tabla TipoMemoria un valor dado su tipo anterior
     *
     */
    public void modifyTMem(String memActual, String memNueva) throws SQLException{

        
        String query = "UPDATE TipoMemoria SET memoria=? WHERE memoria=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,memNueva);
        stmt.setString(2,memActual);

        int rowCount = stmt.executeUpdate(); 
    } 

    /* MÉTODOS PARA TIPO DISCO */ 

    /**
     * Mostrar los valores de la tabla TipoDisco
     *
     */
    public List<String> listTipoDisco() throws SQLException {
 
        ArrayList<String> discos = new ArrayList<String>();

        String query = "SELECT DISTINCT disco FROM TipoDisco;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String disco = rs.getString("disco");

            discos.add(disco);
 		}
        return discos;
    }

    /**
     * Borrar de la tabla TipoDisco una lista de valores dado sus ids
     *
     */
    public String deleteDisco(List<String> ids) throws SQLException{ 

        String res = "";

        for(String id: ids){

            System.out.println(id);
            String query = "SELECT Ordenadores.id FROM Ordenadores INNER JOIN TipoDisco ON Ordenadores.tipo_disco = TipoDisco.id WHERE TipoDisco.disco=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                res = res + "" + id + " ";
            } else { 

                String query2 = "DELETE FROM TipoDisco WHERE disco=?";
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.setString(1,id);
                int rowCount = stmt2.executeUpdate(); 

            } 
        } 

        return res;
    } 

    /**
     * Añadir a la tabla TipoDisco un valor
     *
     */
    public void addDisco(String disco) throws SQLException{

        String query = "INSERT INTO TipoDisco(disco) VALUES(?)";
                    
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,disco);

        int rowCount = stmt.executeUpdate();
    } 

    /**
     * Modificar de la tabla TipoDisco un valor dado su tipo anterior
     *
     */
    public void modifyDisco(String discoActual, String discoNuevo) throws SQLException{

        
        String query = "UPDATE TipoDisco SET disco=? WHERE disco=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,discoNuevo);
        stmt.setString(2,discoActual);

        int rowCount = stmt.executeUpdate();
    } 

    /* MÉTODOS PARA PROCESADORES */

    /**
     * Mostrar los valores de la tabla Procesadores
     *
     */
    public List<String> listProcesadores() throws SQLException {
 
        ArrayList<String> procesadores = new ArrayList<String>();

        String query = "SELECT DISTINCT proc FROM Procesadores;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            String procesador = rs.getString("proc");

            procesadores.add(procesador);
 		}

        return procesadores;
    }

    /**
     * Borrar una lista de valores de la tabla Procesadores dado sus ids
     *
     */
    public String deleteProc(List<String> ids) throws SQLException{ 

        String res = "";

        for(String id: ids){

            System.out.println(id);
            String query = "SELECT Ordenadores.id FROM Ordenadores INNER JOIN Procesadores ON Ordenadores.tipo_mem = Procesadores.id WHERE Procesadores.proc=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                res = res + "" + id + " ";
            } else { 

                String query2 = "DELETE FROM Procesadores WHERE proc=?";
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.setString(1,id);
                int rowCount = stmt2.executeUpdate(); 

            } 
        } 

        return res;
    } 

    /**
     * Añadir un valor nuevo a la tabla Procesadores
     *
     */
    public void addProc(String procesador) throws SQLException{

        String query = "INSERT INTO Procesadores(proc) VALUES(?)";
                    
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,procesador);

        int rowCount = stmt.executeUpdate(); 
    } 

    /**
     * Modificar un objeto de la tabla Procesadores en función de su valor antiguo
     *
     */
    public void modifyProc(String procActual, String procNuevo) throws SQLException{

        
        String query = "UPDATE Procesadores SET proc=? WHERE proc=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,procNuevo);
        stmt.setString(2,procActual);

        int rowCount = stmt.executeUpdate(); 
    } 

    /* MÉTODOS PARA USUARIOS */

    /**
     * Añadir un nuevo usuario a la tabla Usuarios
     *
     */
    public void addUser(Usuario user) throws SQLException{
        
        String query = "INSERT INTO Usuarios(nombre, email, isAdmin, contrasena) VALUES (?,?,?,?)";

        try(PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1,user.getName());
            stmt.setString(2,user.getEmail());
            if(user.getIsAdmin()){
                stmt.setInt(3,1);
            } else{
                stmt.setInt(3,0);
            } 
            stmt.setString(4,user.getContrasena());

            int rowCount = stmt.executeUpdate(); 
        } 
    }

    /**
     * Obtener un usuario dado su email
     *
     */
    public Usuario getUser(String email) throws SQLException{

        System.out.println(email);
        String query = "SELECT * FROM Usuarios WHERE email = ? ";
        Usuario user = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            System.out.println(query);
            stmt.setString(1,email);
            
            ResultSet rs = stmt.executeQuery(); 
             
            
            if (rs.next()){
                
                user = new Usuario(rs.getString("nombre") ,rs.getString("email"), rs.getString("contrasena"), rs.getBoolean("isAdmin"));
                System.out.println(rs.getString("email") + rs.getString("contrasena") + rs.getBoolean("isAdmin"));
            }
        } 
        return user;
    } 

    /**
     * Obtener un usuario dado su nombre
     *
     */
    public Usuario getUserByName(String name) throws SQLException{

        String query = "SELECT * FROM Usuarios WHERE nombre = ? ";
        Usuario user = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            System.out.println(query);
            stmt.setString(1,name);
            
            ResultSet rs = stmt.executeQuery(); 
             
            
            if (rs.next()){
                
                user = new Usuario(rs.getString("nombre") ,rs.getString("email"), rs.getString("contrasena"), rs.getBoolean("isAdmin"));
                System.out.println(rs.getString("email") + rs.getString("contrasena") + rs.getBoolean("isAdmin"));
            }
        } 
        return user;
    } 

    /**
     * Mostrar la lista completa de usuarios
     *
     */
    public List<Usuario> listUsuarios() throws SQLException {
 
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String query = "SELECT * FROM Usuarios;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){

            Usuario user = new Usuario();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("nombre"));
            user.setEmail(rs.getString("email"));
            user.setContrasena(rs.getString("contrasena"));
            if(rs.getInt("isAdmin") == 1){
                user.setIsAdmin(true);
            } else{
                user.setIsAdmin(false);
            } 
            
            usuarios.add(user);
 		}

        return usuarios;
    }

    /**
     * Borrar una lista de usuarios dado sus ids
     *
     */
    public void deleteUsuarios(List<Integer> ids) throws SQLException{

        for(int id: ids){
  
            String query = "DELETE FROM Usuarios WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            int rowCount = stmt.executeUpdate(); 
            
        } 
    } 

    /**
     * Modificar un usuario ya existente
     *
     */
    public void modifyUsuarios(Usuario user) throws SQLException{
        
        String query = "UPDATE Usuarios SET nombre=?, email=?, contrasena=? WHERE id=?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1,user.getName());
        stmt.setString(2,user.getEmail());
        stmt.setString(3,user.getContrasena());
        stmt.setInt(4,user.getId());

        int rowCount = stmt.executeUpdate(); 
    } 

    /* MÉTODOS PARA TIENDASORDENADORES */ 

    /**
     * Obtener las tiendas asociadas a cada ordenador
     *
     */
    public List<Ordenador> getTiendasOrdenadores(List<Ordenador> pcs) throws SQLException{

        List<Ordenador> pcsTiendas = new ArrayList<Ordenador>();

        for(Ordenador pc: pcs){ 
            Ordenador pcCompleto = getOrdenadorCompleto(pc);
            pcsTiendas.add(pcCompleto);
        } 
        
        return pcsTiendas;
    } 

    /**
     * Mostrar las asociaciones entre tiendas y ordenadores
     *
     */
    public List<Venta> listVentas() throws SQLException {
 
        ArrayList<Venta> ventas = new ArrayList<Venta>();

        String query = "SELECT * FROM TiendasOrdenadores;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            Venta venta = new Venta();

            venta.setIdPc(rs.getInt("id_ordenador"));
            venta.setIdTienda(rs.getInt("id_tienda"));
            venta.setPrecio(rs.getDouble("precio"));
            venta.setUrl(rs.getString("url"));

            ventas.add(venta);
 		}
        return ventas;
    }

    /**
     * Borrar una lista de asociaciones tiendas-ordenadores dado sus ids
     *
     */
    public void deleteVentas(List<String> ids) throws SQLException{

        for(String id: ids){
            
            String query = "DELETE FROM TiendasOrdenadores WHERE url=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            int rowCount = stmt.executeUpdate(); 
        
        } 
    } 

    /**
     * Añadir una asociación tienda-ordenador a la tabla TiendasOrdenadores
     *
     */
    public int addPuntoVenta(int idPc,int idTienda,double precio,String url) throws SQLException{

        String query = "SELECT id_ordenador FROM TiendasOrdenadores WHERE id_ordenador=? AND id_tienda=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,idPc);
        stmt.setInt(2,idTienda);
        ResultSet rs = stmt.executeQuery(); 

        int rowCount;

		if (rs.next()){
            return 0;
        } else{
            String query2 = "INSERT INTO TiendasOrdenadores VALUES (?,?,?,?)";

            PreparedStatement stmt2 = connection.prepareStatement(query2);

            stmt2.setInt(1,idPc);
            stmt2.setInt(2,idTienda);
            stmt2.setDouble(3,precio);
            stmt2.setString(4,url);

            rowCount = stmt2.executeUpdate(); 
        } 
        return rowCount;
    } 

    /* MÉTODOS PARA TIENDAS */

    /**
     * Mostrar los valores de la tabla Tiendas
     *
     */
    public List<Tienda> listTiendas() throws SQLException {
 
        ArrayList<Tienda> tiendas = new ArrayList<Tienda>();

        String query = "SELECT * FROM Tiendas;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 

		while (rs.next()){
            Tienda tienda = new Tienda();

            tienda.setId(rs.getInt("id"));
            tienda.setNombre(rs.getString("tienda"));
            tienda.setUrl(rs.getString("url"));

            tiendas.add(tienda);
 		}
        return tiendas;
    }

    /**
     * Borrar una lista de tiendas dado sus ids
     *
     */
    public String deleteTiendas(List<Integer> ids) throws SQLException{

        String res = "";

        for(int id: ids){
            String query = "SELECT id_ordenador FROM TiendasOrdenadores WHERE id_tienda=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery(); 
            
            if(rs.next()){
                res = res + "" + id + " ";
            } else { 
                String query2 = "DELETE FROM Tiendas WHERE id=?";
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.setInt(1,id);
                int rowCount = stmt2.executeUpdate(); 
            } 
        } 

        return res;
    } 

    /**
     * Añadir una tienda a la tabla Tiendas
     *
     */
    public void addTiendas(Tienda t) throws SQLException{
        
        String query = "INSERT INTO Tiendas(tienda,url) VALUES (?,?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        System.out.println(t.getNombre());
        System.out.println(t.getUrl());

        stmt.setString(1,t.getNombre());
        stmt.setString(2,t.getUrl());

        int rowCount = stmt.executeUpdate(); 
    } 

    /**
     * Modificar una tienda ya existente
     *
     */
    public void modifyTiendas(Tienda t) throws SQLException{

        
        String query = "UPDATE Tiendas SET tienda=?, url=? WHERE id=?";

        PreparedStatement stmt = connection.prepareStatement(query);
        System.out.println(t.getNombre());
        System.out.println(t.getUrl());
        System.out.println(t.getId());

        stmt.setString(1,t.getNombre());
        stmt.setString(2,t.getUrl());
        stmt.setInt(3,t.getId());


        int rowCount = stmt.executeUpdate(); 
    } 

    /* MÉTODOS PARA OPINIONES */

    /**
     * Mostrar las opiniones de todos los ordenadores
     *
     */
    public List<String> listOpiniones(int idPc) throws SQLException {
 
        List<String> opiniones = new ArrayList<String>();

        String query = "SELECT valor, mensaje FROM Opiniones INNER JOIN Ordenadores ON id_pc=Ordenadores.id WHERE id_pc=?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,idPc);
		ResultSet rs = stmt.executeQuery(); 
		
        while (rs.next()){

            opiniones.add(rs.getInt("valor") + "");
            opiniones.add(rs.getString("mensaje"));
 		}

        return opiniones;
    }

    /**
     * Mostrar las opiniones correspondientes a cada ordenador
     *
     */
    public List<Opinion> listOpinionesTotales() throws SQLException {
 
        List<Opinion> opiniones = new ArrayList<Opinion>();

        String query = "SELECT Opiniones.id, Ordenadores.id, Ordenadores.marca, Ordenadores.modelo, valor, mensaje FROM Opiniones INNER JOIN Ordenadores ON id_pc=Ordenadores.id;";
        PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(); 
		
        while (rs.next()){
            System.out.println("entra");
            Opinion op = new Opinion();

            op.setId(rs.getInt("Opiniones.id"));
            op.setIdPc(rs.getInt("Ordenadores.id"));
            op.setMarcaPc(rs.getString("Ordenadores.marca")+"");
            op.setModeloPc(rs.getString("Ordenadores.modelo"));
            op.setValor(rs.getInt("valor"));
            op.setMensaje(rs.getString("mensaje"));

            opiniones.add(op);
 		}
        return opiniones;
    }

    /**
     * Borrar una lista de opiniones dada sus ids
     *
     */
    public void deleteOpiniones(List<Integer> ids) throws SQLException{

        for(int id: ids){
            
            String query = "DELETE FROM Opiniones WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            int rowCount = stmt.executeUpdate(); 
        
        } 
    } 

    /**
     * Añadir una opinion nueva 
     *
     */
    public void addOpiniones(Opinion op) throws SQLException{

        
        String query = "INSERT INTO Opiniones(id_pc,valor,mensaje) VALUES (?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1,op.getIdPc());
        stmt.setDouble(2,op.getValor());
        stmt.setString(3,op.getMensaje());

        int rowCount = stmt.executeUpdate(); 
    } 

    /**
     * Asociar una opinion a un ordenador
     *
     */
    public void setOpinion(int idPc, String opinion, int valoracion) throws SQLException{

        
        String query = "INSERT INTO Opiniones(id_pc, valor, mensaje) VALUES(?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1,idPc);
        stmt.setInt(2,valoracion);
        stmt.setString(3,opinion);

        System.out.println(idPc + opinion + valoracion);
        int rowCount = stmt.executeUpdate(); 
    } 
}

