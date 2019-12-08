package logica.ecuaciones;

import java.text.DecimalFormat;

/**
El metodo de Newton Raphson necesita de la evaluacion de la 
derivada de la funcion de la ecuacion; si nos acercamos con una recta secante a 
la raiz de la ecuacion. Tenemos el metodo de la secante que necesita dos 
valores iniciales; necesariamente que contenga a la raiz

La aproximacion a la derivada f'(x) se hace por diferencia, es decir:

              f(X(i-1)) - f(Xi)
    f'(Xi) = -------------------
                X(i-1) - Xi

Si en la formula recursiva de Newton Raphson sustituimos la f'(x) por la 
diferencia
                  f(Xi)
    f(Xi) = Xi - --------
                  f'(Xi)

tenemos:
                         f(Xi)
    f(Xi) = Xi -  -------------------
                   f(X(i-1)) - f(Xi)
                   -----------------
                      X(i-1) - Xi

Ley de Oreja:
                   f(Xi) (X(i-1) - Xi)
    f(Xi) = Xi -  ---------------------
                    f(X(i-1)) - f(Xi)
*/
public class MetodoLaSecante {

    private double limiteInferior;
    private double limiteSuperior;
    private int errorPorcentual;
    private double errorEstimado;
    private int iteraciones;
    private boolean hayPorcentaje;
    private DecimalFormat formato;
    
    public MetodoLaSecante() {
        errorPorcentual = 100;
        errorEstimado = 5;
        limiteInferior = 0;
        limiteSuperior = 1;
        formato = new DecimalFormat("#.#####");
    }

    public void aplicarEcuacion(double Xi, double Xs) {
        double fXmenos1 = aplicarFuncion(0);
        double fXi = aplicarFuncion(1);
        
        double raiz = encontrarRaiz(0, 1);
    }
    
    public double aplicarFuncion(double numero) {
        double resultado = Math.pow(Math.E, -numero) - numero;
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double encontrarRaiz(double Xi, double Xs) {
//        double x = Xs; // 1
//        double fX = aplicarFuncion(x); // -0,63212
//        double xmenos1;
//        double fXmenos1;
//        
//        double resultado = x - (fX * (xmenos1 - x)) / (fXmenos1 - fX);
        
        return 0;
    }
    
    public void encontrarErrorPorcentual(double primeraRaiz, double segundaRaiz) {
        
    }
}