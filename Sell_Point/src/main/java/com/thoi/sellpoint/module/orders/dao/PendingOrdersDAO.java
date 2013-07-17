package com.thoi.sellpoint.module.orders.dao;

import java.sql.PreparedStatement;

import com.thoi.sellpoint.common.database.DBConnection;
import com.thoi.sellpoint.common.util.LoggerService;

public class PendingOrdersDAO {

	static String UPDATE_ORDER_STATUS_QUERY="Update tb_factura set Estado = ? where Num_Factura = ?";
	
	static DBConnection connection;
	static PreparedStatement psSelect = null;
	
	
	/**
	 * Actualiza el estado de una factura
	 * 
	 * @param orderID Número de la factura
	 * @param status Estado al que va a actualizar
	 */
	public static void updateOrderStatus(int orderID, char status){
		
		try {
		 	connection= new DBConnection();		 	
			if (null == psSelect) {
	           	psSelect = connection.getConexion().prepareStatement(UPDATE_ORDER_STATUS_QUERY);
	        }			
            psSelect.setString(0, String.valueOf(status));
            psSelect.setInt(1, orderID);            
            psSelect.executeQuery();
            
        } catch (Exception e) {
            LoggerService.logException(e);
            connection.closeStatement(psSelect);
            connection.closeConnection();
        }
		
	}
	
}
