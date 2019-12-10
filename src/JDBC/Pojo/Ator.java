package JDBC.Pojo;

public class Ator {

	private int aid;
	private String nome;
	private String cidade;
	
	public Ator(int aid, String nome, String cidade) {
		this.aid = aid;
		this.nome = nome;
		this.cidade = cidade;
	}
	
	public Ator(String nome, String cidade) {
		this.nome = nome;
		this.cidade = cidade;
	}
	
	public Ator() {
		
	}

	public int getAid() {
		return this.aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String toString() {
		String str;
		str = "\naid: "+this.aid+"\nNome: "+this.nome+"\nCidade: "+this.cidade;
		return str;
	}
	
}
