package com.thoi.sellpoint.module.orders;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoi.sellpoint.module.orders.dao.CreateOrderDAO;
import com.thoi.sellpoint.module.orders.dto.OrderDTO;

@Controller
@RequestMapping("/createOrder/savedOrder")
public class createOrderJSONController {
	
	
	@RequestMapping(value="order", method = RequestMethod.POST)
	public @ResponseBody OrderDTO post(@RequestBody final OrderDTO order) {
		
		// TODO: Ac� falta la validaci�n de la informaci�n
		
		// Se agrega la fecha del servidor
		order.setDate(new Date());
		
		// Crea la orden recibida
		CreateOrderDAO.createOrder(order);	
		
		return order;
	}
	

}
