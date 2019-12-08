package logica;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class PageViewer extends CardLayout {

    @Override
    public Dimension preferredLayoutSize(Container contenedor) {
        // Primero ubicamos el panel que se muestra actualmente
        Component actual = ubicarPanelActual(contenedor);
        
        if (actual != null) {
            Insets recuadro = contenedor.getInsets();
            Dimension pref = actual.getPreferredSize();
            
            pref.width += recuadro.left + recuadro.right;
            pref.height += recuadro.top + recuadro.bottom;
            
            return pref;
        }
        
        return super.preferredLayoutSize(contenedor); 
    }

    /**
     * Recorre el panel contenedor en busca del panel que se visualiza actualmente
     * @param contenedor El panel contenedor
     * @return El panel que se muestra actualmente
     */
    public Component ubicarPanelActual(Container contenedor) {
        // Recorremos el panel contenedor en busca del panel que se muestra actualmente
        for (Component comp : contenedor.getComponents()) {
            if (comp.isVisible()) 
                return comp;            
        }
        
        return null;
    }
}