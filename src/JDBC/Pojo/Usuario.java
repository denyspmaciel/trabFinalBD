package JDBC.Pojo;

public class Usuario {

	private int uid;
	private String nome;
	private String cidade;
	private String endereco;
	
	public Usuario (int uid, String nome, String cidade, String endereco) {
		this.uid = uid;
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
	}
	
	public Usuario(String nome, String cidade, String endereco) {
		this.nome= nome;
		this.cidade = cidade;
		this.endereco = endereco; 
	}
	
	public Usuario() {
		
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		String str;
		str = "\nuid: "+this.uid+"\nNome: "+this.nome+"\nCidade: "+this.cidade+"\nEndereço: "+this.endereco;
		return str;
	}
	
}
