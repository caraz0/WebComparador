package model;

public class Tienda {

    private int id;
	private String nombre;
    private String url;

    public Tienda() {}
 
    public Tienda(int id, String nombre, String url) {
        this.setId(id);
        this.setNombre(nombre);
        this.setUrl(url);
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}