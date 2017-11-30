package modelo;

public class Persona {
    private String ced;
    private String nombres;
    private String apellidos;
    private int edad;
    
    public Persona(){
        
    }
    
    public Persona(String ced, String nombres, String apellidos, int edad){
        this.ced = ced;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    
    public String getCed(){
        return ced;
    }
    
    public void setCed(String ced){
        this.ced = ced;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}
