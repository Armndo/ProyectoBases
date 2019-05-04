package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Armando
 */
public class DatabaseConnector {
    
    private final String URL = "jdbc:mysql://localhost:3306/vuelos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";    
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";   
    private final String USER = "root";   
    private final String PASSWORD = "root";
    private Connection con;
   
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD); 
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("DatabaseConnector@getConnection: " + e.getMessage());
        }
        return con;
    }
    
    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if(con != null)
                con.close();
            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            System.err.println("DatabaseConnector@closeConnection: " + e.getMessage());
        }
    }
    
    public void closeConnection(Connection con, PreparedStatement ps) {
        try {
            if(con != null)
                con.close();
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            System.err.println("DatabaseConnector@closeConnection: " + e.getMessage());
        }
    }
    
}