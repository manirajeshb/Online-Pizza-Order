package controller;
import Data.PizzaDB;
import Data.CartDB;
import Data.DeliveryDB;
import Data.PaymentDB;
import Data.UserDB;
import model.User;
import model.Pizza;
import model.Cart;
import model.Delivery;
import model.Payment;

import java.io.IOException;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class UserController
 */
//@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session= request.getSession();
		String url="/Index.jsp";
		String action=request.getParameter("action");
		log("action : " + action);
		if(action.equals("")||action==null||action.isEmpty()){
			url="Index.jsp";
			InetAddress local=InetAddress.getLocalHost();
			Cookie c = new Cookie("host", local.toString());
			Cookie c1 = new Cookie("port", request.getServerPort() + "");
			c.setMaxAge(315360000);
			c1.setMaxAge(315360000);
			response.addCookie(c);
			response.addCookie(c1);
			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				response.setIntHeader("Refresh", 1);
			}
		}

		// register a user in database
		if (action.equals("create"))
		{
			System.out.println("Inside if loop");
			//log("enters create action loop");
			String firstName = request.getParameter("FirstName");
			String lastName = request.getParameter("LastName");

			String gender = request.getParameter("gender");  // Male or Female
			String email = request.getParameter("Email");
			String name = firstName +" "+ lastName;
			String password = request.getParameter("Password");


			String con_pwd= request.getParameter("ConfirmPwd");
			//log(firstName + lastName + gender);
			if (UserDB.emailExists(email))
			{
				request.setAttribute("msg", "The Email id is already registered");
				request.setAttribute("email", email);
				request.setAttribute("name", name);
				url = "/register.jsp";
			}


			else if(password.length()<4){
				request.setAttribute("msg", "Password length must be minimum 4 characters.");
				request.setAttribute("email", email);
				request.setAttribute("name", name);
				url = "/register.jsp";

			}
			else if (!password.equals(con_pwd))
			{
				request.setAttribute("msg", "Incorrect!! Passwords dont match.");
				request.setAttribute("email", email);
				request.setAttribute("name", name);
				url = "/register.jsp";
			}

			else
			{
				String hashResult  ;
				try {
					hashResult = HashPassword.hashPassword(password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					hashResult = "error";
				}
				if(!hashResult.equals("error")){
					password = hashResult;
				}
				//log(password);
				//log(hashResult);
				//System.out.println(password + " and hashed : " + hashResult);
				User user = new User( lastName, firstName, email,  
						gender,"Customer" , password);
				ArrayList<Pizza> pizza = PizzaDB.getPizzas();
				// write logic to get count of previous purchases by user
				//log("else loop user email: "+user.getEmail());
				UserDB.insertUser(user);
				String recipient = email;
				String subject = "Welcome";
				String content = "Hi";
				log(recipient);
				String resultMessage = "";
				String host = "smtp.gmail.com";
				String port = "587";
				String userEmail = "ras1.team10@gmail.com";
				String pass = "Saikrishna123";
				try {
					EmailUtility.sendEmail(host, port, userEmail, pass, recipient, subject,
							content);
					resultMessage = "The e-mail was sent successfully";
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "There were an error: " + ex.getMessage();
				}
				//System.out.println(resultMessage);
				request.setAttribute("theUser", user);
				request.setAttribute("pizzas", pizza);//changed to pizza from pizzas//
				url = "/UserLanding.jsp";
			}
		}

		if(action.equals("login")){
			String email=request.getParameter("email");
			log("email : " + email);
			String password=request.getParameter("password");
			String hashResult = "";
			try {
				hashResult = HashPassword.hashPassword(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(UserDB.selectUser(email,hashResult)){
				User user = UserDB.getUser(email);
				String userType = user.getUserType();
				if (userType.equalsIgnoreCase("Customer"))
				{
					ArrayList<Pizza> pizzas = PizzaDB.getPizzas();
					//log(books.get(0).getBookname() + " " + books.get(1).getBookname());
					session.setAttribute("theUser", user);
					request.setAttribute("pizzas", pizzas);

					url = "/UserLanding.jsp";
				}

			}else{
				request.setAttribute("msg", "Enter correct email and password.");
				url="/Login.jsp";
			}
			if(UserDB.selectUser(email,password)) {
				User user = UserDB.getUser(email);
				String userType = user.getUserType();
				if (userType.equalsIgnoreCase("Admin"))
				{
					ArrayList<User> users = UserDB.getUsers();
					session.setAttribute("theAdmin", user);
					request.setAttribute("users", users);
					url = "/AdminLanding.jsp";
				}
				else {
					request.setAttribute("msg", "Enter correct email and password.");
					url="/Login.jsp";
				}
			}

		} 


		//Add to cart action
		if(action.equals("Add_to_Cart")){

			//log("here --- ");
			String[] values = request.getParameterValues("check1");
			ArrayList<Pizza> pizzas = PizzaDB.getPizzas();
			ArrayList<Cart> carts=new ArrayList<Cart>();
			for(int i=0;i<values.length;i++){
				if(Integer.parseInt(values[i])>0){
					Pizza b=pizzas.get(i);

					Cart cart = new Cart(b.getPizzaName(),Integer.parseInt(values[i]), b.getPrice(),Float.parseFloat(values[i])*b.getPrice(),b.getPizzaid());	          
					carts.add(cart);
				}
			}
			for(Cart c:carts){
				log(c.getPizzaName());
			}
			CartDB.insertCart(carts);		
			ArrayList<Cart> carts1 = CartDB.getCartDetails();

			request.setAttribute("carts", carts1);	          
			url="/cart.jsp";
		}

		//Proceed to checkout action so that it redirects to delivery page
		if(action.equals("Proceed_to_checkout")){

			url = "/delivery.jsp";
		}

		//Place your order ensures the order is placed

		if(action.equals("Place_Your_Order")){
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String zipcode=request.getParameter("zip");
			String cardnumber=request.getParameter("cardnumber");
			String expirymonth=request.getParameter("expirymonth");
			String expiryyear=request.getParameter("expiryyear");
			String cvv=request.getParameter("cvv");
			log(address + " " + city + " " + state + " " + zipcode +" "+ cardnumber + " " + expirymonth + " "+expiryyear + " " + cvv);

			if(address.length()<4){
				request.setAttribute("msg", "Please enter a valid address");
				request.setAttribute("state", state);
				url = "/delivery.jsp";	        	
			}

			else if(city.length()<4){
				request.setAttribute("msg", "Please enter a valid state name");
				request.setAttribute("city", city);
				url = "/delivery.jsp";	        	
			}

			else if(state.length()<2){
				request.setAttribute("msg", "Please enter a valid state name");
				request.setAttribute("state", state);
				url = "/delivery.jsp";	        	
			}
			else if(zipcode.length()!=5){
				request.setAttribute("msg", "Please enter your 5-digit zipcode");
				request.setAttribute("zipcode", zipcode);
				url = "/delivery.jsp";	        	
			}

			else if(cardnumber.length()!=16){
				request.setAttribute("msg", "Please enter a 16-digit card number");
				request.setAttribute("cardnumber", cardnumber);
				url = "/delivery.jsp";	        	
			}

			else if(cvv.length()!=3){
				request.setAttribute("msg", "Please enter a valid 3-digit cvv number");
				request.setAttribute("cvv", cvv);
				url = "/delivery.jsp";	        	
			}
			else{

				ArrayList<Pizza> pizzas = PizzaDB.getPizzas();
				HashMap<Integer,Integer> pizzasupdate = new HashMap<>();
				for(Pizza b : pizzas){
					log("pizza name: " + b.getPizzaid() + " quantity : " + b.getQuantity().size());
					pizzasupdate.put(b.getPizzaid(), b.getQuantity().size());
				}
				//log(books.get(0).getBookname() + " " + books.get(1).getBookname());
				Delivery delivery=new Delivery(address,"Cash on Delivery",
						city,state,Integer.parseInt(zipcode));
				DeliveryDB.insertDelivery(delivery);
				Payment payment=new Payment(cardnumber,Integer.parseInt(expirymonth),
						Integer.parseInt(expiryyear),
						Integer.parseInt(cvv));
				PaymentDB.insertPayment(payment);
				ArrayList<Cart> carts = CartDB.getCartDetails();
				ArrayList<String> updateStore = new ArrayList();
				for(Cart c: carts){
					log("cart id : " + Integer.toString(c.getPizzaid()));
					log("quantity from Map : "+Integer.toString(pizzasupdate.get(c.getPizzaid())));
					int quantity = pizzasupdate.get(c.getPizzaid()) - c.getQuantity();
					updateStore.add(c.getPizzaid() + "##" + quantity);
				}
				PizzaDB.updatePizzaDetails(updateStore);
				CartDB.deleteCartDetails();		

				url="/thanks.jsp";				
			}
		}

		// This action redirects to User Landing page
		if(action.equals("Buy_Here")){			
			ArrayList<Pizza> pizzas = PizzaDB.getPizzas();

			request.setAttribute("pizzas", pizzas);            
			url = "/UserLanding.jsp";

		}

		// This action invalidates the session	
		if(action.equals("logout")){
			session.invalidate();
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
