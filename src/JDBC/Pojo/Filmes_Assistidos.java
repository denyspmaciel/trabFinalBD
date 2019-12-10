package JDBC.Pojo;

public class Filmes_Assistidos {

	private Usuario usuario;
	private Filme filme;
	
	public Filmes_Assistidos(Usuario usuario, Filme filme) {
		this.usuario = usuario;
		this.filme = filme;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Filme getFilme() {
		return this.filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public String toString() {
		String str;
		str = "\nUsuário: "+getUsuario().getNome()+"\nFilme: "+getFilme().getNome();
		return str;
	}
	
}