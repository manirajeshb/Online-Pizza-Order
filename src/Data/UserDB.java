package Data;
import java.sql.*;
import java.util.ArrayList;

import Data.ConnectionPool;
import Data.DBUtil;
import model.User;


public class UserDB {
	// signup.jsp page | registration
    public static int insertUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO customer (lastname,firstname,gender,usertype,email,password) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, user.getLastname());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getGender());	
            ps.setString(4, user.getUserType());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User getUser(String email)
    {
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;
      String query = "SELECT * from customer WHERE email = ?";
      try
      {
        ps = connection.prepareStatement(query);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.next())
        {
          User user = new User();
          user.setFirstname(rs.getString("firstname"));
          user.setEmail(rs.getString("email"));
          user.setUserType(rs.getString("userType"));
          user.setLastname(rs.getString("lastname"));
         
          user.setGender(rs.getString("gender"));
          user.setPassword(rs.getString("password"));
          return user;
        }
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
      return null;
    }
    public static ArrayList<User> getUsers()
    {
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;
      String query = "SELECT * from customer";
      try
      {
        ps = connection.prepareStatement(query);       
        rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while(rs.next())
        {
          User user = new User();
          
          user.setFirstname(rs.getString("FirstName"));
          user.setLastname(rs.getString("LastName"));
          user.setGender(rs.getString("Gender"));
          user.setEmail(rs.getString("Email"));
          user.setPassword(rs.getString("Password"));
          users.add(user);
        }
        return users;
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
    
    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE customer SET "
                + "firstname = ?, "
                + "lastname = ? "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM customer "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT email FROM customer "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
   
    // check if user exists in the database or not for login
    public static boolean selectUser(String email,String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM customer "
                + "WHERE email = ? and Password = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return false;
    }
    
   
}
