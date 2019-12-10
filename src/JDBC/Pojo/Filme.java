package JDBC.Pojo;

public class Filme {

	private int fid;
	private String nome, genero;
	private Ator ator1, ator2;
	
	public Filme(int fid, String nome, String genero, Ator ator1, Ator ator2) {
		this.fid = fid;
		this.nome = nome;
		this.genero = genero;
		this.ator1 = ator1;
		this.ator2 = ator2;
	}
	
	public Filme(String nome, String genero, Ator ator1, Ator ator2) {
		this.nome = nome;
		this.genero = genero;
		this.ator1 = ator1;
		this.ator2 = ator2;
	}
	
	public Filme() {
		
	}

	public String getGenero() {
		return this.genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getFid() {
		return this.fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Ator getAtor1() {
		return this.ator1;
	}

	public void setAtor1(Ator ator1) {
		this.ator1 = ator1;
	}

	public Ator getAtor2() {
		return this.ator2;
	}

	public void setAtor2(Ator ator2) {
		this.ator2 = ator2;
	}
	
	public String toString() {
		String str;
		str = "\nfid: "+this.fid+"\nNome: "+this.nome+"\nGênero: "+this.genero+
				"\nAtores: "+this.ator1.getNome()+" e "+this.ator2.getNome();
		return str;
	}
	
}
