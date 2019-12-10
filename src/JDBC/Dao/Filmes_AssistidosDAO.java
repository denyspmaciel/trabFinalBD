package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Filme;
import JDBC.Pojo.Filmes_Assistidos;
import JDBC.Pojo.Usuario;

public class Filmes_AssistidosDAO {

	private Connection connection;
	
	public Filmes_AssistidosDAO() {
		
	}
	
	public boolean addFilmeAssistido(Usuario usuario, Filme filme) {
		String sql = "INSERT INTO Filmes_Assistidos (uid, fid) values (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getUid());
			stmt.setInt(2, filme.getFid());
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			if(affectedRows > 0)
				return true;
			return false;
		}
		
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public ArrayList<Filmes_Assistidos> listarFilmesAssistidos() {
		String sql = "SELECT * FROM Filmes_Assistidos";
		ArrayList<Filmes_Assistidos> filmes_assis = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int uid = Integer.parseInt(rs.getString("uid"));
				int fid = Integer.parseInt(rs.getString("fid"));
				
				UsuarioDAO usuarioDao = new UsuarioDAO();
				FilmeDAO filmeDao = new FilmeDAO();
				Usuario usuario = usuarioDao.getUsuarioByUid(uid);
				Filme filme = filmeDao.getFilmeByFid(fid);
				
				Filmes_Assistidos novo = new Filmes_Assistidos(usuario, filme);
				filmes_assis.add(novo);
				
			}
			stmt.close();
		}
		
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return filmes_assis;
	}
	
}
