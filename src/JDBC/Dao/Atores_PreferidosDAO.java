package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Ator;
import JDBC.Pojo.Atores_Preferidos;

import JDBC.Pojo.Usuario;

public class Atores_PreferidosDAO {

	private Connection connection;
	
	public Atores_PreferidosDAO() {
		
	}
	
	public boolean addAtorPreferido(Usuario usuario, Ator ator) {
		String sql = "INSERT INTO Atores_Preferidos (uid, aid) values (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getUid());
			stmt.setInt(2, ator.getAid());
			
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
			catch(SQLException e ) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public ArrayList<Atores_Preferidos> listarAtoresPreferidos() {
		String sql = "SELECT * FROM Atores_Preferidos";
		ArrayList<Atores_Preferidos> atores_prefs = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int uid = Integer.parseInt(rs.getString("uid"));
				int aid = Integer.parseInt(rs.getString("aid"));
				AtorDAO atorDao = new AtorDAO();
				UsuarioDAO usuarioDao = new UsuarioDAO();
				
				Usuario usuario = usuarioDao.getUsuarioByUid(uid);
				Ator ator = atorDao.getAtorByAid(aid);
				
				Atores_Preferidos novo = new Atores_Preferidos(usuario, ator);
				atores_prefs.add(novo);
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
		
		return atores_prefs;
	}
		
}
	
	

	
