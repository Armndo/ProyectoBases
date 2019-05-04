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
public class Usuario {
    
    private int id;
    private String email;
    private String password;
    
    public Usuario(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            email = rs.getString("email");
            password = rs.getString("password");
        } catch (SQLException ex) {
            System.out.println("Usuario@Usuario: No existe el usuario con el id = " + id);
        }
        dc.closeConnection(con, ps, rs);
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Usuario(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        int err = 0;
        try {
            ps = con.prepareStatement("INSERT INTO usuario VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Usuario@commit: " + ex.getMessage());
            err = ex.getErrorCode();
        }
        dc.closeConnection(con, ps);
        return err;
    }
    
    public static ArrayList<Usuario> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM usuario");
            rs = ps.executeQuery();
            while(rs.next())
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("password")));
        } catch(SQLException ex) {
            System.out.println("Usuario@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return usuarios;
    }
    
    public void update(int id, String email, String password) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE usuario SET email = ?, password = ? WHERE id = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Empleado@update: " + ex.getMessage());
        } finally {
            this.email = email;
            this.password = password;
        }
        dc.closeConnection(con, ps);
    }
    
    public void destroy() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Usuario@destroy: " + ex.getMessage());
        }
        dc.closeConnection(con, ps);
    }
    
    public Persona persona() {
        return new Persona(id);
    }
    
    @Override
    public String toString() {
        return "\nUsuario[" + id + "] {\n\temail: " + email + "\n\tpassword: " + password + "\n}";
    }
    
}
