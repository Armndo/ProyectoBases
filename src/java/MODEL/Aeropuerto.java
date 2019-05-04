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
public class Aeropuerto {
    
    private String iata;
    private String nombre;
    private int pais_id;

    public Aeropuerto(String iata) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM aeropuerto WHERE iata = ?");
            ps.setString(1, iata);
            rs = ps.executeQuery();
            rs.first();
            this.iata= rs.getString("iata");
            nombre = rs.getString("nombre");
            pais_id = rs.getInt("pais_id");
        } catch (SQLException ex) {
            System.out.println("Aeropuerto@Aeropuerto: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Aeropuerto(String nombre, int pais_id) {
        this.nombre = nombre;
        this.pais_id = pais_id;
    }
    
    public Aeropuerto(String iata, String nombre, int pais_id) {
        this.iata = iata;
        this.nombre = nombre;
        this.pais_id = pais_id;
    }

    public String getIata() {
        return iata;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPais_id() {
        return pais_id;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO aeropuerto VALUES (?, ?, ?)");
            ps.setString(1, iata);
            ps.setString(2, nombre);
            ps.setInt(3, pais_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aeropuerto@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public static ArrayList<Aeropuerto> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM aeropuerto");
            rs = ps.executeQuery();
            while(rs.next())
                aeropuertos.add(new Aeropuerto(rs.getString("iata"), rs.getString("nombre"), rs.getInt("pais_id")));
        } catch(SQLException ex) {
            System.out.println("Aeropuerto@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeropuertos;
    }
    
    public static ArrayList<Aeropuerto> search(String data) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
        try {
            data = "%" + data + "%";
            ps = con.prepareStatement("SELECT * FROM aeropuerto WHERE iata LIKE ? OR nombre LIKE ?");
            ps.setString(1, data);
            ps.setString(2, data);
            rs = ps.executeQuery();
            while(rs.next())
                aeropuertos.add(new Aeropuerto(rs.getString("iata"), rs.getString("nombre"), rs.getInt("pais_id")));
        } catch(SQLException ex) {
            System.out.println("Aeropuerto@search: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeropuertos;
    }
    
    public void update(String u_iata, String iata, String nombre, int pais_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE aeropuerto SET iata = ?, nombre = ?, pais_id = ? WHERE iata = ?");
            ps.setString(1, iata);
            ps.setString(2, nombre);
            ps.setInt(3, pais_id);
            ps.setString(4, u_iata);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aeropuerto@update: " + ex.getMessage());
        } finally {
            this.iata = iata;
            this.nombre = nombre;
            this.pais_id = pais_id;
        }
        dc.closeConnection(con, ps);
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM aeropuerto WHERE iata = ?");
            ps.setString(1, iata);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aeropuerto@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public Pais pais() {
        return new Pais(pais_id);
    }
    
    @Override
    public String toString() {
        return "\nAeropuerto[" + iata + "] {\n\tnombre: " + nombre + "\n\tpais_id: " + pais_id + "\n}";
    }
    
}