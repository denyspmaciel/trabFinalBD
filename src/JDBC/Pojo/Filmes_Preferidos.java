package JDBC.Pojo;

public class Filmes_Preferidos {

	private Usuario usuario;
	private String genero_filme;
	
	public Filmes_Preferidos(Usuario usuario, String genero) {
		this.usuario = usuario;
		this.genero_filme = genero;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getGenero() {
		return this.genero_filme;
	}

	public void setGenero(String genero) {
		this.genero_filme = genero;
	}
	
	public String toString() {
		String str;
		str = "\nUsuário: "+getUsuario().getNome()+"\nGênero: "+getGenero();
		return str;
	}
	
}
