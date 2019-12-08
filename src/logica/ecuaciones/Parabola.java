package logica.ecuaciones;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.text.DecimalFormat;

/**
Una ecuacion de 2do grado que graficamente se representa con una parabola

Este metodo se utiliza como una herramienta para buscar el valor inicial de la
raiz de una ecuacion.

La raiz de una ecuacion se puede determinar en forma grafica.

Se construye la grafica de la ecuacion y la raiz de la euacion o cero de la 
funcion es el punto de interseccion del eje X con la curva de la ecuacion.

Se conoce como 'cero de una funcion' al cero de una funcion y = f(x); es el 
valor 'x' donde 'y' se hace cero.

¿Como saber si un intervalo cerrado contiene la raiz de la ecuacion?

Un intervalo I = [Xi, Xs] contiene la raiz o cero de la funcion si hay cambio
de signo en los limites del intervalo

Xi = Limite inferior
Xs = Limite superior

Si f(Xi) * f(Xs) = 0    La raiz es Xi ó Xs
   f(Xi) * f(Xs) > 0    El intervalo no contiene la raiz
   f(Xi) * f(Xs) < 0    El intervalo si contiene la raiz

Ejemplo: Verificar qué intervalos contienen la raiz de la ecuacion 
x² + 2x - 3 = 0

a) I = [2, 4]
b) I = [-6, -4]
c) I = [0, 3]
d) I = [-5, -2]
e) I = [-1, 1]

    f(Xi) * f(Xs)
    f(2) * f(4)
    f(2² + (2 * 2) - 3) * f(4² + (2 * 4) - 3)
    5 * 21 > 0
    (+) 
    I = [2, 4] no contiene la raiz

    f(Xi) * f(Xs)
    f(-6) * f(-4)
    f(-6² + (2 * -6) - 3) * f(-4² + (2 * -4) - 3)
    21 * 5 > 0
    (+) 
    I = [-6, -4] no contiene la raiz

    f(Xi) * f(Xs)
    f(0) * f(3)
    f(0² + (2 * 0) - 3) * f(3² + (2 * 3) - 3)
    -3 * 12 < 0
    (-) 
    I = [0, 3] si contiene la raiz

    f(Xi) * f(Xs)
    f(-5) * f(-2)
    f(-5² + (2 * -5) - 3) * f(-2² + (2 * -2) - 3)
    12 * -3 < 0
    (-) 
    I = [-5, -2] si contiene la raiz

    f(Xi) * f(Xs)
    f(-1) * f(1)
    f(-1² + (2 * -1) - 3) * f(1² + (2 * 1) - 3)
    -4 * 0 = 0
    El intervalo I = [-1, 1] contiene la raiz en Xi (-4)
 */
public class Parabola extends Ecuacion {

    //Datos ingresados en los campos de texto de la Ecuacion Cuadratica (Parabola)
    private double A;
    private double B;
    private double C;
    private DecimalFormat formato;
        
    public Parabola() {
        formato = new DecimalFormat();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public double getA() {
        return A;
    }

    public void setA(int a) {
        this.A = a;
    }

    public DecimalFormat getFormato() {
        return formato;
    }

    public void setFormato(DecimalFormat formato) {
        this.formato = formato;
    }

    public double getB() {
        return B;
    }

    public void setB(int b) {
        this.B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(int c) {
        this.C = c;
    }
    
    private void setX() {
        x = (-B) / (2 * A);        
    }

    private void setY() {
        y = ((4 * (A * A) * C) - (A * B * B)) / (4 * A * A);
    }

    @Override
    public double getX() {
        setX();
        
        return x;
    }

    @Override
    public double getY() {
        setY();
        
        return y;
    }
    // </editor-fold>
    
    /**
     * x^2 + 2x + c
     * @param numero
     * @return 
     */
    public double aplicarEcuacion(double numero) {
        return Math.pow(numero, 2) + (2 * numero) + C;
//        return Math.log10(Math.pow(numero, 2) + 2) + numero - 5;
    }
    
    public String aplicarEcuacion(double limiteInferior, double limiteSuperior) {
        double inferior = aplicarEcuacion(limiteInferior);
        double superior = aplicarEcuacion(limiteSuperior);
        
        System.out.println("fXi " + inferior);
        System.out.println("fXs " + superior);

        double resultado = inferior * superior;
        
        if (resultado == 0) {
            return "El intervalo [" + formato.format(limiteInferior) + ", " + 
                    formato.format(limiteSuperior) + "] contiene la raiz en " + 
                    (inferior != 0 ? "Li: " + inferior : "Ls: " + superior);
        }
        
        if (resultado > 0) {
            return "El intervalo [" + formato.format(limiteInferior) + ", " + 
                    formato.format(limiteSuperior) + "] no contiene la raiz";
        } else {
            return "El intervalo [" + formato.format(limiteInferior) + ", " + 
                    formato.format(limiteSuperior) + "] si contiene la raiz";
        }
    }

    /**
     * Grafica el punto de Interseccion y la Parabola
     * @param elPanel
     */
    @Override
    public void graficarEcuacion(Graphics g, grafica.PanelCoordenadas elPanel) {
        // Punto de Interseccion
        g.fillOval((int) (elPanel.distanciaHorizontalAlOrigen() + (getX() * elPanel.getMargen()) - elPanel.getMitadPunto()),
                (int) (elPanel.distanciaVerticalAlOrigen() - (getY() * elPanel.getMargen()) - elPanel.getMitadPunto()), 7, 7);
        
        // Eje de la parabola
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(2.6f));
        for (int i = 0; i < elPanel.getAlto(); i += 35) {
            g2d.drawLine((int) this.getX() * elPanel.getMargen() + elPanel.distanciaHorizontalAlOrigen(), i, 
                    (int) this.getX() * elPanel.getMargen() + elPanel.distanciaHorizontalAlOrigen(), i + 10);
        }
        
        // Parabola
        Polygon p = new Polygon();
        double scala = 0.025;

        if (getA() < 0) {    //Si el primer dato es negativo
            for (int i = -300; i < 300; i++) {
                p.addPoint((int) (i + elPanel.distanciaHorizontalAlOrigen() + getX() * elPanel.getMargen()),
                        (int) (elPanel.distanciaVerticalAlOrigen() - (getY() * elPanel.getMargen()) + (int) (scala * i * i)));
            }
            
            g.drawPolygon(p);
        } else {
            for (int i = -300; i < 300; i++) {
                p.addPoint((int) (i + elPanel.distanciaHorizontalAlOrigen() + getX() * elPanel.getMargen()),
                        (int) (elPanel.distanciaVerticalAlOrigen() - (getY() * elPanel.getMargen()) - (int) (scala * i * i)));
            }
            
            g.drawPolygon(p);
        }
    }
}