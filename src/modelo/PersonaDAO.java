package modelo;
import java.sql.*;
import java.util.ArrayList;

public class PersonaDAO {
    
    Conn conn;
    
    public PersonaDAO(){
        conn = new Conn();
    }
    
    public String addPersona(String ced, String nombres, String apellidos, int edad){
        String rptaRegistro = null;
        try {
            Connection accesoBD = conn.getConn();
            PreparedStatement ps = accesoBD.prepareStatement("INSERT INTO persona (per_cod, per_nom, per_ape, per_edad) VALUES (?, ?, ?, ?)");
            ps.setString(1, ced);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setInt(4, edad);
            int re = ps.executeUpdate();
            if (re > 0)
                rptaRegistro = "Registro Exitoso";
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
    public ArrayList<Persona> lstPersona(){
        ArrayList lstPersona = new ArrayList();
        Persona persona;
        try {
            Connection accesoBD = conn.getConn();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM persona");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                persona = new Persona();
                persona.setCed(rs.getString(1));
                persona.setNombres(rs.getString(2));
                persona.setApellidos(rs.getString(3));
                persona.setEdad(rs.getInt(4));
                lstPersona.add(persona);
            }
        } catch (Exception e) {
        }
        return lstPersona;
    }
    
    public int editPer(String cedula, String nombres, String apellidos, int edad){
        int numFA = 0;
        try {
            Connection accesoBD = conn.getConn();
            PreparedStatement ps = accesoBD.prepareStatement("UPDATE persona SET per_nom = ?, per_ape = ?, per_edad = ? WHERE per_cod = ?");
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setInt(3, edad);
            ps.setString(4, cedula);
            numFA = ps.executeUpdate();
        } catch (Exception e) {
        }
        return numFA;
    }
    
    public int deletePer(String cedula){
        int numFA = 0;
        try {
            Connection accesoBD = conn.getConn();
            PreparedStatement ps = accesoBD.prepareStatement("DELETE FROM persona WHERE per_cod = ?");
            ps.setString(1, cedula);
            numFA = ps.executeUpdate();
        } catch (Exception e) {
        }
        return numFA;
    }
    
    public ArrayList<Persona> searchPer(String apellidos){
        ArrayList<Persona> lstPersona = new ArrayList();
        Persona persona;
        try {
            Connection accesoBD = conn.getConn();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT * FROM persona WHERE per_ape LIKE CONCAT (?, '%')");
            ps.setString(1, apellidos);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                persona = new Persona();
                persona.setCed(rs.getString("per_cod"));
                persona.setNombres(rs.getString("per_nom"));
                persona.setApellidos(rs.getString("per_ape"));
                persona.setEdad(rs.getInt("per_edad"));
                lstPersona.add(persona);
            }
        } catch (Exception e) {
        }
        return lstPersona;
    }
}
