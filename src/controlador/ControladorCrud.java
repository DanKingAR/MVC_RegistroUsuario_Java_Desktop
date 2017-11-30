package controlador;
import modelo.*;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorCrud implements ActionListener, KeyListener{
    JFCrud vistaCRUD = new JFCrud();
    PersonaDAO modeloCRUD = new PersonaDAO();
    
    public ControladorCrud(JFCrud vistaCRUD, PersonaDAO modeloCRUD){
        this.vistaCRUD = vistaCRUD;
        this.modeloCRUD = modeloCRUD;
        this.vistaCRUD.btnRegistrar.addActionListener(this);
        this.vistaCRUD.btnListar.addActionListener(this);
        this.vistaCRUD.btnEditar.addActionListener(this);
        this.vistaCRUD.btnEliminar.addActionListener(this);
        this.vistaCRUD.btnGedit.addActionListener(this);
        this.vistaCRUD.txtCedula.addKeyListener(this);
        this.vistaCRUD.txtNombre.addKeyListener(this);
        this.vistaCRUD.txtApellido.addKeyListener(this);
        this.vistaCRUD.txtEdad.addKeyListener(this);
        this.vistaCRUD.txtListar.addKeyListener(this);
    }
    
    public void InicializarCrud(){
        
    }
    
    public void LlenarTabla(JTable tablaD){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("CEDULA");
        modeloT.addColumn("NOMBRES");
        modeloT.addColumn("APELLIDOS");
        modeloT.addColumn("EDAD");
        Object[] columna = new Object[4];
        int numRegistros = modeloCRUD.lstPersona().size();
        for (int i = 0; i < numRegistros; i++){
            columna[0] = modeloCRUD.lstPersona().get(i).getCed();
            columna[1] = modeloCRUD.lstPersona().get(i).getNombres();
            columna[2] = modeloCRUD.lstPersona().get(i).getApellidos();
            columna[3] = modeloCRUD.lstPersona().get(i).getEdad();
            modeloT.addRow(columna);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCRUD.btnRegistrar){
            String cedula = vistaCRUD.txtCedula.getText();
            String nombres = vistaCRUD.txtNombre.getText();
            String apellidos = vistaCRUD.txtApellido.getText();
            int edad = Integer.parseInt(vistaCRUD.txtEdad.getText());
            String rptaRegistro = modeloCRUD.addPersona(cedula, nombres, apellidos, edad);
            if (rptaRegistro != null)
                JOptionPane.showMessageDialog(null, rptaRegistro);
            else
                JOptionPane.showMessageDialog(null, "El Registro no se Puede Guardar");
            LlenarTabla(vistaCRUD.jtDatos);
        }
        if (e.getSource() == vistaCRUD.btnListar){
            LlenarTabla(vistaCRUD.jtDatos);
        }
        if (e.getSource() == vistaCRUD.btnEditar){
            int filaEditar = vistaCRUD.jtDatos.getSelectedRow();
            int numFS = vistaCRUD.jtDatos.getSelectedRowCount();
            if (filaEditar >= 0 && numFS == 1){
                vistaCRUD.txtCedula.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 0)));
                vistaCRUD.txtNombre.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 1)));
                vistaCRUD.txtApellido.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 2)));
                vistaCRUD.txtEdad.setText(String.valueOf(vistaCRUD.jtDatos.getValueAt(filaEditar, 3)));
                vistaCRUD.txtCedula.setEnabled(false);
                vistaCRUD.btnEditar.setEnabled(false);
                vistaCRUD.btnEliminar.setEnabled(false);
                vistaCRUD.btnRegistrar.setEnabled(false);
                vistaCRUD.btnListar.setEnabled(false);
            }
            else
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una Fila");
        }
        if (e.getSource() == vistaCRUD.btnGedit){
            String cedula = vistaCRUD.txtCedula.getText();
            String nombres = vistaCRUD.txtNombre.getText();
            String apellidos = vistaCRUD.txtApellido.getText();
            int edad = Integer.parseInt(vistaCRUD.txtEdad.getText());
            int rptaEdit = modeloCRUD.editPer(cedula, nombres, apellidos, edad);
            if (rptaEdit > 0)
                JOptionPane.showMessageDialog(null, "Edición Exitosa");
            else
                JOptionPane.showMessageDialog(null, "Fallo en la Edición");
            Clear();
            vistaCRUD.btnEditar.setEnabled(true);
            vistaCRUD.btnEliminar.setEnabled(true);
            vistaCRUD.btnRegistrar.setEnabled(true);
            vistaCRUD.txtListar.setEnabled(true);
            vistaCRUD.btnGedit.setEnabled(true);
            LlenarTabla(vistaCRUD.jtDatos);
        }
        if (e.getSource() == vistaCRUD.btnEliminar){
            int filaInicio = vistaCRUD.jtDatos.getSelectedRow();
            int numFS = vistaCRUD.jtDatos.getSelectedRowCount();
            ArrayList<String> lstCedula = new ArrayList();
            String cedula = "";
            if (filaInicio > 0){
                for (int i = 0; i < numFS; i++){
                    cedula = String.valueOf(vistaCRUD.jtDatos.getValueAt(i + filaInicio, 0));
                    lstCedula.add(cedula);
                }
                for (int i = 0; i < numFS; i++){
                    int rptaUsuario = JOptionPane.showConfirmDialog(null, "¿Quiere Eliminar el Registro con " + cedula + "?");
                    if (rptaUsuario == 0)
                        modeloCRUD.deletePer(cedula);
                }
                LlenarTabla(vistaCRUD.jtDatos);
            }
            else
                JOptionPane.showMessageDialog(null, "Seleccione Almenos una Fila");
        }
    }
    
    public void Clear(){
        vistaCRUD.txtCedula.setEnabled(true);
        vistaCRUD.txtCedula.setText("");
        vistaCRUD.txtNombre.setText("");
        vistaCRUD.txtApellido.setText("");
        vistaCRUD.txtEdad.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaCRUD.txtCedula || e.getSource() == vistaCRUD.txtEdad){
            char c = e.getKeyChar();
            if (c < '0' || c > '9')
                e.consume();
        }
        if (e.getSource() == vistaCRUD.txtNombre || e.getSource() == vistaCRUD.txtApellido || e.getSource() == vistaCRUD.txtListar){
            char c = e.getKeyChar();
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != (char)KeyEvent.VK_SPACE))
                e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistaCRUD.txtListar){
            String apellidos = vistaCRUD.txtListar.getText();
            DefaultTableModel modeloT = new DefaultTableModel();
            vistaCRUD.jtDatos.setModel(modeloT);
            modeloT.addColumn("CEDULA");
            modeloT.addColumn("NOMBRES");
            modeloT.addColumn("APELLIDOS");
            modeloT.addColumn("EDAD");
            Object [] columna = new Object[4];
            int numregistros = modeloCRUD.searchPer(apellidos).size();
            for (int i = 0; i < numregistros; i++){
                columna[0] = modeloCRUD.searchPer(apellidos).get(i).getCed();
                columna[1] = modeloCRUD.searchPer(apellidos).get(i).getNombres();
                columna[2] = modeloCRUD.searchPer(apellidos).get(i).getApellidos();
                columna[3] = modeloCRUD.searchPer(apellidos).get(i).getEdad();
                modeloT.addRow(columna);
            }
        }
    }
}
