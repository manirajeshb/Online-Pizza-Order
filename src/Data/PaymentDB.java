package Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Payment;


public class PaymentDB {
	public static int insertPayment(Payment payment) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO payment (cardnumber,expirymonth,expiryyear,cvv) "
                + "VALUES (?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, payment.getCardnumber());
            ps.setInt(2, payment.getExpirymonth());
            ps.setInt(3, payment.getExpiryyear());
            ps.setInt(4, payment.getCvv());	           
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

	
	public static ArrayList<Payment> getPayment()
    {
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      PreparedStatement ps = null;
      ResultSet rs = null;
      String query = "SELECT * from payment";
      try
      {
        ps = connection.prepareStatement(query);       
        rs = ps.executeQuery();
        ArrayList<Payment> payments = new ArrayList<>();
        while(rs.next())
        {
          Payment payment = new Payment();
          payment.setCardnumber(rs.getString("cardnumber"));
          payment.setExpirymonth(rs.getInt("expirymonth"));
          payment.setExpiryyear(rs.getInt("expiryyear"));
          payment.setCvv(rs.getInt("cvv"));
          
          payments.add(payment);
        }
        return payments;
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