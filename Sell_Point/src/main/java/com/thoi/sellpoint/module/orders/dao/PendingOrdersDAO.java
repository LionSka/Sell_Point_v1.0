package com.thoi.sellpoint.module.orders.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thoi.sellpoint.common.database.DBConnection;
import com.thoi.sellpoint.common.util.LoggerService;
import com.thoi.sellpoint.module.orders.dto.OrderDTO;

public class PendingOrdersDAO {

	static String SELECT_ORDERS_BY_STATUS_QUERY="Select * from tb_factura where Estado = ?";
	static String UPDATE_ORDER_STATUS_QUERY="Update tb_factura set Estado = ? where Num_Factura = ?";
	
	static DBConnection connection;
	static PreparedStatement psSelect = null;
	
	
	/**
	 * Obtiene todas las ordenes pendientes en la base de datos
	 * 
	 * @return Retorna una lista de ordenes en estado "Pendiente"
	 */
	public static ArrayList<OrderDTO> getPendingOrders(){
		
		try {
		 	connection= new DBConnection();		 	
			psSelect = connection.getConexion().prepareStatement(SELECT_ORDERS_BY_STATUS_QUERY);
	        psSelect.setString(1, "P");
	        return convertResultSetToOrderList(psSelect.executeQuery());
            
        } catch (Exception e) {
            LoggerService.logException(e);
            connection.closeStatement(psSelect);
            connection.closeConnection();
            return null;
        }
		
		
	}
	
	/**
	 * Actualiza el estado de una factura
	 * 
	 * @param orderID Número de la factura
	 * @param status Estado al que va a actualizar
	 */
	public static void updateOrderStatus(int orderID, char status){
		
		try {
		 	connection= new DBConnection();	
	        psSelect = connection.getConexion().prepareStatement(UPDATE_ORDER_STATUS_QUERY);
	        			
            psSelect.setString(1, String.valueOf(status));
            psSelect.setInt(2, orderID);            
            psSelect.executeQuery();
            
        } catch (Exception e) {
            LoggerService.logException(e);
            connection.closeStatement(psSelect);
            connection.closeConnection();
        }
	}
	
	/**
	 * Convierte los registros provenientes de la BD en OrderDTO
	 * @param result 
	 * @return Lista de ordenes
	 * @throws SQLException
	 */
	static ArrayList<OrderDTO> convertResultSetToOrderList(ResultSet result) throws SQLException{
		
		ArrayList<OrderDTO> orderList=new ArrayList<OrderDTO>();
		
        while (result.next()) {
        	orderList.add(new OrderDTO(result.getInt("Num_Factura"), 
        				result.getDouble("SubTotal"), 
        				result.getDouble("Descuento"), 
        				result.getDouble("Total"), 
        				result.getString("Num_Transaccion"), 
        				result.getDouble("Efectivo"), 
        				result.getString("Estado").charAt(0), 
        				result.getString("Tipo").charAt(0),
        				result.getTimestamp("Fecha"),
        				result.getString("Usuario")
        	));
        }
        return orderList;
	}
	
}
