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
public class Vuelo {
    
    private int id;
    private String fecha;
    private String hora;
    private int avion_id;
    private String origen;
    private String destino;
    private double precio;

    public Vuelo(int id) {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM vuelo WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
            this.id = rs.getInt("id");
            fecha = rs.getString("fecha");
            hora = rs.getString("hora");
            avion_id = rs.getInt("avion_id");
            origen = rs.getString("origen");
            destino = rs.getString("destino");
            precio = rs.getDouble("precio");
        } catch (SQLException ex) {
            System.out.println("Vuelo@Vuelo: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }

    public Vuelo(String fecha, String hora, int avion_id, String origen, String destino, double precio) {
        this.fecha = fecha;
        this.hora = hora;
        this.avion_id = avion_id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }

    public Vuelo(int id, String fecha, String hora, int avion_id, String origen, String destino, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.avion_id = avion_id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getAvion_id() {
        return avion_id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getPrecio() {
        return precio;
    }
    
    public void commit() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("INSERT INTO vuelo (fecha, hora, avion_id, origen, destino, precio) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, avion_id);
            ps.setString(4, origen);
            ps.setString(5, destino);
            ps.setDouble(6, precio);
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Vuelo@commit: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
    }
    
    public static ArrayList<Vuelo> get() {
        DatabaseConnector dc = new DatabaseConnector();
        Connection con = dc.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM vuelo");
            rs = ps.executeQuery();
            while(rs.next())
                vuelos.add(new Vuelo(rs.getInt("id"), rs.getString("fecha"), rs.getString("hora"), rs.getInt("avion_id"), rs.getString("origen"), rs.getString("destino"), rs.getDouble("precio")));
        } catch (SQLException ex) {
            System.out.println("Vuelo@get: " + ex.getMessage());
        }
        dc.closeConnection(con, ps, rs);
        return vuelos;
    }
    
    public Avion avion() {
        return new Avion(avion_id);
    }
    
    public Aeropuerto origen() {
        return new Aeropuerto(origen);
    }
    
    public Aeropuerto destino() {
        return new Aeropuerto(destino);
    }
    
    @Override
    public String toString() {
        return "Vuelo[" + id + "] {\n\tfecha: " + fecha + "\n\thora: " + hora + "\n\tavion_id: " + avion_id + "\n\torigen: " + origen + "\n\tdestino: " + destino + "\n\tprecio: " + precio + "\n}" ;
    }
    
}
