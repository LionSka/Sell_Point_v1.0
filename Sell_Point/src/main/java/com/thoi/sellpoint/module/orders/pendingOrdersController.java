package com.thoi.sellpoint.module.orders;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoi.sellpoint.module.orders.dao.PendingOrdersDAO;
import com.thoi.sellpoint.module.orders.dto.OrderDTO;
import com.thoi.sellpoint.module.orders.helper.PendingOrdersHelper;

@Controller
public class pendingOrdersController {

	@RequestMapping(value = "/pendingOrders", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "pendingOrders";
	}
	
	/**
	 * Alimenta la tabla de ordenes pendientes vía AJAX
	 * 
	 * @param locale
	 * @param model
	 * @return El JSON de respuesta
	 */
	@RequestMapping(value = "/pendingOrders/ajax/getPendingOrders", method = RequestMethod.GET)
	public @ResponseBody String getPendingOrders(Locale locale, Model model) {
		
		ArrayList<OrderDTO> orderList=PendingOrdersDAO.getPendingOrders();
		String ajaxData = PendingOrdersHelper.convertListToJSON(orderList);
		
		return ajaxData;
	}
}
