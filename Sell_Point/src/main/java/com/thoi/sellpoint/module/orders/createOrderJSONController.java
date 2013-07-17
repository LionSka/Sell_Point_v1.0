package com.thoi.sellpoint.module.orders;

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
		
		// TODO: Acá falta la validación de la información
		
		// Crea la orden recibida
		CreateOrderDAO.createOrder(order);	
		
		return order;
	}
	

}
