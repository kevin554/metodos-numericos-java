package grafica;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logica.ecuaciones.MetodoBiseccion;

public class FormularioMetodoBiseccion extends JPanel {

    private Ventana laVentana;
    private JTextArea taLimites;
    private JButton btnDespejar;
    private String expression;
    private JPanel panelPrincipal;
    private MetodoBiseccion laEcuacion;
    private JTextField tfEcuacion;
    private JTextField tfError;
    private JLabel lbEcuacion;
    private JLabel lbError;
    private final String expresion = "I?\\[?(-?\\d*\\.?\\d*)\\s*,?\\s*(-?\\d*\\.?\\d*)\\]?\\s?";
    
    public FormularioMetodoBiseccion(Ventana laVentana) {
        this.laVentana = laVentana;
        
        init();
    }
    
    private void init() {
        laEcuacion = new MetodoBiseccion();
        
        taLimites = new JTextArea(8, 30);
        btnDespejar = new JButton("Despejar");
        tfEcuacion = new JTextField(10);
        tfEcuacion.setText("e^x - x");
        tfError = new JTextField(5);
        tfError.setText("5%");
        lbEcuacion = new JLabel("Ecuacion:");
        lbError = new JLabel("Error Estimado:");
        panelPrincipal = new JPanel(new GridBagLayout());
        
        taLimites.setBorder(BorderFactory.createEtchedBorder());
        taLimites.setLineWrap(true);
        taLimites.setWrapStyleWord(true);
        taLimites.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        
        agregarComponente(panelPrincipal, lbEcuacion, 0, 0, 1, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE);
        agregarComponente(panelPrincipal, tfEcuacion, 1, 0, 2, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE);
        agregarComponente(panelPrincipal, lbError, 3, 0, 1, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE);
        agregarComponente(panelPrincipal, tfError, 4, 0, 2, 1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE);
        agregarComponente(panelPrincipal, taLimites, 0, 1, 6, 3, 
                GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        agregarComponente(panelPrincipal, btnDespejar, 1, 5, 2, 1, 
                GridBagConstraints.WEST, GridBagConstraints.NONE);
        
        btnDespejar.addActionListener(this::despejar);
        
        this.add(panelPrincipal);
    }
    
    private void despejar(ActionEvent evt) {
        obtenerDatos();
        
        if (taLimites.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar intervalos");
            return;
        }
        
        double limiteInferior, limiteSuperior;
        StringBuilder sb = new StringBuilder();
        
        Pattern pattern = Pattern.compile(expresion);
        Matcher matcher = pattern.matcher(taLimites.getText().trim());

        while (matcher.find()) {
            limiteInferior = Double.parseDouble(matcher.group(1));
            limiteSuperior = Double.parseDouble(matcher.group(2));

            sb.append(((MetodoBiseccion) laEcuacion).aplicarEcuacion(limiteInferior, limiteSuperior));

            // Para evitar que duplique la ultima expresion encontrada
            if (matcher.hitEnd()) {
                break;
            }
        }
        
        JOptionPane.showMessageDialog(this, sb);
    }
    
    private void obtenerDatos() {
        if (!tfError.getText().trim().equals("")) { // Si hay texto
            if (tfError.getText().trim().contains("%")) {
                ((MetodoBiseccion) laEcuacion).setHayPorcentaje(true);
            } else {
                ((MetodoBiseccion) laEcuacion).setHayPorcentaje(false);
            }
        }
        
        ((MetodoBiseccion) laEcuacion).setIteraciones(0);
        ((MetodoBiseccion) laEcuacion).setErrorEstimado(Double.parseDouble(tfError.getText().replace("%", "")));
        ((MetodoBiseccion) laEcuacion).setErrorPorcentual(100);
        // Validar el error estimado, y si este lleva porcentaje
    }
    
    private void agregarComponente(JPanel elPanel, JComponent componente, 
            int posX, int posY, int ancho, int alto, int ubicacion, int rellenar) {
        GridBagConstraints restriccion = new GridBagConstraints();
        
        restriccion.gridx = posX;
        restriccion.gridy = posY;
        restriccion.gridwidth = ancho;
        restriccion.gridheight = alto;
        restriccion.weightx = 100;
        restriccion.weighty = 100;
        restriccion.insets = new Insets(5, 5, 5, 5);
        restriccion.anchor = ubicacion;
        restriccion.fill = rellenar;
        
        elPanel.add(componente, restriccion);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 285);
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Ventana getLaVentana() {
        return laVentana;
    }

    public void setLaVentana(Ventana laVentana) {
        this.laVentana = laVentana;
    }

    public JTextArea getTaLimites() {
        return taLimites;
    }

    public void setTaLimites(JTextArea taLimites) {
        this.taLimites = taLimites;
    }

    public JButton getBtnDespejar() {
        return btnDespejar;
    }

    public void setBtnDespejar(JButton btnDespejar) {
        this.btnDespejar = btnDespejar;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public MetodoBiseccion getLaEcuacion() {
        return laEcuacion;
    }

    public void setLaEcuacion(MetodoBiseccion laEcuacion) {
        this.laEcuacion = laEcuacion;
    }

    public JTextField getTfEcuacion() {
        return tfEcuacion;
    }

    public void setTfEcuacion(JTextField tfEcuacion) {
        this.tfEcuacion = tfEcuacion;
    }

    public JTextField getTfError() {
        return tfError;
    }

    public void setTfError(JTextField tfError) {
        this.tfError = tfError;
    }

    public JLabel getLbEcuacion() {
        return lbEcuacion;
    }

    public void setLbEcuacion(JLabel lbEcuacion) {
        this.lbEcuacion = lbEcuacion;
    }

    public JLabel getLbError() {
        return lbError;
    }

    public void setLbError(JLabel lbError) {
        this.lbError = lbError;
    }
    // </editor-fold>
}