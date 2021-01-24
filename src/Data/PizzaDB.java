package data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Pizza;


public class PizzaDB {
	 public static ArrayList<Pizza> getPizzas()
	    {
	      ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String query = "SELECT * from pizza WHERE availability = ?";
	      try
	      {
	        ps = connection.prepareStatement(query);
	        ps.setString(1, "Yes");
	        rs = ps.executeQuery();
	        ArrayList<Pizza> pizzas = new ArrayList<>();
	        while(rs.next())
	        {
	          Pizza pizza = new Pizza();
	          pizza.setPizzaid(rs.getInt("pizzaid"));
	          pizza.setPizzaName(rs.getString("PizzaName"));
	          pizza.setPrice(rs.getFloat("Price"));
	          
	          ArrayList<Integer> quantity_list = new ArrayList<>();
	          int quantity_db = rs.getInt("Quantity");
	          int i = 1;
	          while(i <= quantity_db){
	        	  quantity_list.add(i);
	        	  i++;
	          }
	          pizza.setQuantity(quantity_list);
	          pizzas.add(pizza);
	        }
	        return pizzas;
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
	 
	 public static ArrayList<Pizza> getPizzaDetails()
	    {
	      ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String query = "SELECT * from pizza";
	      try
	      {
	        ps = connection.prepareStatement(query);
	        rs = ps.executeQuery();
	        ArrayList<Pizza> pizzas = new ArrayList<>();
	        while(rs.next())
	        {
	          Pizza pizza = new Pizza();
	          pizza.setPizzaid(rs.getInt("pizzaid"));
	          pizza.setPizzaName(rs.getString("PizzaName"));
	          pizza.setPrice(rs.getFloat("Price"));
	          
	          ArrayList<Integer> quantity_list = new ArrayList<>();
	          int quantity_db = rs.getInt("Quantity");
	          int i = 1;
	          while(i <= quantity_db){
	        	  quantity_list.add(i);
	        	  i++;
	          }
	          pizza.setQuantity(quantity_list);
	          pizzas.add(pizza);
	        }
	        return pizzas;
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
	 
	 public static void updatePizzaDetails(ArrayList<String> updatestore){
		 ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      ArrayList<Integer> pizzaids = new ArrayList<>();
	      try
	      {
	    	for(String s : updatestore){
	    		int quantity = Integer.parseInt(s.split("##")[1]);
	    		if(quantity <= 0){
	    			String query = "update pizza set availability=?, quantity=? where pizzaid = ?";
	    			ps = connection.prepareStatement(query);
		    		ps.setString(1, "NO");
		    		//System.out.println();
		    		ps.setInt(2, quantity);
		    		//System.out.println(quantity);
		    	    ps.setInt(3,Integer.parseInt(s.split("##")[0]));
		    	    //System.out.println(s.split("##")[0]);
		    		ps.executeUpdate();
	    		}else{
	    			String query = "update pizza set quantity=? where pizzaid = ?";
		    		ps = connection.prepareStatement(query);
		    		ps.setInt(1, quantity);
		    	    ps.setInt(2,Integer.parseInt(s.split("##")[0]));
		    		ps.executeUpdate();
	    		}
	    	}
	    	
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
	 
	 public static void updatePizzas_Admin(HashMap<String,String> updatePizzas){
		 ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      
	      try
	      {
	    	for(String bookKey : updatePizzas.keySet()){
	    		String value = updatePizzas.get(bookKey);
	    		int quantity = Integer.parseInt(value.split("##")[2]);
	    		if(quantity <=0){
	    			String query = "update pizza set PizzaName=?, price=?, quantity=?, availability=? where pizzaid = ?";
		    		ps = connection.prepareStatement(query);
		    		ps.setString(1, value.split("##")[0]);
		    	    ps.setFloat(2,Float.parseFloat(value.split("##")[1]));
		    	    ps.setInt(3,quantity);
		    	    ps.setString(4, "NO");
		    	    ps.setInt(5,Integer.parseInt(bookKey));
		    		ps.executeUpdate();
	    		}else{
	    			String query = "update pizza set PizzaName=?, price=?, quantity=?,availability=? where pizzaid = ?";
		    		ps = connection.prepareStatement(query);
		    		ps.setString(1, value.split("##")[0]);
		    	    ps.setFloat(2,Float.parseFloat(value.split("##")[1]));
		    	    ps.setInt(3,quantity);
		    	    ps.setString(4,"Yes");
		    	    ps.setInt(5,Integer.parseInt(bookKey) );
		    	    
		    		ps.executeUpdate();
	    		}
	    		
	    	}
	    	
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
	 
	 public static void deletePizzas_Admin(ArrayList<String> deletepizzas){
		 ConnectionPool pool = ConnectionPool.getInstance();
	      Connection connection = pool.getConnection();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      
	      try
	      {
	    	for(String pizzaKey : deletepizzas){
	    		String query = "delete from pizza where PizzaName = ?";
	    		ps = connection.prepareStatement(query);
	    	    ps.setInt(1, Integer.parseInt(pizzaKey));
	    		ps.executeUpdate();
	    	}
	    	
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
}
