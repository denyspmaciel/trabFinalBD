package JDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Connection.ConnectionFactory;
import JDBC.Pojo.Usuario;


public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {

	}

	public boolean addUsuario(Usuario usuario) {

		String sql = "INSERT INTO Usuario (nome, cidade, endereco) VALUES (?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCidade());
			stmt.setString(3, usuario.getEndereco());

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

	public ArrayList<Usuario> listarUsuarios() {
		String sql = "SELECT * FROM Usuario";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				int uid = rs.getInt("uid");
				String nome = rs.getString("nome");
				String cidade = rs.getString("cidade");
				String endereco = rs.getString("endereco");

				Usuario novo = new Usuario(uid, nome, cidade, endereco);
				usuarios.add(novo);
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


		return usuarios;
	}

	public boolean delUsuario(int uid) {
		String sql = "DELETE FROM Usuario where uid = ?";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, uid);

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

	public Usuario getUsuarioByUid(int uid) {
		String sql = "SELECT * FROM Usuario WHERE uid = ?";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, uid);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			Usuario usuario = new Usuario(uid, rs.getString("nome"), rs.getString("cidade"), rs.getString("endereco"));
			stmt.close();

			return usuario;
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

	public boolean atualizarUsuario(Usuario usuario) {

		String sql = "UPDATE Usuario set nome = ?, cidade = ?, endereco = ? WHERE uid = ?";
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCidade());
			stmt.setString(3, usuario.getEndereco());
			stmt.setInt(4,usuario.getUid());

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
