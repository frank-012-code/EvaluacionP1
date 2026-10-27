package ec.edu.taller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** Completar manualmente la interfaz Swing e integrarla con GestorImpresiones. */
public class InterfazImpresiones {
    private JTabbedPane tabbedPane1;
    private JComboBox<String> cboSolicitante;
    private JTextField txtCodigo;
    private JTextField textField1;
    private JSpinner spiPrioridad;
    private JButton REGISTRARTRABAJOButton;
    private JButton LIMPIARELEMENTOSButton;
    private JTextArea textArea1;
    private JPanel panel;

    private GestorImpresiones gestor = new GestorImpresiones(10);
    public InterfazImpresiones() {

        spiPrioridad.setModel(new SpinnerListModel(new Object[]{1, 3, 5}));

        REGISTRARTRABAJOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigo.getText().trim();
                String solicitante = cboSolicitante.getSelectedItem().toString();
                String documento = textField1.getText().trim();
                int prioridad = Integer.parseInt(spiPrioridad.getValue().toString());
                // Validación de campos vacíos
                if (codigo.isEmpty() || documento.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }
                // Crear e intentar registrar el trabajo en el gestor
                TrabajoImpresion nuevoTrabajo = new TrabajoImpresion(codigo, solicitante, documento, prioridad);
                boolean registrado = gestor.registrarTrabajo(nuevoTrabajo);
                if (!registrado) {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el trabajo (código duplicado o capacidad llena).");
                    return;
                }
                // Recorrer los trabajos con un bucle for-each e imprimirlos en el área de texto
                StringBuilder sb = new StringBuilder();
                for (TrabajoImpresion aux : gestor.listarTrabajos()) {
                    sb.append(aux.toString()).append("\n");
                }
                textArea1.setText(sb.toString());
                JOptionPane.showMessageDialog(null, "Trabajo registrado con éxito.");
            }
        });
        // Listener para el botón Limpiar Elementos
        LIMPIARELEMENTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCodigo.setText("");
                textField1.setText("");
                if (cboSolicitante.getItemCount() > 0) {
                    cboSolicitante.setSelectedIndex(0);
                }
                spiPrioridad.setValue(1);
                textArea1.setText("");
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("InterfazImpresiones");
        frame.setContentPane(new InterfazImpresiones().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}