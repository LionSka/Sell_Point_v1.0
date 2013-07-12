package com.thoi.sellpoint.module.orders.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thoi.sellpoint.common.database.DBConnection;
import com.thoi.sellpoint.module.orders.dto.ProductDTO;

/**
 * Clase encargada de realizar las transacciones en la Base de Datos
 * @author Equipo
 *
 */
public class CreateOrderDAO {
	
	static String SELECT_ALL_PRODUCTS_QUERY="SELECT * FROM db_thoi.tb_producto";
	static DBConnection connection;
	static PreparedStatement psSelect = null;
	
	
	/**
	 * Obtiene todos los productos que se encuentren en la BD
	 * @return Lista de productos
	 */
	public static ArrayList<ProductDTO> getAllProducts(){
		
		 try {
			 	connection= new DBConnection();
			 	
				if (null == psSelect) {
		           	psSelect = connection.getConexion().prepareStatement(SELECT_ALL_PRODUCTS_QUERY);
		        }
				
	            // psSelect.setInt(1, "valor del parametro");
	            return convertResultSetToOrder(psSelect.executeQuery());
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            connection.closeStatement(psSelect);
	            connection.closeConnection();
	            return null;
	        }
	}
	
	/**
	 * Convierte los registros provenientes de la BD en ProductDTO
	 * @param result 
	 * @return Lista de productos
	 * @throws SQLException
	 */
	static ArrayList<ProductDTO> convertResultSetToOrder(ResultSet result) throws SQLException{
		
		ArrayList<ProductDTO> productList=new ArrayList<ProductDTO>();
		
        while (result.next()) {
        	productList.add(new ProductDTO(result.getInt(1), result.getString(2), result.getDouble(3)));
        }
        
        //closeStatement(psSelect);
        //closeConnection();
        return productList;
	}
}
