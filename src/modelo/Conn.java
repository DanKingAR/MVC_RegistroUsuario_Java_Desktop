package modelo;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conn {
    
    public Conn(){
        
    }
    
    public Connection getConn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_persona", "root", "");
        } catch (Exception e) {
        }
        return conn;
    }   
}