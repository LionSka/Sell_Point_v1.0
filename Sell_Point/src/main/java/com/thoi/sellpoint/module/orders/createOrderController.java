package com.thoi.sellpoint.module.orders;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoi.sellpoint.module.orders.dao.CreateOrderDAO;
import com.thoi.sellpoint.module.orders.dto.ProductDTO;

// La anotación @Controller siempre debe estar en los objetos tipo controller
@Controller
public class createOrderController {

	private static final Logger logger = LoggerFactory.getLogger(createOrderController.class);
		
	
	// El value es el url por el que se va a accesar al jsp
	@RequestMapping(value = "/createOrder", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Using language {}.", locale.getDisplayName());
		
		// Obtiene los productos desde la base de datos
		ArrayList<ProductDTO> product = CreateOrderDAO.getAllProducts();
		
		// Envía la lista de productos al jsp, con el nombre de "productList"
		model.addAttribute("productList", product);
		
		// El return siempre debe llevar el nombre del jsp al que va llamar el controlador
		return "createOrder";
	}
	
}
