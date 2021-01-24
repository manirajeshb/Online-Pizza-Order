package model;

public class Cart {
	private String PizzaName;
	private int quantity;
	private float price;
	private float total_price;
	private int pizzaid;
	
	
	public int getPizzaid() {
		return pizzaid;
	}
	public void setPizzaid(int pizzaid) {
		this.pizzaid = pizzaid;
	}
	public String getPizzaName() {
		return PizzaName;
	}
	public void setPizzaName(String pizzaName) {
		PizzaName = pizzaName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	
	

	public Cart(String pizzaName, int quantity, float price, float total_price, int pizzaid) {
		this.PizzaName = pizzaName;
		this.quantity = quantity;
		this.price = price;
		this.total_price = total_price;
		this.pizzaid = pizzaid;
	}
	public Cart(){

	};


}





