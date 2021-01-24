package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cart;
import model.User;

public class CartDB {
	
	
	public static int insertCart(ArrayList<Cart> carts) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO cart (PizzaName,quantity,price,total_price,pizzaid) "
                + "VALUES (?,?,?,?,?)";
        
    	try {
    		for(Cart cart:carts){
            ps = connection.prepareStatement(query);
            
            ps.setString(1, cart.getPizzaName());
            ps.setInt(2, cart.getQuantity());
            ps.setFloat(3, cart.getPrice());
            ps.setFloat(4, cart.getTotal_price());
            ps.setInt(5, cart.getPizzaid());
           
            ps.executeUpdate();
    		}
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
		return 0;
    }
	
	
	public static void deleteCartDetails(){
		
		ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String query = "delete from cart";
	      try
	      {
	        ps = connection.prepareStatement(query);
	        ps.executeUpdate();        
	        
	      }
	      catch (SQLException e)
	      {
	        System.out.println(e);
	      }
	      finally 
	      {
	        DBUtil.closeResultSet(rs);
	        DBUtil.closePreparedStatement(ps);
	        pool.freeConnection(connection);
	      }
	    }

	
		 public static ArrayList<Cart> getCartDetails()
		    {
		      ConnectionPool pool = ConnectionPool.getInstance();
		      Connection connection = pool.getConnection();
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      String query = "SELECT * from cart";
		      try
		      {
		        ps = connection.prepareStatement(query);
		        rs = ps.executeQuery();
		        ArrayList<Cart> carts = new ArrayList<>();
		        while(rs.next())
		        {
		          Cart cart = new Cart();
		          cart.setPizzaName(rs.getString("PizzaName"));
		          cart.setQuantity(rs.getInt("quantity"));
		          cart.setPrice(rs.getFloat("Price"));
		          cart.setTotal_price(rs.getFloat("total_price"));
		          cart.setPizzaid(rs.getInt("pizzaid"));
		          
		          carts.add(cart);
		        
		        }
		        return carts;
		      }
		      catch (SQLException e)
		      {
		        System.out.println(e);
		        return null;
		      }
		      finally 
		      {
		        DBUtil.closeResultSet(rs);
		        DBUtil.closePreparedStatement(ps);
		        pool.freeConnection(connection);
		      }
		    }
	}


