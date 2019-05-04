package MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Armando
 */
public class Avion {
    
    private int id;
    private int aeronave_id;
    private int aerolinea_id;

    public Avion(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM avion WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            aeronave_id = rs.getInt("aeronave_id");
            aerolinea_id = rs.getInt("aerolinea_id");
        } catch (SQLException ex) {
            System.out.println("Avion@Avion: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Avion(int id, int aeronave_id, int aerolinea_id) {
        this.id = id;
        this.aeronave_id = aeronave_id;
        this.aerolinea_id = aerolinea_id;
    }

    public Avion(int aeronave_id, int aerolinea_id) {
        this.aeronave_id = aeronave_id;
        this.aerolinea_id = aerolinea_id;
    }

    public int getId() {
        return id;
    }

    public int getAeronave_id() {
        return aeronave_id;
    }

    public int getAerolinea_id() {
        return aerolinea_id;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO avion (aeronave_id, aerolinea_id) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, aeronave_id);
            ps.setInt(2, aerolinea_id);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Avion@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public static ArrayList<Avion> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Avion> aviones = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM avion");
            rs = ps.executeQuery();
            while(rs.next())
                aviones.add(new Avion(rs.getInt("id"), rs.getInt("aeronave_id"), rs.getInt("aerolinea_id")));
        } catch(SQLException ex) {
            System.out.println("Avion@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aviones;
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM avion WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Avion@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public Aeronave aeronave() {
        return new Aeronave(aeronave_id);
    }
    
    public Aerolinea aerolinea() {
        return new Aerolinea(aerolinea_id);
    }
    
}
