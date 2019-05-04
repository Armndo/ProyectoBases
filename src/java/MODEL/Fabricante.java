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
public class Fabricante {
    
    private int id;
    private String nombre;

    public Fabricante(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM fabricante WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            nombre = rs.getString("nombre");
        } catch (SQLException ex) {
            System.out.println("Fabricante@Fabricante: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Fabricante(String nombre) {
        this.nombre = nombre;
    }
    
    public Fabricante(int id, String nombre) {
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
            ps = con.prepareStatement("INSERT INTO fabricante (nombre) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Fabricante@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public static ArrayList<Fabricante> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Fabricante> fabricantes = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM fabricante");
            rs = ps.executeQuery();
            while(rs.next())
                fabricantes.add(new Fabricante(rs.getInt("id"), rs.getString("nombre")));
        } catch (SQLException ex) {
            System.out.println("Fabricante@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return fabricantes;
    }

    
    public ArrayList<Aeronave> aeronaves() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aeronave> aeronaves = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM aeronave WHERE fabricante_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next())
                aeronaves.add(new Aeronave(rs.getInt("id"), rs.getString("modelo"), rs.getInt("capacidad"), rs.getInt("fabricante_id")));
        } catch (SQLException ex) {
            System.out.println("Fabricante@aeronaves: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return aeronaves;
    }

    @Override
    public String toString() {
        return "\nFabricante[" + id + "] {\n\tnombre: " + nombre + "\n}";
    }
    
}