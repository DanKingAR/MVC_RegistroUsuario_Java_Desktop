package crudmvc;
import vista.*;
import modelo.*;
import controlador.*;

public class CrudMvc {

    public static void main(String[] args) {
        JFCrud vistaC = new JFCrud();
        PersonaDAO modeloC = new PersonaDAO();
        ControladorCrud controlaC = new ControladorCrud(vistaC, modeloC);
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
    }
    
}
