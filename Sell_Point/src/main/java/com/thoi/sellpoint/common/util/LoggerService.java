package com.thoi.sellpoint.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase encargada de registrar en bitacora.
 * @author David Cascante
 *
 */
public class LoggerService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);
	
	/**
	 * Registra el stack de la excepción
	 * 
	 * @param e La excepción
	 */
	public static void logException(Exception e){
		for (StackTraceElement element : e.getStackTrace()) {
			logger.error(element.toString());
		}
	}
	
	
	/**
	 * Registra unicamente un mensaje informativo
	 * 
	 * @param message El mensaje
	 */
	public static void logMessage(String message){
		logger.info(message);
	}
	
}
