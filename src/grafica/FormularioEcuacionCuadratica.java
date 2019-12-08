package grafica;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logica.ecuaciones.Ecuacion;
import logica.ecuaciones.Parabola;

/*
 */
public class FormularioEcuacionCuadratica extends JPanel {

    // Para capturar la ecuacion cuadratica
    // ([+-]?\d+[.,]?\d*)x\^2\s*([+-]\d+[.,]?\d*)x\s*([+-]\d+[.,]?\d*)\s*
    // e^x - x
    // ([-+]?\d*.\d*e?)\^([+-]?\d*.?\d*x)\s*([-+]?\d*.?\d*x?)\s*
    // Para capturar intervalos I?\\[?(-?\\d*)\\s*,?\\s*(-?\\d*)\\]?\\s?
    // I?[[(]?([+-]?\\d+)\\s*,?\\s*([+-]?\\d+)[)]]?\\s?
    private String expresion = "I?\\[?(-?\\d*\\.?\\d*)\\s*,?\\s*(-?\\d*\\.?\\d*)\\]?\\s?";
    private PanelEcuacionCuadratica elPanelEcuacion;
    private JTextArea taLimites;
    private JButton btnDespejar;
    private JButton btnVerGrafica;
    private JPanel panelPrincipal;
    private Ventana laVentana;
    private Ecuacion laEcuacion;

    public FormularioEcuacionCuadratica(Ventana laVentana) {
        this.laVentana = laVentana;

        init();
    }

    private void init() {
        laEcuacion = new Parabola();
        taLimites = new JTextArea(8, 30);
        btnDespejar = new JButton("Despejar");
        btnVerGrafica = new JButton("Ver Grafica");
        panelPrincipal = new JPanel(new GridBagLayout());

        taLimites.setBorder(BorderFactory.createEtchedBorder());
        taLimites.setLineWrap(true);
        taLimites.setWrapStyleWord(true);
        taLimites.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        elPanelEcuacion = new PanelEcuacionCuadratica();
        
        JScrollPane scroll = new JScrollPane(taLimites);

        agregarComponente(panelPrincipal, elPanelEcuacion, 0, 0, 6, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE);

        agregarComponente(panelPrincipal, scroll, 0, 1, 6, 3,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        agregarComponente(panelPrincipal, btnDespejar, 0, 5, 2, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE);

        agregarComponente(panelPrincipal, btnVerGrafica, 3, 5, 2, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE);

        btnDespejar.addActionListener(this::despejar);
        btnVerGrafica.addActionListener(this::verGrafica);

        this.add(panelPrincipal);
    }

    private void despejar(ActionEvent evt) {
        obtenerDatos();

        double limiteInferior, limiteSuperior;
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile(expresion);
        Matcher matcher = pattern.matcher(taLimites.getText().trim());

        while (matcher.find()) {
            limiteInferior = Double.parseDouble(matcher.group(1));
            limiteSuperior = Double.parseDouble(matcher.group(2));

            sb.append(((Parabola) 
                    laEcuacion).aplicarEcuacion(limiteInferior, limiteSuperior))
                    .append("\n");

            // Para evitar que duplique la ultima expresion encontrada
            if (matcher.hitEnd()) {
                break;
            }
        }

        JOptionPane.showMessageDialog(this, sb);
    }

    private void verGrafica(ActionEvent evt) {
        obtenerDatos();

        laVentana.getVentanaCoordenadas().getElPanel().setLaEcuacion(laEcuacion);
        laVentana.getVentanaCoordenadas().pack();
        laVentana.getVentanaCoordenadas().setLocationRelativeTo(null);
        laVentana.getVentanaCoordenadas().setVisible(true);
    }

    public void obtenerDatos() {
        String A = elPanelEcuacion.getTfA().getText();
        String B = elPanelEcuacion.getTfB().getText();
        String C = elPanelEcuacion.getTfC().getText();

        A = A.replaceAll(" ", "");
        B = B.replaceAll(" ", "");
        C = C.replaceAll(" ", "");

        switch (A) {
            case "":
            case "x":
            case "+":
                ((Parabola) laEcuacion).setA(1);
                break;

            case "-":
                ((Parabola) laEcuacion).setA(-1);
                break;

            default:
                ((Parabola) laEcuacion).setA(Integer.parseInt(A));
                break;
        }

        switch (B) {
            case "":
            case "x":
            case "x+":
            case "+":
                ((Parabola) laEcuacion).setB(1);
                break;

            case "-":
                ((Parabola) laEcuacion).setB(-1);
                break;

            default:
                ((Parabola) laEcuacion).setB(Integer.parseInt(B));
                break;
        }

        if (C.equals("")) {
            ((Parabola) laEcuacion).setC(0);
        } else {
            ((Parabola) laEcuacion).setC(Integer.parseInt(C));
        }
    }

    public void agregarComponente(JPanel elPanel, JComponent componente,
            int posX, int posY, int ancho, int alto, int ubicacion, int rellenar) {
        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = posX;
        gridConstraints.gridy = posY;
        gridConstraints.gridwidth = ancho;
        gridConstraints.gridheight = alto;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.anchor = ubicacion;
        gridConstraints.fill = rellenar;

        elPanel.add(componente, gridConstraints);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 285);
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
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

    public JButton getBtnVerGrafica() {
        return btnVerGrafica;
    }

    public void setBtnVerGrafica(JButton btnVerGrafica) {
        this.btnVerGrafica = btnVerGrafica;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public PanelEcuacionCuadratica getElPanelEcuacion() {
        return elPanelEcuacion;
    }

    public void setElPanelEcuacion(PanelEcuacionCuadratica elPanelEcuacion) {
        this.elPanelEcuacion = elPanelEcuacion;
    }

    public Ecuacion getLaEcuacion() {
        return laEcuacion;
    }

    public void setLaEcuacion(Ecuacion laEcuacion) {
        this.laEcuacion = laEcuacion;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public Ventana getLaVentana() {
        return laVentana;
    }

    public void setLaVentana(Ventana laVentana) {
        this.laVentana = laVentana;
    }
    // </editor-fold>

    public class PanelEcuacionCuadratica extends JPanel implements KeyListener {

        protected JLabel lbAdvertencia;
        private JLabel lbY;
        private JLabel lbX2;
        private JLabel lbX;
        private JTextField tfA;
        private JTextField tfB;
        private JTextField tfC;

        public PanelEcuacionCuadratica() {
            init();
        }

        private void init() {
            this.setLayout(null);

            lbAdvertencia = new JLabel();
            lbY = new JLabel("y=");
            lbX2 = new JLabel("²");
            lbX = new JLabel("x");
            tfA = new JTextField(2);
            tfB = new JTextField(2);
            tfC = new JTextField(2);

            tfA.setText("x");
            tfB.setText("+");
            tfA.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (tfA.getText().trim().equalsIgnoreCase("x")) {
                        tfA.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfA.getText().trim().equals("")) {
                        tfA.setText("x");
                    }
                }
            });

            tfB.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (tfB.getText().trim().equalsIgnoreCase("+")) {
                        tfB.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfB.getText().trim().equals("")) {
                        tfB.setText("+");
                    }
                }
            });

            this.add(lbAdvertencia);
            this.add(lbY);
            this.add(tfA);
            this.add(lbX2);
            this.add(tfB);
            this.add(lbX);
            this.add(tfC);

            lbY.setBounds(0, 0, 20, 25);
            tfA.setBounds(20, 0, 20, 25);
            lbX2.setBounds(40, 0, 20, 25);
            tfB.setBounds(55, 0, 25, 25);
            lbX.setBounds(80, 0, 20, 25);
            tfC.setBounds(90, 0, 20, 25);
            lbAdvertencia.setBounds(120, 0, 140, 30);

            tfA.addKeyListener(this);
            tfB.addKeyListener(this);
            tfC.addKeyListener(this);
        }

        /**
         * Elimina una letra (solo se deben escribir numeros)
         *
         * @param e
         */
        private void eliminarLetra(KeyEvent e) {
            char c = e.getKeyChar();

            if (Character.isLetter(c)) {
                getToolkit().beep();
                e.consume();

                new Thread(this::mostrarAdvertencia).start();
            }
        }

        /**
         * Le muestra un mensaje al usuario indicandole que no puede ingresar
         * letras en los campos de texto
         */
        private void mostrarAdvertencia() {
            lbAdvertencia.setText("Sólo num en la ecuacion");

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                /* TODO-CODE here */ }

            lbAdvertencia.setText("");
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(260, 25);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() == tfA || e.getSource() == tfB
                    || e.getSource() == tfC) {
                eliminarLetra(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
        public JLabel getLbY() {
            return lbY;
        }

        public void setLbY(JLabel lbY) {
            this.lbY = lbY;
        }

        public JLabel getLbX2() {
            return lbX2;
        }

        public void setLbX2(JLabel lbX2) {
            this.lbX2 = lbX2;
        }

        public JLabel getLbX() {
            return lbX;
        }

        public void setLbX(JLabel lbX) {
            this.lbX = lbX;
        }

        public JTextField getTfA() {
            return tfA;
        }

        public void setTfA(JTextField tfA) {
            this.tfA = tfA;
        }

        public JTextField getTfB() {
            return tfB;
        }

        public void setTfB(JTextField tfB) {
            this.tfB = tfB;
        }

        public JTextField getTfC() {
            return tfC;
        }

        public void setTfC(JTextField tfC) {
            this.tfC = tfC;
        }

        public JLabel getLbAdvertencia() {
            return lbAdvertencia;
        }

        public void setLbAdvertencia(JLabel lbAdvertencia) {
            this.lbAdvertencia = lbAdvertencia;
        }
        // </editor-fold>
    }
}
