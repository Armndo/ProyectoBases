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
public class Aerolinea {
    
    private int id;
    private String nombre;

    public Aerolinea(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM aerolinea WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            nombre = rs.getString("nombre");
        } catch (SQLException ex) {
            System.out.println("Aerolinea@Aerolinea: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Aerolinea(String nombre) {
        this.nombre = nombre;
    }

    public Aerolinea(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO aerolinea (nombre) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Aerolinea@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public static ArrayList<Aerolinea> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aerolinea> aerolineas = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM aerolinea");
            rs = ps.executeQuery();
            while(rs.next())
                aerolineas.add(new Aerolinea(rs.getInt("id"), rs.getString("nombre")));
        } catch(SQLException ex) {
            System.out.println("Aerolinea@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aerolineas;
    }
    
    public static ArrayList<Aerolinea> search(String data) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aerolinea> aerolineas = new ArrayList<>();
        try {
            data = "%" + data + "%";
            ps = con.prepareStatement("SELECT * FROM aerolinea WHERE nombre LIKE ?");
            ps.setString(1, data);
            rs = ps.executeQuery();
            while(rs.next())
                aerolineas.add(new Aerolinea(rs.getInt("id"), rs.getString("nombre")));
        } catch(SQLException ex) {
            System.out.println("Aerolinea@search: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aerolineas;
    }
    
    public void update(int id, String nombre) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE aerolinea SET nombre = ? WHERE id = ?");
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aerolinea@update: " + ex.getMessage());
        } finally {
            this.nombre = nombre;
        }
        dc.closeConnection(con, ps);
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM aerolinea WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aerolinea@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public ArrayList<Aeronave> aeronaves() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeronave> aeronaves = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT a.id, a.modelo, a.capacidad, a.fabricante_id FROM avion av, aeronave a WHERE a.id = av.aeronave_id and av.aerolinea_id = ? GROUP BY a.id");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                aeronaves.add(new Aeronave(rs.getInt("id"), rs.getString("modelo"), rs.getInt("capacidad"), rs.getInt("fabricante_id")));
            }
        } catch(SQLException ex) {
            System.out.println("Aerolinea@aeronaves: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeronaves;
    }
    
    public ArrayList<Avion> aviones() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Avion> aviones = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM avion WHERE aerolinea_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                aviones.add(new Avion(rs.getInt("id"), rs.getInt("aeronave_id"), rs.getInt("aerolinea_id")));
            }
        } catch(SQLException ex) {
            System.out.println("Aerolinea@aviones: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aviones;
    }
    
    public int count(int aeronave_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = con.prepareStatement("SELECT count(*) FROM avion WHERE aeronave_id = ? AND aerolinea_id = ?");
            ps.setInt(1, aeronave_id);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            rs.first();
            count = rs.getInt("count(*)");
        } catch(SQLException ex) {
            System.out.println("Aerolinea@count: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return count;
    }
    
    @Override
    public String toString() {
        return "\nAerolinea[" + id + "] {\n\tnombre: " + nombre + "\n}";
    }
    
}
