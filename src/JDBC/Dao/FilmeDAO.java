package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Ator;
import JDBC.Pojo.Filme;

public class FilmeDAO {

	private Connection connection;

	public FilmeDAO() {

	}

	public boolean addFilme(Filme filme) {
		String sql = "INSERT INTO Filme (nome, genero, aid_ator1, aid_ator2) values (?, ?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, filme.getNome());
			stmt.setString(2, filme.getGenero());
			stmt.setInt(3, filme.getAtor1().getAid());
			stmt.setInt(4, filme.getAtor2().getAid());

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

	public ArrayList<Filme> listarFilmes() {
		String sql = "SELECT * FROM Filme";
		ArrayList<Filme> filmes = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				int fid = rs.getInt("fid");
				String nome = rs.getString("nome");
				String genero = rs.getString("genero");

				int aid_ator1 = Integer.parseInt(rs.getString("aid_ator1"));
				int aid_ator2 = Integer.parseInt(rs.getString("aid_ator2"));
				AtorDAO atorDao = new AtorDAO();
				Ator ator1 = atorDao.getAtorByAid(aid_ator1);
				Ator ator2 = atorDao.getAtorByAid(aid_ator2);

				Filme novo = new Filme(fid, nome, genero, ator1, ator2);
				filmes.add(novo);

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

		return filmes;
	}

	public boolean delFilme(int fid) {
		String sql = "DELETE FROM Filme WHERE fid = ?";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, fid);

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
	
	public Filme getFilmeByFid(int fid) {
		String sql = "SELECT * FROM Filme WHERE fid = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, fid);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			int aid_ator1 = Integer.parseInt(rs.getString("aid_ator1"));
			int aid_ator2 = Integer.parseInt(rs.getString("aid_ator2"));
			AtorDAO atorDao = new AtorDAO();
			Ator ator1 = atorDao.getAtorByAid(aid_ator1);
			Ator ator2 = atorDao.getAtorByAid(aid_ator2);
			
			Filme filme = new Filme(fid, rs.getString("nome"), rs.getString("genero"), ator1, ator2);
			stmt.close();
			
			return filme;
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
	
	public ArrayList<Filme> recomendarPorAtor(int uid) {
		
		String sql = "select f.fid, f.nome from Usuario u, Filme f, Atores_Preferidos ap " + 
				"where u.uid = ? and (f.aid_ator1 = ap.aid or f.aid_ator2 = ap.aid)" + 
				"and f.fid not in (select fa.fid from Filmes_Assistidos fa where fa.uid = u.uid)";
		
		ArrayList<Filme> filmes = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, uid);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int fid = Integer.parseInt(rs.getString("fid"));
				
				FilmeDAO filmeDao = new FilmeDAO();
				Filme filme = filmeDao.getFilmeByFid(fid);
				filmes.add(filme);
				
			}
			stmt.close();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				this.connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return filmes;
	}
		
	
	public ArrayList<Filme> recomendarPorGenero(int uid) {
		
		String sql = "select f.fid, f.nome from Usuario u, Filme f, Filmes_Preferidos fp "+
				"where u.uid = ? and f.genero = fp.genero_filme and "+
				"f.fid not in (select fa.fid from Filmes_Assistidos fa where fa.uid = u.uid)";
		
		ArrayList<Filme> filmes = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, uid);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				int fid = Integer.parseInt(rs.getString("fid"));
				
				FilmeDAO filmeDao = new FilmeDAO();
				Filme filme = filmeDao.getFilmeByFid(fid);
				filmes.add(filme);
				
			}
			stmt.close();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				this.connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return filmes;
	}
}
