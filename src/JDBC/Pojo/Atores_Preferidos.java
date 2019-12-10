package JDBC.Pojo;

public class Atores_Preferidos {

	private Usuario usuario;
	private Ator ator;
	
	public Atores_Preferidos(Usuario usuario, Ator ator) {
		this.usuario = usuario;
		this.ator = ator;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ator getAtor() {
		return this.ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}
	
	public String toString() {
		String str;
		str = "\nUsuário: "+getUsuario().getNome()+"\nAtor: "+getAtor().getNome();
		return str;
	}
	
}
