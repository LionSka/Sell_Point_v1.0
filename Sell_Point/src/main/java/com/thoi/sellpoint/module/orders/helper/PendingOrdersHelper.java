package com.thoi.sellpoint.module.orders.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.thoi.sellpoint.module.orders.dto.OrderDTO;

public class PendingOrdersHelper {

	public static String convertListToJSON(ArrayList<OrderDTO> list){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyy hh:mm:ss");
		int listSize=list.size();
		OrderDTO order=new OrderDTO();
		String ajaxData="{\"aaData\":[";		
		
		for(int i=0;i<listSize;i++){
			order=list.get(i);
			
			if(i==(listSize-1)){
				ajaxData+= "[" + order.getOrderID() + ", \""+dateFormat.format(order.getDate())+"\", \""+order.getUser()+"\", \"" + getStatusName(order.getStatus()) +"\"]";
			}else{
				ajaxData+= "["+ order.getOrderID() + ", \""+dateFormat.format(order.getDate())+"\", \""+order.getUser()+"\", \"" + getStatusName(order.getStatus()) +"\"], ";
			}
		}
		ajaxData+="]}";
		
		return ajaxData;
	}
	
	static String getStatusName(char statusCode){
		switch (statusCode) {
		case 'P':
			return "Pendiente";
			
		case 'C':
			return "Cerrado";
			
		default:
			return "";
		}
	}
}
