package model;

public class Delivery {
	  
	  private String address;
	  private String deliverytype;
	  private String city;
	  private String state;
	  private int zipcode;
	  
	  
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(String deliverytype) {
		this.deliverytype = deliverytype;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public Delivery() {};
	public Delivery(String deliverytype,String address,String city,String state, int zipcode){
		
		this.address=address;
		this.city=city;
		this.deliverytype=deliverytype;
		this.state=state;
		this.zipcode=zipcode;
	}
}
