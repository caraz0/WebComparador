package model;

import java.util.List;

public class Ordenador {
   
    private int id_producto;
    private String marca;
    private String modelo;
    private int memoria;
    private int capacidad;
    private double pantalla;
    private String tipo_memoria;
    private String disco;
    private String procesador;
    private List<String> tiendas; 
    private List<Double> precios;
    private List<String> urls; 
   
    public Ordenador(){
    }

    public Ordenador(int id_producto, String marca, String modelo, int memoria, int capacidad, double pantalla, String tipo_memoria, String disco, 
        String procesador, List<String> tiendas, List<Double> precios, List<String> urls) {

        this.setId_producto(id_producto);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setMemoria(memoria);
        this.setCapacidad(capacidad);
        this.setPantalla(pantalla);
        this.setTipoMemoria(tipo_memoria);
        this.setDisco(disco);
        this.setProcesador(procesador);
        this.setTiendas(tiendas);
        this.setPrecios(precios);
        this.setUrls(urls);
    }
 
   
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

	public int getMemoria() {
		return memoria;
	}

	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

    public double getPantalla() {
		return pantalla;
	}

	public void setPantalla(double pantalla) {
		this.pantalla = pantalla;
	}
 
    public String getTipoMemoria() {
		return tipo_memoria;
	}

	public void setTipoMemoria(String tipo_memoria) {
		this.tipo_memoria = tipo_memoria;
	}

    public String getDisco() {
		return disco;
	}

	public void setDisco(String disco) {
		this.disco = disco;
	}
    
    public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}
    
    public List<String> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<String> tiendas) {
		this.tiendas = tiendas;
	}

    public List<Double> getPrecios() {
		return precios;
	}

	public void setPrecios(List<Double> precios) {
		this.precios = precios;
	}

    public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
