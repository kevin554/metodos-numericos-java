package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelAcercaDe extends JPanel {

    private JLabel lbTitulo;
    private JLabel lbNombre;
    private JLabel lbCopyRight;
    private JPanel panelCentral;
    private Border borde;

    /**
     * El codigo para mostrarlo: JOptionPane.showMessageDialog(this, 
     * new PanelAcercaDe(), "Acerca De", JOptionPane.PLAIN_MESSAGE);
     *
     * @author Nico
     */
    public PanelAcercaDe() {
        init();
    }
    
    private void init() {
        lbTitulo = new JLabel("Metodos Numericos");
        lbNombre = new JLabel("Nicolas Duran");
        lbCopyRight = new JLabel("CopyRight 2017");
        panelCentral = new JPanel(new GridLayout(3, 1));
        borde = BorderFactory.createEtchedBorder();
        
        panelCentral.add(lbTitulo);
        panelCentral.add(lbNombre);
        panelCentral.add(lbCopyRight);
        
        this.setBorder(borde);
        this.add(panelCentral, BorderLayout.CENTER);
    }
}
