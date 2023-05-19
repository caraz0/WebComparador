package model;

public class Usuario {

	private int id;
	private String name;
	private String contrasena;
	private String email;
	private boolean isAdmin;

	public Usuario(String name, String email, String contrasena, boolean isAdmin) {
		
		this.setName(name);
		this.setContrasena(contrasena);
		this.setEmail(email);
		this.isAdmin=isAdmin;
	}
	public Usuario() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contraseña=" + contrasena + ", es admin=" + isAdmin + "]";
	}

}