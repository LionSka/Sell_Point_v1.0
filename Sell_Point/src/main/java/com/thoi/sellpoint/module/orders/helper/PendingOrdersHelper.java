package com.thoi.sellpoint.module.orders.helper;

import java.util.ArrayList;

import com.thoi.sellpoint.module.orders.dto.OrderDTO;

public class PendingOrdersHelper {

	public static String convertListToJSON(ArrayList<OrderDTO> list){
		//"{ \"aaData\": [[2, \"17/07/2013\",\"jcascantes206\",\"Pendiente\"], [3, \"17/07/2013\",\"jcascantes206\",\"Pendiente\"], [4, \"17/07/2013\",\"jcascantes206\",\"Pendiente\"]] }";
		//{"aaData":[2, "17-07-2013 12:03pm", "jcascantes206", "Pendiente"], [3, "17-07-2013 12:03pm", "jcascantes206", "Pendiente"], [4, "17-07-2013 12:03pm", "jcascantes206", "Pendiente"]}
		int listSize=list.size();
		OrderDTO order=new OrderDTO();
		String ajaxData="{\"aaData\":[";		
		
		for(int i=0;i<listSize;i++){
			order=list.get(i);
			
			if(i==(listSize-1)){
				ajaxData+= "[" + order.getOrderID() + ", \"17-07-2013 12:03pm\", \"jcascantes206\", \"" + getStatusName(order.getStatus()) +"\"]";
			}else{
				ajaxData+= "["+ order.getOrderID() + ", \"17-07-2013 12:03pm\", \"jcascantes206\", \"" + getStatusName(order.getStatus()) +"\"], ";
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
