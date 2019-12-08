package grafica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import logica.ecuaciones.Ecuacion;

public class PanelCoordenadas extends JPanel {

    /**
     * Espacio que existe entre segmentos y que mide 1 cm. real
     */
    private final int margen = 40;
    /**
     * La mitad del tamaño de un laEcuacion de Interseccion
     */
    private final int mitadPunto = 4;
    /**
     * La Ecuacion a graficar
     */
    private Ecuacion laEcuacion;

    public PanelCoordenadas() {
    }
    
    public PanelCoordenadas(Ecuacion nuevaEcuacion) {
        laEcuacion = nuevaEcuacion;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 640);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        dibujarSistemaDeCoordenadas(g);
        
        if (laEcuacion != null)
            laEcuacion.graficarEcuacion(g, this);
    }

    /**
     * Por defecto, El origen se encuentra en (320, 320) Para dibujar el laEcuacion
 se utiliza un circulo de 7x7 Hay 8 Segmentos por Cuadrante. Cada semento 
     * tiene 40 pixeles de ancho/alto
     *
     * @param g
     */
    public void dibujarSistemaDeCoordenadas(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //Pie de pagina Winner
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Winner", Font.BOLD, 16));
        g2d.drawString("WINNER", getWidth() - 80, getHeight() - 20);

        //LINEAS HORIZONTALES
        g.setColor(Color.gray);
        //Del centro hacia arriba        
        for (int i = distanciaVerticalAlOrigen() - margen; i > 0; i -= margen) 
            g.drawLine(0, i, getAncho(), i);
        
        //Del centro hacia abajo
        for (int i = distanciaVerticalAlOrigen() + margen; i < getAlto(); i += margen) 
            g.drawLine(0, i, getAncho(), i);

        //LINEAS VERTICALES
        //Del centro a la izquierda
        for (int i = distanciaHorizontalAlOrigen() - margen; i >= margen; i -= margen) 
            g.drawLine(i, 0, i, getAlto());
        
        //Del centro a la derecha
        for (int i = distanciaHorizontalAlOrigen() + margen; i < getAncho(); i += margen) 
            g.drawLine(i, 0, i, getAlto());
        
        //Resaltamos la coordenada del origen
        g.setColor(Color.black);
        g.fillOval(distanciaHorizontalAlOrigen() - mitadPunto, distanciaVerticalAlOrigen() - mitadPunto, mitadPunto * 2, mitadPunto * 2);
        
        //SEGMENTOS DEL EJE X   
        g2d.setStroke(new BasicStroke(2.6f));
        //Del centro a la izquierda
        for (int i = distanciaHorizontalAlOrigen() - margen; i >= margen; i -= margen)          
            g2d.drawLine(i, distanciaVerticalAlOrigen() - 5, i, distanciaVerticalAlOrigen() + 5);
        
        //Del centro a la derecha
        for (int i = distanciaHorizontalAlOrigen() + margen; i < getAncho(); i += margen) 
            g2d.drawLine(i, distanciaVerticalAlOrigen() - 5, i, distanciaVerticalAlOrigen() + 5);

        //SEGMENTOS DEL EJE Y
        //Del centro hacia arriba
        for (int i = distanciaVerticalAlOrigen() - margen; i > 0; i -= margen) 
            g2d.drawLine(distanciaHorizontalAlOrigen() - 5, i, distanciaHorizontalAlOrigen() + 5, i);
        
        //Del centro hacia abajo
        for (int i = distanciaVerticalAlOrigen() + margen; i < getAlto(); i += margen) 
            g2d.drawLine(distanciaHorizontalAlOrigen() - 5, i, distanciaHorizontalAlOrigen() + 5, i);

        //Numeros del eje X
        for (int i = 1; i < cantidadSegmentosHorizontales(); i++) {
            g.drawString(i + "", distanciaHorizontalAlOrigen() + (i * margen), distanciaVerticalAlOrigen() + (margen / 2));
            g.drawString("-" + i, distanciaHorizontalAlOrigen() - (i * margen), distanciaVerticalAlOrigen() + (margen / 2));
        }

        //Numeros del eje Y
        for (int i = 1; i < cantidadSegmentosVerticales() + 1; i++) {
            g.drawString(i + "", distanciaHorizontalAlOrigen() + 15, distanciaVerticalAlOrigen() - (i * margen));
            g.drawString("-" + i, distanciaHorizontalAlOrigen() + 15, distanciaVerticalAlOrigen() + (i * margen));
        }

        //X y Y
        g.drawString("X", getAncho() - (margen / 2), distanciaVerticalAlOrigen() - (margen / 2));
        g.drawString("Y", distanciaHorizontalAlOrigen() + (margen / 2), (margen / 2));
              
        //FLECHAS: ARRBIBA, DERECHA, ABAJO, IZQUIERDA
        dibujarFlecha(getAncho() / 2, 0, g);
        dibujarFlecha(getAncho(), getAlto() / 2, g);        
        dibujarFlecha(getAncho() / 2, getAlto(), g);        
        dibujarFlecha(0, getAlto() / 2, g);
    }
        
    /**
     * Dibuja una flecha desde el Origen hasta el laEcuacion especificado
     * @param x La coordenada X 
     * @param y La coordeada Y
     * @param g 
     */
    public void dibujarFlecha(int x, int y, Graphics g) {
        double ang, angSep;
        double tx, ty;
        int dist;
        Point punto1, punto2;
        
        // Defino dos puntos extremos
        punto1 = new Point(distanciaHorizontalAlOrigen(), distanciaVerticalAlOrigen());
        punto2 = new Point(x, y);
        
        // Tamaño de la punta de la flecha
        dist = 20;
        
        // las coordenadas de la ventana es al revez
        // calculo de la variacion de "x" y "y" para hallar el angulo
        ty = -(punto1.y - punto2.y) * 1.0;
        tx = (punto1.x - punto2.x) * 1.0;
        
        // angulo
        ang = Math.atan(ty / tx);
        
        // si tx es negativo, aumentar 180 grados
        if (tx < 0)            
            ang += Math.PI;
        
        // puntos de control para la punta
        // p1 y p2 son los puntos de salida
        Point p1 = new Point(), p2 = new Point(), Punto = punto2;
        
        // angulo de separacion
        angSep = 25.0;
        
        p1.x = (int)(Punto.x + dist * Math.cos (ang - Math.toRadians(angSep)));
        p1.y = (int)(Punto.y - dist * Math.sin (ang - Math.toRadians(angSep)));
        p2.x = (int)(Punto.x + dist * Math.cos (ang + Math.toRadians(angSep)));
        p2.y = (int)(Punto.y - dist * Math.sin (ang + Math.toRadians(angSep)));
        
        Graphics2D g2D = (Graphics2D) g;
        
        // grosor de la linea
        g2D.setStroke(new BasicStroke(1.2f));
        // dibuja la linea de extremo a extremo
        g.drawLine(punto1.x, punto1.y, Punto.x, Punto.y);
        // dibujar la punta
        g.drawLine(p1.x, p1.y, Punto.x, Punto.y);
        g.drawLine(p2.x, p2.y, Punto.x, Punto.y);
    }
    
    /**
     * La cantidad de pixeles desde un extremo hasta el origen, por default 320
     * @return 
     */
    public int distanciaHorizontalAlOrigen() {
        return getAncho() / 2;
    }
    
    /**
     * La cantidad de pixeles desde un extremo hasta el origen, por default 320
     * @return 
     */
    public int distanciaVerticalAlOrigen() {
        return getAlto() / 2;
    }
    
    /**
     * Cantidad de segmentos que existen en un cuadrante, que corresponde al
     * eje de las abcisas (La abcisa es la distancia horizontal)
     * @return 
     */
    public int cantidadSegmentosHorizontales() {
        return (getAncho() / 2) / margen;
    }
    
    /**
     * Cantidad de segmentos que existen en un cuadrante, que corresponde al
     * eje de las ordenadas (La ordenada es la distancia vertical)
     * @return 
     */
    public int cantidadSegmentosVerticales() {
        return (getAlto() / 2) / margen;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    /**
     * Devuelve el tamaño horizontal de la pantalla
     * @return 
     */
    public int getAncho() {
       return this.getWidth();
    }
    
    /**
     * Devuelve el tamaño vertical de la pantalla
     * @return 
     */
    public int getAlto() {
        return this.getHeight();        
    }
    
    /**
     * Devuelve la cantidad de pixeles que existe entre un segmento a otro
     * Por defecto es 40
     * @return 
     */
    public int getMargen() {
        return margen;
    }

    /**
     * Devuelve la mitad del tamaño de un laEcuacion de interseccion
 Por defecto es 4
     * @return 
     */
    public int getMitadPunto() {
        return mitadPunto;
    }

    public Ecuacion getLaEcuacion() {
        return laEcuacion;
    }

    public void setLaEcuacion(Ecuacion laEcuacion) {
        this.laEcuacion = laEcuacion;
    }
    // </editor-fold>
}
