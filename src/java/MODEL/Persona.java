package MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Armando
 */
public class Persona {
    
    private int id;
    private String nombre;
    private String appaterno;
    private String apmaterno;
    private String direccion;
    private String sexo;
    private int pais_id;
    
    public Persona(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM persona WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            nombre = rs.getString("nombre");
            appaterno = rs.getString("appaterno");
            apmaterno = rs.getString("apmaterno");
            direccion = rs.getString("direccion");
            sexo = rs.getString("sexo");
            pais_id = rs.getInt("pais_id");
        } catch (SQLException ex) {
            System.out.println("Usuario@Usuario: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Persona(String nombre, String appaterno, String apmaterno, String direccion, String sexo, int pais_id) {
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.direccion = direccion;
        this.sexo = sexo;
        this.pais_id = pais_id;
    }

    public Persona(int id, String nombre, String appaterno, String apmaterno, String direccion, String sexo, int pais_id) {
        this.id = id;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.direccion = direccion;
        this.sexo = sexo;
        this.pais_id = pais_id;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public int getPais_id() {
        return pais_id;
    }
    
    public int commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO persona (nombre, appaterno, apmaterno, direccion, sexo, pais_id) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.setString(2, appaterno);
            if(apmaterno != null)
                ps.setString(3, apmaterno);
            else
                ps.setNull(3, Types.VARCHAR);
            if(direccion != null)
                ps.setString(4, direccion);
            else
                ps.setNull(4, Types.VARCHAR);
            if(sexo != null)
                ps.setString(5, sexo);
            else
                ps.setNull(5, Types.VARCHAR);
            ps.setInt(6, pais_id);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Persona@commit: " + ex.getMessage());
            return ex.getErrorCode();
        }
        dc.closeConnection(con, ps, rs);
        return 0;
    }
    
    public static ArrayList<Persona> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM persona");
            rs = ps.executeQuery();
            while(rs.next())
                personas.add(new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("appaterno"), rs.getString("apmaterno"), rs.getString("direccion"), rs.getString("sexo"), rs.getInt("pais_id")));
        } catch(SQLException ex) {
            System.out.println("Persona@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return personas;
    }
    
    public void update(int id, String nombre, String appaterno, String apmaterno, String direccion, String sexo, int pais_id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE persona SET nombre = ?, appaterno = ?, apmaterno = ?, direccion = ?, sexo = ?, pais_id = ? WHERE id = ?");
            ps.setString(1, nombre);
            ps.setString(2, appaterno);
            if(apmaterno != null)
                ps.setString(3, apmaterno);
            else
                ps.setNull(3, Types.VARCHAR);
            if(direccion != null)
                ps.setString(4, direccion);
            else
                ps.setNull(4, Types.VARCHAR);
            if(sexo != null)
                ps.setString(5, sexo);
            else
                ps.setNull(5, Types.VARCHAR);
            ps.setInt(6, pais_id);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Persona@update: " + ex.getMessage());
        } finally {
            this.nombre = nombre;
            this.appaterno = appaterno;
            this.apmaterno = apmaterno;
            this.direccion = direccion;
            this.sexo = sexo;
            this.pais_id = pais_id;
        }
        dc.closeConnection(con, ps);
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM persona WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Persona@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public Pais pais() {
        return new Pais(pais_id);
    }
    
    public Usuario usuario() {
        Usuario usuario = new Usuario(id);
        return usuario.getId() != 0 ? usuario : null;
    }
    
    public Pasajero pasajero() {
        Pasajero pasajero = new Pasajero(id);
        return pasajero.getId() != 0 ? pasajero : null;
    }
    
    public Empleado empleado() {
        Empleado empleado = new Empleado(id);
        return empleado.getId() != 0 ? empleado : null;
    }
    
    @Override
    public String toString() {
        return "\nPersona[" + id + "] {\n\tnombre: " + nombre + "\n\tappaterno: " + appaterno + "\n\tapmaterno: " + apmaterno + "\n\tdireccion: " + direccion + "\n\tsexo: " + sexo + "\n\tpais_id: " + pais_id + "\n}";
    }
    
}