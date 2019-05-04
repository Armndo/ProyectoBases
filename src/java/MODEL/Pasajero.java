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
public class Pasajero {
    
    private int id;
    private String pasaporte;

    public Pasajero(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM pasajero WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            pasaporte = rs.getString("pasaporte");
        } catch (SQLException ex) {
            if(!ex.getSQLState().equals("S1000"))
                System.out.println("Pasajero@Pasajero: No existe el pasajero con el id = " + id);
        }
        dc.closeConnection(con, ps, rs);
    }

    public Pasajero(String pasaporte) {
        this.pasaporte = pasaporte;
    }
    
    public Pasajero(int id, String pasaporte) {
        this.id = id;
        this.pasaporte = pasaporte;
    }

    public int getId() {
        return id;
    }

    public String getPasaporte() {
        return pasaporte;
    }
        
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO pasajero VALUES (?, ?)");
            ps.setInt(1, id);
            ps.setString(2, pasaporte);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Pasajero@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public static ArrayList<Pasajero> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM pasajero");
            rs = ps.executeQuery();
            while(rs.next())
                pasajeros.add(new Pasajero(rs.getInt("id"), rs.getString("pasaporte")));
        } catch(SQLException ex) {
            System.out.println("Pasajero@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return pasajeros;
    }
    
    public static ArrayList<Pasajero> search(String data) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        try {
            data = "%" + data + "%";
            ps = con.prepareStatement("SELECT id, pasaporte FROM pasajero NATURAL JOIN persona WHERE nombre LIKE ? OR appaterno LIKE ? OR pasaporte LIKE ? OR sexo LIKE ?");
            ps.setString(1, data);
            ps.setString(2, data);
            ps.setString(3, data);
            ps.setString(4, data);
            rs = ps.executeQuery();
            while(rs.next())
                pasajeros.add(new Pasajero(rs.getInt("id"), rs.getString("pasaporte")));
        } catch(SQLException ex) {
            System.out.println("Pasajero@search: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return pasajeros;
    }
    
    public void update(int id, String pasaporte) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE pasajero SET pasaporte = ? WHERE id = ?");
            ps.setString(1, pasaporte);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Pasajero@update: " + ex.getMessage());
        } finally {
            this.pasaporte = pasaporte;
        }
        dc.closeConnection(con, ps);
    }
    
    public Persona persona() {
        return new Persona(id);
    }
    
    @Override
    public String toString() {
        return "\nPasajero[" + id + "] {\n\tpasaporte: " + pasaporte + "\n}";
    }
    
}