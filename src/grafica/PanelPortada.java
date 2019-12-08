package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPortada extends JPanel {

    private JLabel lbNumeros = new JLabel();
    private ImageIcon imgNumeros;
    
    public PanelPortada() {
        init();
    }
    
    private void init() {
        this.setLayout(new BorderLayout());
        
        imgNumeros = new ImageIcon(getClass()
                .getResource("/imagenes/Numeros.jpg"));
        lbNumeros = new JLabel(imgNumeros);
        
        this.add(lbNumeros, "Center");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imgNumeros.getIconWidth(), 
                imgNumeros.getIconHeight());
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public JLabel getLbNumeros() {
        return lbNumeros;
    }

    public void setLbNumeros(JLabel lbNumeros) {
        this.lbNumeros = lbNumeros;
    }

    public ImageIcon getImgNumeros() {
        return imgNumeros;
    }

    public void setImgNumeros(ImageIcon imgNumeros) {
        this.imgNumeros = imgNumeros;
    }
    // </editor-fold>
}
