package MODEL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Armando
 */
public class Empleado {
    
    private int id;
    private String puesto;
    private int aerolinea_id;

    public Empleado(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM empleado WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            puesto = rs.getString("puesto");
            aerolinea_id = rs.getInt("aerolinea_id");
        } catch (SQLException ex) {
            System.out.println("Empleado@Empleado: No existe el empleado con el id = " + id);
        }
        dc.closeConnection(con, ps, rs);
    }

    public Empleado(String puesto, int aerolinea_id) {
        this.puesto = puesto;
        this.aerolinea_id = aerolinea_id;
    }
    
    public Empleado(int id, String puesto, int aerolinea_id) {
        this.id = id;
        this.puesto = puesto;
        this.aerolinea_id = aerolinea_id;
    }

    public int getId() {
        return id;
    }

    public String getPuesto() {
        return puesto;
    }

    public int getAerolinea_id() {
        return aerolinea_id;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO empleado VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, puesto);
            if(aerolinea_id != 0)
                ps.setInt(3, aerolinea_id);
            else
                ps.setNull(3, Types.INTEGER);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Empleado@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public static ArrayList<Empleado> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM empleado");
            rs = ps.executeQuery();
            while(rs.next())
                empleados.add(new Empleado(rs.getInt("id"), rs.getString("puesto"), rs.getInt("aerolinea_id")));
        } catch(SQLException ex) {
            System.out.println("Empleado@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return empleados;
    }
    
    public static ArrayList<Empleado> search(String data) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            data = "%" + data + "%";
            ps = con.prepareStatement("SELECT id, puesto, aerolinea_id FROM empleado NATURAL JOIN persona WHERE nombre LIKE ? OR appaterno LIKE ? OR puesto LIKE ? OR sexo LIKE ?");
            ps.setString(1, data);
            ps.setString(2, data);
            ps.setString(3, data);
            ps.setString(4, data);
            rs = ps.executeQuery();
            while(rs.next())
                empleados.add(new Empleado(rs.getInt("id"), rs.getString("puesto"), rs.getInt("aerolinea_id")));
        } catch(SQLException ex) {
            System.out.println("Empleado@search: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return empleados;
    }
    
    public void update(int id, String puesto, int aerolinea_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE empleado SET puesto = ?, aerolinea_id = ? WHERE id = ?");
            ps.setString(1, puesto);
            if(aerolinea_id != 0)
                ps.setInt(2, aerolinea_id);
            else
                ps.setNull(2, Types.INTEGER);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Empleado@update: " + ex.getMessage());
        } finally {
            this.puesto = puesto;
            this.aerolinea_id = aerolinea_id;
        }
        dc.closeConnection(con, ps);
    }
    
    public Persona persona() {
        return new Persona(id);
    }
    
    public Aerolinea aerolinea() {
        Aerolinea aerolinea = new Aerolinea(aerolinea_id);
        return aerolinea.getId() != 0 ? aerolinea : null;
    }
    
    @Override
    public String toString() {
        return "\nEmpleado[" + id + "] {\n\tpuesto: " + puesto + "\n\taerolinea_id: " + aerolinea_id + "\n}";
    }
    
}