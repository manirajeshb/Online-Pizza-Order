package model;


public class Payment {
	  private String cardnumber;
	  private int expirymonth;
	  private int expiryyear;
	  private int cvv;
	 
	  
	  
	
	
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public int getExpirymonth() {
		return expirymonth;
	}
	public void setExpirymonth(int expirymonth) {
		this.expirymonth = expirymonth;
	}
	public int getExpiryyear() {
		return expiryyear;
	}
	public void setExpiryyear(int expiryyear) {
		this.expiryyear = expiryyear;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Payment(){};
	public Payment(String cardnumber,int expirymonth,int expiryyear,int cvv){
		this.cardnumber=cardnumber;
		this.expirymonth=expirymonth;
		this.expiryyear=expiryyear;
		this.cvv=cvv;
		
	}
	
}
