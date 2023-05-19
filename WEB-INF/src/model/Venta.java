package model;

public class Venta {

    private int id_tienda;
	private int id_pc;
    private double precio;
    private String url;

    public Venta() {}
 
    public Venta(int id_tienda, int id_pc, double precio, String url) {
        this.setIdTienda(id_tienda);
        this.setIdPc(id_pc);
        this.setPrecio(precio);
        this.setUrl(url);
    }
 
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

	public int getIdTienda() {
		return id_tienda;
	}

	public void setIdTienda(int id_tienda) {
		this.id_tienda = id_tienda;
	}

    public int getIdPc() {
		return id_pc;
	}

	public void setIdPc(int id_pc) {
		this.id_pc = id_pc;
	}
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}