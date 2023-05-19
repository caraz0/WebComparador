package model;

public class Opinion {

    private int id;
    private int idPc;
    private String marcaPc;
    private String modeloPc;
    private double valor;
	private String mensaje;

    public Opinion() {}
 
    public Opinion(int id, int idPc,String marcaPc, String modeloPc, double valor, String mensaje) {
        this.setId(id);
        this.setIdPc(idPc);
        this.setMarcaPc(marcaPc);
        this.setModeloPc(modeloPc);
        this.setValor(valor);
        this.setMensaje(mensaje);
    }
 
    public String getMarcaPc() {
        return marcaPc;
    }

    public void setMarcaPc(String marcaPc) {
        this.marcaPc = marcaPc;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public int getIdPc() {
		return idPc;
	}

	public void setIdPc(int idPc) {
		this.idPc = idPc;
	}

    public String getModeloPc() {
		return modeloPc;
	}

	public void setModeloPc(String modeloPc) {
		this.modeloPc = modeloPc;
	}

    public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

    public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}