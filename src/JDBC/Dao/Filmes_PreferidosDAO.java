package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Filmes_Preferidos;
import JDBC.Pojo.Usuario;

public class Filmes_PreferidosDAO {

	private Connection connection;
	
	public Filmes_PreferidosDAO() {
		
	}
	
	public boolean addFilmePreferido(Filmes_Preferidos fp) {
		String sql = "INSERT INTO Filmes_Preferidos (uid, genero_filme) values (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, fp.getUsuario().getUid());
			stmt.setString(2, fp.getGenero());
			
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

	public ArrayList<Filmes_Preferidos> listarFilmesPreferidos() {
		String sql = "SELECT * FROM Filmes_Preferidos";
		ArrayList<Filmes_Preferidos> filmes_prefs = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int uid = Integer.parseInt(rs.getString("uid"));
				String genero_filme = rs.getString("genero_filme");
				//int fid = Integer.parseInt(rs.getString("fid"));
				UsuarioDAO usuarioDao = new UsuarioDAO();
				Usuario usuario = usuarioDao.getUsuarioByUid(uid);
				//FilmeDAO filmeDao = new FilmeDAO();
				//Filme filme = filmeDao.getFilmeByFid(fid);
				
				Filmes_Preferidos novo = new Filmes_Preferidos(usuario, genero_filme);
				filmes_prefs.add(novo);
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
		return filmes_prefs;
	}
}
