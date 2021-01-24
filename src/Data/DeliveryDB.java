package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Delivery;


public class DeliveryDB {
	public static int insertDelivery(Delivery delivery) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO delivery (address,deliverytype,city,state,zipcode) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, delivery.getAddress());
            ps.setString(2, delivery.getDeliverytype());
            ps.setString(3, delivery.getCity());
            ps.setString(4, delivery.getState());
            ps.setInt(5, delivery.getZipcode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
	public static ArrayList<Delivery> getDeliveryDetails()
    {
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;
      String query = "SELECT * from delivery";
      try
      {
        ps = connection.prepareStatement(query);       
        rs = ps.executeQuery();
        ArrayList<Delivery> ds = new ArrayList<>();
        while(rs.next())
        {
          Delivery del = new Delivery();
          del.setAddress(rs.getString("address"));
          del.setDeliverytype(rs.getString("deliverytype"));
          del.setCity(rs.getString("city"));
          del.setState(rs.getString("state"));
          del.setZipcode(rs.getInt("zipcode"));
          
          ds.add(del);
        }
        return ds;
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