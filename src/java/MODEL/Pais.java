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
public class Pais {
    
    private int id;
    private String nombre;
    
    public Pais(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM pais WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            nombre = rs.getString("nombre");
        } catch (SQLException ex) {
            System.out.println("Pais@Pais: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public Pais(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public static ArrayList<Pais> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pais> paises = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM pais");
            rs = ps.executeQuery();
            while(rs.next())
                paises.add(new Pais(rs.getInt("id"), rs.getString("nombre")));
        } catch(SQLException ex) {
            System.out.println("Pais@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return paises;
    }
    
    @Override
    public String toString() {
        return "\nPais[" + id + "] {\n\tnombre: " + nombre + "\n}";
    }
    
}
