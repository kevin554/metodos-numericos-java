package grafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import logica.ecuaciones.Ecuacion;

public class VentanaCoordenadas extends JFrame {

    private PanelCoordenadas elPanel;
    private Ecuacion laEcuacion;

    public VentanaCoordenadas() {
        init();
    }
    
    public VentanaCoordenadas(Ecuacion elPunto) {
        laEcuacion = elPunto;
        
        init();
    }
    
    private void init() {
        elPanel = new PanelCoordenadas(laEcuacion);
        elPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2) 
                    maximizar();
            }
        });
        
        //CONFIGURAMOS LA VENTANA
        this.add(elPanel);
    }
    
    public void maximizar() {
        this.dispose();
        
        if (elPanel.getAncho() == (int) elPanel.getPreferredSize().width) {
            this.setExtendedState(MAXIMIZED_BOTH);
            this.setUndecorated(true);
            this.setVisible(true);
        } else {
            this.setSize(elPanel.getPreferredSize());
            this.setUndecorated(false);
            this.setVisible(true);
            this.pack();
            this.setLocationRelativeTo(null);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public PanelCoordenadas getElPanel() {
        return elPanel;
    }

    public void setElPanel(PanelCoordenadas elPanel) {
        this.elPanel = elPanel;
    }

    public Ecuacion getLaEcuacion() {
        return laEcuacion;
    }

    public void setLaEcuacion(Ecuacion laEcuacion) {
        this.laEcuacion = laEcuacion;
    }
    // </editor-fold>
}
