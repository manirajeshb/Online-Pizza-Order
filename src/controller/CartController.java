package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.PizzaDB;
import Data.UserDB;
import model.Pizza;
import model.User;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session= request.getSession();
		String url="/cart.jsp";
		String action=request.getParameter("action");
		log("action : " + action);
		if(action==null || action.isEmpty() ||"".equalsIgnoreCase(action)){
			url="cart.jsp";
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
			
		if(action.equals("Proceed_to_checkout")){
			log("Going to delivery page");
			url = "/delivery.jsp";
			log("Delivered");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
