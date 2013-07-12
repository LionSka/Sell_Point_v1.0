package com.thoi.sellpoint.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @author David Cascante
 *
 * Clase que establece conexion con la base de datos en el momento de ser intanciada
 */
public class DBConnection {
	
	static Connection connection;
	static String bd = "DB_Thoi";
	static String user = "root";
	static String password = "1234";
	static String server = "jdbc:mysql://localhost:3306/" + bd;
	
	public DBConnection() throws Exception{
		doConnection();	
	}
	
	/**
	 * Abre la conexion con la base de datos
	 * @throws Exception
	 */
	void doConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(server, user, password);
			System.out.print("Conexion exitosa");
		}
		
		catch (Exception e) {
			System.out.println("Imposible realizar conexion con la BD");
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Cierra un ResultSet
	 * @param rs ResultSet a cerrar
	 */
	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} 
			
			catch (Exception e) {
				System.out.print("No es posible cerrar el ResulSet");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cierra un Statement
	 * @param stmt Statement a cerrar
	 */
	public void closeStatement(java.sql.Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} 
			
			catch (Exception e) {
				System.out.print("No es posible cerrar statement");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cierra la conexion con la Base de Datos
	 */
	public void closeConnection() {

		if (connection != null) {

			try {
				connection.close();
				System.out.print("Conexion cerrada");
			} 
			
			catch (Exception e) {
				System.out.print("No es posible cerrar la conexion");
				e.printStackTrace();
			}
		}
	}	

	/**
	 * Retorna el valor del objeto connection
	 * @return Connection
	 */
	public Connection getConexion() {
		return connection;
	}

}
