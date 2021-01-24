package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza
implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String PizzaName;
    private float Price;
	private int pizzaid;
    private ArrayList<Integer> quantity;
	public String getPizzaName() {
		return PizzaName;
	}
	public void setPizzaName(String pizzaName) {
		PizzaName = pizzaName;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public int getPizzaid() {
		return pizzaid;
	}
	public void setPizzaid(int pizzaid) {
		this.pizzaid = pizzaid;
	}
	public ArrayList<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}
	public Pizza(String pizzaName, float price, int pizzaid, ArrayList<Integer> quantity) {
		
		this.PizzaName = pizzaName;
		this.Price = price;
		this.pizzaid = pizzaid;
		this.quantity = quantity;
	}
    

    public Pizza(){};
}
