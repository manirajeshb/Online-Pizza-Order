package controller;

import java.io.IOException;

import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.DeliveryDB;
import model.Delivery;

import Data.PaymentDB;
import model.Payment;

/**
 * Servlet implementation class DeliveryController
 */
@WebServlet("/DeliveryController")
public class DeliveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String url="/delivery.jsp";
		String action=request.getParameter("action");
		log("action : " + action);
		if(action==null||action.isEmpty()||"".equalsIgnoreCase(action)){
			url="delivery.jsp";
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
		
		if(action.equals("Place Your Order")){
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String zipcode=request.getParameter("zipcode");
			
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
			
			else if(state.length()<4){
	        	request.setAttribute("msg", "Please enter a valid state name");
		        request.setAttribute("state", state);
		        url = "/delivery.jsp";	        	
	        }
			else if(zipcode.length()!=6){
	        	request.setAttribute("msg", "Please enter your 6-digit zipcode");
		        request.setAttribute("zipcode", zipcode);
		        url = "/delivery.jsp";	        	
	        }
			else{
				url="/payment.jsp";
				
			}
		}
		
		if(action.equals("Make a payment")){
			String cardnumber=request.getParameter("cardnumber");
			String expirymonth=request.getParameter("month");
			String expiryyear=request.getParameter("year");
			String cvv=request.getParameter("cvv");
			
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String zipcode=request.getParameter("zipcode");
			if(cardnumber.length()!=16){
	        	request.setAttribute("msg", "Please enter a 16-digit card number");
		        request.setAttribute("cardnumber", cardnumber);
		        url = "/payment.jsp";	        	
	        }
			
			else if(cvv.length()!=3){
	        	request.setAttribute("msg", "Please enter a valid 3-digit cvv number");
		        request.setAttribute("cvv", cvv);
		        url = "/payment.jsp";	        	
	        }
			else{

				Delivery delivery=new Delivery(address,"Cash on Delivery",
						city,state,Integer.parseInt(zipcode));
				DeliveryDB.insertDelivery(delivery);
				Payment payment=new Payment(cardnumber,Integer.parseInt(expirymonth),
						Integer.parseInt(expiryyear),
						Integer.parseInt(cvv));
				PaymentDB.insertPayment(payment);
				url="/thanks.jsp";
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
