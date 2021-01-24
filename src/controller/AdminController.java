package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.PizzaDB;
import Data.DeliveryDB;
import Data.UserDB;
import Data.PaymentDB;
import model.Pizza;
import model.Delivery;
import model.User;
import model.Payment;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String url="/AdminLanding.jsp";
		String action=request.getParameter("action");
		log("action : " + action);
		if(action.equals("")||action==null||action.isEmpty()){
			url="AdminLanding.jsp";
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
			
		if(action.equals("ViewBooks")){
			ArrayList<Pizza> pizzas = PizzaDB.getPizzaDetails();
			request.setAttribute("pizzas", pizzas);
	          url = "/ViewBooks.jsp";
		}
		log(action);
		if(action.equals("ViewCustomers")){
			ArrayList<User> users = UserDB.getUsers();
			request.setAttribute("users", users);
	          url = "/ViewCustomers.jsp";
		}
		if(action.equals("ViewPayments")){
			ArrayList<Payment> payments = PaymentDB.getPayment();
			request.setAttribute("payments", payments);
	          url = "/ViewPayments.jsp";
		}
		
		if(action.equals("ViewDelivery")){
			ArrayList<Delivery> ds = DeliveryDB.getDeliveryDetails();
			request.setAttribute("ds", ds);
	          url = "/ViewDelivery.jsp";
		}
		
		if(action.equals("ModifyBooks")){
			ArrayList<Pizza> books = PizzaDB.getPizzaDetails();
			request.setAttribute("books", books);
	          url = "/ModifyBooks.jsp";
		}
		
		
		/*
		if(action.equals("DeleteCustomers")){
			ArrayList<User> users = UserDB.getUsers();
			request.setAttribute("users", users);
	          url = "/ModifyCustomers.jsp";
		}
		
		
		
		if(action.equals("Deletepayments")){
			ArrayList<Payment> payments = PaymentDB.getPayment();
			request.setAttribute("payments", payments);
	          url = "/ModifyPayments.jsp";
		}
		
		if(action.equals("DeleteDelivery")){
			ArrayList<Delivery> ds = DeliveryDB.getDeliveryDetails();
			request.setAttribute("ds", ds);
	          url = "/ModifyDelivery.jsp";
		}*/
		
		if(action.equals("UpdateBookChanges")){
			
			String[] updates = request.getParameterValues("update");
			Set<String> updatesSet = new HashSet<>();
			String[] deletes = request.getParameterValues("delete");
			Set<String> deletesSet = new HashSet<String>();
			HashMap<String,String> updateAdmin = new HashMap<>();
			ArrayList<String> deleteAdmin = new ArrayList();
			// update the changes made by admin
			if(updates != null || deletes != null){
				String[] bookids = request.getParameterValues("bookid");
				String[] booknames = request.getParameterValues("bookname");
				String[] bookprices = request.getParameterValues("bookprice");
				String[] bookquantity = request.getParameterValues("bookquantity");
				if(updates != null && deletes != null){
					 updatesSet = new HashSet<String>(Arrays.asList(updates));
					 deletesSet = new HashSet<String>(Arrays.asList(deletes));
					for(int i=0;i<bookids.length; i++){
						if(updatesSet.contains(bookids[i])){
							updateAdmin.put(bookids[i],booknames[i] + "##" + bookprices[i] + "##" + bookquantity[i]);
							updatesSet.remove(bookids[i]);
						}
						if(deletesSet.contains(bookids[i])){
							deleteAdmin.add(bookids[i]);
							deletesSet.remove(bookids[i]);
						}
					}
					PizzaDB.updatePizzas_Admin(updateAdmin);
					PizzaDB.deletePizzas_Admin(deleteAdmin);
				}else{
					if(updates != null){
						updatesSet = new HashSet<String>(Arrays.asList(updates));
						for(int i=0;i<bookids.length; i++){
							if(updatesSet.contains(bookids[i])){
								updateAdmin.put(bookids[i],booknames[i] + "##" + bookprices[i] + "##" + bookquantity[i]);
								updatesSet.remove(bookids[i]);
							}
						}
						PizzaDB.updatePizzas_Admin(updateAdmin);
					}else{
						deletesSet = new HashSet<String>(Arrays.asList(deletes));
						for(int i=0;i<bookids.length; i++){
							if(deletesSet.contains(bookids[i])){
								deleteAdmin.add(bookids[i]);
								deletesSet.remove(bookids[i]);
							}
						}
						PizzaDB.deletePizzas_Admin(deleteAdmin);
					}
				}
				
			}
			
			
			ArrayList<Pizza> pizzas = PizzaDB.getPizzaDetails();
			request.setAttribute("pizzas", pizzas);
	          url = "/ViewBooks.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
