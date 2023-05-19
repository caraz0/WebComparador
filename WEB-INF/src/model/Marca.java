package model;

public class Marca {

    private int id_marca;
	private String nombre;
    private int cantidad;
    private String imagen;

     public Marca() {}
 
    public Marca(int id_marca, String nombre, int cantidad, String imagen) {
        this.setIdMarca(id_marca);
        this.setNombre(nombre);
        this.setCantidad(cantidad);
        this.setImagen(imagen);
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public int getIdMarca() {
		return id_marca;
	}

	public void setIdMarca(int id_marca) {
		this.id_marca = id_marca;
	}
    
    public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
