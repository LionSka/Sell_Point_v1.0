package com.thoi.sellpoint.module.orders.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thoi.sellpoint.common.database.DBConnection;
import com.thoi.sellpoint.common.util.LoggerService;
import com.thoi.sellpoint.module.orders.dto.OrderDTO;
import com.thoi.sellpoint.module.orders.dto.ProductDTO;

/**
 * Clase encargada de realizar las transacciones en la Base de Datos
 * @author Equipo
 *
 */
public class CreateOrderDAO {
	
	static String SELECT_ALL_PRODUCTS_QUERY="SELECT * FROM db_thoi.tb_producto";
	static String INSERT_NEW_ORDER_QUERY="INSERT INTO tb_factura (SubTotal, Descuento, Total, Num_Transaccion, Tipo, Estado, Efectivo) VALUES (?,?,?,?,?,?,?)";
	
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
	            return convertResultSetToOrder(psSelect.executeQuery());
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            connection.closeStatement(psSelect);
	            connection.closeConnection();
	            return null;
	        }
	}
	
	/**
	 * Crea una nueva factura en la base de datos
	 * 
	 * @param order El objeto que contiene los datos de la orden
	 */
	public static void createOrder(OrderDTO order){
		
		try {
		 	connection= new DBConnection();		 	
			psSelect = connection.getConexion().prepareStatement(INSERT_NEW_ORDER_QUERY);
			
            psSelect.setDouble(1, order.getSubTotal());
            psSelect.setDouble(2, order.getDiscount());
            psSelect.setDouble(3, order.getTotal());
            psSelect.setString(4, order.getTransactionNumber());
            psSelect.setString(5, String.valueOf(order.getType()));
            psSelect.setString(6, String.valueOf(order.getStatus()));
            psSelect.setDouble(7, order.getCash());
            psSelect.executeUpdate();
            
        } catch (Exception e) {
            LoggerService.logException(e);
            connection.closeStatement(psSelect);
            connection.closeConnection();
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
        return productList;
	}
}
