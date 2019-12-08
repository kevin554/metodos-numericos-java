package logica.ecuaciones;

import grafica.PanelCoordenadas;
import java.awt.Graphics;

public abstract class Ecuacion {
    
    /**
     * El panel en el cual se graficará la ecuacion
     */
    protected PanelCoordenadas elPanel;
    /**
     * El punto interseccion x
     */
    protected double x;
    /**
     * El punto de interseccion y
     */
    protected double y;
    
    /**
     * @param g
     * @param elPanel El panel en el cual se graficará la ecuacion
     */
    public abstract void graficarEcuacion(Graphics g, PanelCoordenadas elPanel);

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public PanelCoordenadas getElPanel() {
        return elPanel;
    }

    public void setElPanel(PanelCoordenadas elPanel) {
        this.elPanel = elPanel;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    // </editor-fold>
}