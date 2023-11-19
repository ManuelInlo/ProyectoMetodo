package Controlador;

import Modelo.Modelo;
import Modelo.Paciente;
import Modelo.PersonalTableModel;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteControlador implements ActionListener{

    private ventanaPaciente ventana;
    private final Modelo modelo = new Modelo();

    public PacienteControlador(ventanaPaciente ventana) {
        this.ventana = ventana;
    }
    public void iniciar(){
        ventana.tablaP.setModel(new PersonalTableModel(modelo.getPacientes()));
    }

    public ventanaPaciente getVentana() {
        return ventana;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setVentana(ventanaPaciente ventana) {
        this.ventana = ventana;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==ventana.btAgregar){
            try {
                Paciente np = new Paciente();
                Modelo m = new Modelo();

                // Verifica que el campo de ID no esté vacío antes de intentar la conversión
                if (!ventana.txtId.getText().isEmpty()) {
                    np.setId(Integer.parseInt(ventana.txtId.getText()));
                } else {
                    System.out.println("El campo ID no puede estar vacío.");
                    return;
                }

                np.setNombre(ventana.txtNombre.getText());
                np.setFechaN(ventana.txtFecha.getText());
                np.setEstadoC(ventana.txtEstadoC.getSelectedItem().toString());
                np.setNivelEstud(ventana.txtNE.getSelectedItem().toString());
                np.setOcupacion(ventana.txtOcupacion.getText());  

                // Agrega un mensaje de depuración para verificar los datos antes de agregar el paciente
                System.out.println("Paciente a agregar: " + np.toString());

                m.agregarPaciente(np);
                iniciar();
                limpiar();
            } catch (NumberFormatException ex) {
                System.out.println("Error al convertir el ID a entero: " + ex.getMessage());
            }
        }
        if(e.getSource() == ventana.btModificar){
            Paciente pa = new Paciente();
            pa.setId(Integer.parseInt(ventana.txtId.getText()));
            pa.setNombre(ventana.txtNombre.getText());
            pa.setFechaN(ventana.txtFecha.getText());
            pa.setEstadoC(ventana.txtEstadoC.getSelectedItem().toString());
            pa.setNivelEstud(ventana.txtNE.getSelectedItem().toString());
            pa.setOcupacion(ventana.txtOcupacion.getText());
            Modelo m = new Modelo();
            m.ActualizarPaciente(pa);
            iniciar();
            limpiar();
            ventana.txtId.setEnabled(true);
        }
        if(e.getSource()==ventana.btEliminar){
            Paciente a = new Paciente();
            a.setId(Integer.parseInt(ventana.txtId.getText()));
            modelo.PapeleriaPaciente(a);
            iniciar();
            limpiar();
            ventana.txtId.setEnabled(true);
        }
    }
    
    private void limpiar(){
        ventana.txtId.setText("");
        ventana.txtNombre.setText("");
        ventana.txtFecha.setText("");
        ventana.txtEstadoC.setSelectedIndex(0);
        ventana.txtNE.setSelectedIndex(0);
        ventana.txtOcupacion.setText("");  
    }
    
}
