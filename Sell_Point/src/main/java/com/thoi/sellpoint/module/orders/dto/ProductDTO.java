package com.thoi.sellpoint.module.orders.dto;

/**
 * @author David Cascante
 *
 * Objeto que almacena la estructura de los productos
 */
public class ProductDTO {
	
	public int ID_Producto; 
	public String Nom_Prod; 
	public Double Precio;
	
	// Contructor
	public ProductDTO(int iD_Producto, String nom_Prod, Double precio) {
		super();
		ID_Producto = iD_Producto;
		Nom_Prod = nom_Prod;
		Precio = precio;
	}
	
	// Getters y Setters
	public int getID_Producto() {
		return ID_Producto;
	}
	public void setID_Producto(int iD_Producto) {
		ID_Producto = iD_Producto;
	}
	
	public String getNom_Prod() {
		return Nom_Prod;
	}
	public void setNom_Prod(String nom_Prod) {
		Nom_Prod = nom_Prod;
	}
	
	public Double getPrecio() {
		return Precio;
	}
	public void setPrecio(Double precio) {
		Precio = precio;
	}
	
	
}
