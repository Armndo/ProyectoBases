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
public class Clase {
    
    private int id;
    private String nombre;

    public Clase(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM clase WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            nombre = rs.getString("nombre");
        } catch (SQLException ex) {
            System.out.println("Clase@Clase: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Clase(String nombre) {
        this.nombre = nombre;
    }

    public Clase(int id, String nombre) {
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
            ps = con.prepareStatement("INSERT INTO clase (nombre) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Clase@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public ArrayList<Clase> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Clase> clases = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM clase");
            rs = ps.executeQuery();
            while(rs.next())
                clases.add(new Clase(rs.getInt("id"), rs.getString("nombre")));
        } catch (SQLException ex) {
            System.out.println("Clase@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return clases;
    }
    
}