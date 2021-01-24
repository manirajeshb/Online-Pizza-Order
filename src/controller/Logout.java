package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Logout() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		String url="/Index.jsp";
		String action=request.getParameter("action");
		if(action.equals("logout")){
		request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/Index.jsp");
        url="/Index.jsp";
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
