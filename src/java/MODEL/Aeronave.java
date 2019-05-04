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
public class Aeronave {
    
    private int id;
    private String modelo;
    private int capacidad;
    private int fabricante_id;

    public Aeronave(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM aeronave WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            modelo = rs.getString("modelo");
            capacidad = rs.getInt("capacidad");
            fabricante_id = rs.getInt("fabricante_id");
        } catch (SQLException ex) {
            System.out.println("Aeronave@Aeronave: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Aeronave(String modelo, int fabricante_id, int capacidad) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.fabricante_id = fabricante_id;
    }

    public Aeronave(int id, String modelo, int capacidad, int fabricante_id) {
        this.id = id;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.fabricante_id = fabricante_id;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getFabricante_id() {
        return fabricante_id;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO aeronave (modelo, capacidad, fabricante_id) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, modelo);
            ps.setInt(2, capacidad);
            ps.setInt(3, fabricante_id);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Aeronave@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public static ArrayList<Aeronave> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeronave> aeronaves = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM aeronave");
            rs = ps.executeQuery();
            while(rs.next())
                aeronaves.add(new Aeronave(rs.getInt("id"), rs.getString("modelo"), rs.getInt("capacidad"), rs.getInt("fabricante_id")));
        } catch(SQLException ex) {
            System.out.println("Aeronave@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeronaves;
    }
    
    public static ArrayList<Aeronave> search(String data) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeronave> aeronaves = new ArrayList<>();
        try {
            data = "%" + data + "%";
            ps = con.prepareStatement("SELECT a.id, a.modelo, a.capacidad, a.fabricante_id FROM aeronave a, fabricante f WHERE a.fabricante_id = f.id AND (modelo LIKE ? OR f.nombre LIKE ?)");
            ps.setString(1, data);
            ps.setString(2, data);
            rs = ps.executeQuery();
            while(rs.next())
                aeronaves.add(new Aeronave(rs.getInt("id"), rs.getString("modelo"), rs.getInt("capacidad"), rs.getInt("fabricante_id")));
        } catch(SQLException ex) {
            System.out.println("Aeronave@search: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeronaves;
    }
    
    public void update(int id, String modelo, int capacidad, int fabricante_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE aeronave SET modelo = ?, capacidad = ?, fabricante_id = ? WHERE id = ?");
            ps.setString(1, modelo);
            ps.setInt(2, capacidad);
            ps.setInt(3, fabricante_id);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aeronave@update: " + ex.getMessage());
        } finally {
            this.modelo = modelo;
            this.capacidad = capacidad;
            this.fabricante_id = fabricante_id;
        }
        dc.closeConnection(con, ps);
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM aeronave WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Aeronave@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public ArrayList<Aerolinea> aerolineas() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aerolinea> aerolineas = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT a.id, a.nombre FROM avion av, aerolinea a WHERE a.id = av.aerolinea_id and av.aeronave_id = ? GROUP BY a.id");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next())
                aerolineas.add(new Aerolinea(rs.getInt("id"), rs.getString("nombre")));
        } catch(SQLException ex) {
            System.out.println("Aeronave@aerolineas: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aerolineas;
    }
    
    public int count(int aerolinea_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = con.prepareStatement("SELECT aerolinea_id, aeronave_id, count(*) FROM avion WHERE aerolinea_id = ? AND aeronave_id = ?");
            ps.setInt(1, aerolinea_id);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            rs.first();
            count = rs.getInt("count(*)");
            System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getInt(3));
        } catch(SQLException ex) {
            System.out.println("Aeronave@count: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return count;
    }
    
    public Fabricante fabricante() {
        return new Fabricante(fabricante_id);
    }
    
    @Override
    public String toString() {
        return "\nAeronave[" + id + "] {\n\tmodelo: " + modelo + "\n\tcapacidad: " + capacidad +" \n\tfabricante_id: " + fabricante_id + "\n}";
    }
    
}