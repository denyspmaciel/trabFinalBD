package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Ator;


public class AtorDAO {

	private Connection connection;
	
	public AtorDAO() {
		
	}
	
	public boolean addAtor(Ator ator) {
		
		String sql = "INSERT INTO Ator (nome, cidade) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, ator.getNome());
			stmt.setString(2, ator.getCidade());
			
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
	
	public ArrayList<Ator> listarAtores() {
		String sql = "SELECT * from Ator";
		ArrayList<Ator> atores = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int aid = rs.getInt("aid");
				String nome = rs.getString("nome");
				String cidade = rs.getString("cidade");
				
				Ator novo = new Ator(aid, nome, cidade);
				atores.add(novo);
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
		
		return atores;
		
	}
	
	public boolean delAtor(int aid) {
		String sql = "DELETE FROM Ator where aid = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, aid);
			
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
	
	public Ator getAtorByAid(int aid) {
		String sql = "SELECT * FROM Ator WHERE aid = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, aid);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Ator ator = new Ator(aid, rs.getString("nome"), rs.getString("cidade"));
			stmt.close();
			
			return ator;
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
		return null;
	}
	
	public boolean atualizarAtor(Ator ator) {

		String sql = "UPDATE Ator set nome = ?, cidade = ? WHERE uid = ?";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, ator.getNome());
			stmt.setString(2, ator.getCidade());
			stmt.setInt(3,ator.getAid());

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
 	
}
