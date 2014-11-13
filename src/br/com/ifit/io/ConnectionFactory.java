package br.com.ifit.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ifit.exception.BDException;

public abstract class ConnectionFactory {
    
	// database URL
	private static final String SERVER_NAME = "localhost";
	private static final String BD_NAME = "iFit";
    private static final String DATABASE_URL = "jdbc:mysql://" + SERVER_NAME + "/" + BD_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public static String status = "Nao conectou...";

    public Connection getConnection() throws BDException {
    	Connection connection = null; //atributo do tipo Connection 
		try { // Carregando o JDBC Driver 
			String driverName = "com.mysql.jdbc.Driver"; 
			Class.forName(driverName);
			String url = DATABASE_URL; 
			String username = USERNAME; 
			String password = PASSWORD;
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) { 
				status = ("Conectado com sucesso!"); 
			}else { 
				status = ("Nao foi possivel realizar conexao"); 
			} 
			return connection;
			
		} catch (ClassNotFoundException e) { //Driver nao encontrado 
			throw new BDException("O driver expecificado nao foi encontrado."); 
		} catch (SQLException e) { //Nao conseguindo se conectar ao banco 
			throw new BDException("Nao foi possivel conectar ao Banco de Dados."); 
		} 
    }

    public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
	     try {
	         close(conn, stmt, rs);
	     } catch (Exception e) {
	         System.out.println("Erro ao fechar conexao.");
	         e.printStackTrace();
	     }
    }

    public void closeConnection(Connection conn, Statement stmt) {
	     try {
	         close(conn, stmt, null);
	     } catch (Exception e) {
	         System.out.println("Erro ao fechar conexao.");
	         e.printStackTrace();
	     }
    }

    public void closeConnection(Connection conn) {
     try {
         close(conn, null, null);
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {

     try {
         if (rs != null) {
             rs.close();
         }
         if (stmt != null) {
             stmt.close();
         }
         if (conn != null) {
             conn.close();
         }
     } catch (Exception e) {
         System.out.println("Erro ao fechar conexao.");
         e.printStackTrace();
     }
    }
}