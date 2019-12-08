package logica.ecuaciones;

/**
Este metodo necesita como valores los limites de un intervalo cerrado.

I = [Xi, Xs]     Xi <= Xr <= Xs

Este metodo consiste en reemplazar la grafica de la funcion por una recta, 
el punto de corte de esta recta con el eje x es la raiz arpoximada de la 
ecuacion(El punto de corte es una falsa posicion de la raiz, de aquí el 
nombre del metodo).

El algoritmo es analogo al metodo de biseccion; con la diferencia: 
"Reemplazar la formula de aproximacion"

     Xr = (Xi + Xs) / 2   por   Xr = Xs - (f(Xs) * (Xi - Xs)) / f(Xi) - f(Xs)

Ejemplo: Resolver la ecuacion (e^-x) = x con el metodo de la falsa posicion 
entre 0 y 1; con un error estimado de 0,3 y con 3 decimales significativos

1. Xi = 0   Xs = 1   Es = 0,03

2. f(0) * f(1)
   ((e^-0) - 0) * ((e^-1) - 1)
   1 * -0,632 < 0 

3. Xr = 1 - (-0,632 * (0 - 1)) / 1 - (-0,632) = 1 - (0,632 / 1,632) = 0,613

4. f(Xi) * f(Xr)
   1 * (e^-(-0,613)) - (-0,613)
   1 * -0,071 < 0   Xs <-   Xr      Xs = 0,613

5. Xr = 0,613 - (((-0,72) * (0 - 0,613)) / 1 - (-0,071)) = 0,572

6. Ea = | (0,572 - 0,612) / 0,572 | = 0,07 > 0,03


4. f(Xi) * f(Xr)
   1 * (e^-(-0,572)) - (-0,572)
   1 * -0,008 < 0   Xs <-   Xr      Xs = 0,572

5. Xr = 0,572 - (((-0,008) * (0 - 0,572)) / 1 - (-0,008)) = 0,567

6. Ea = | (0,567 - 0,572) / 0,567 | = 0,01 < 0,03
   
La solucion a la ecuacion es X = 0,567 con un error aproximado de 0,03
 */
public class MetodoFalsaPosicion extends MetodoBiseccion {

    public MetodoFalsaPosicion() {
        errorEstimado = 0.3;
        hayPorcentaje = true;
    }

    @Override
    public double encontrarRaiz(double Xi, double Xs) {
        double fXi = aplicarFuncion(Xi);
        double fXs = aplicarFuncion(Xs);
        
        Double resultado = Xs - ((fXs * (Xi - Xs)) / (fXi - fXs));
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
//    @Override
//    public DecimalFormat getFormato() {
//        return formato;
//    }
//
//    @Override
//    public void setFormato(DecimalFormat formato) {
//        this.formato = formato;
//    }
//
//    @Override
//    public double getErrorEstimado() {
//        return errorEstimado;
//    }
//
//    @Override
//    public void setErrorEstimado(double errorEstimado) {
//        this.errorEstimado = errorEstimado;
//    }
//
//    @Override
//    public boolean isHayPorcentaje() {
//        return hayPorcentaje;
//    }
//
//    @Override
//    public void setHayPorcentaje(boolean hayPorcentaje) {
//        this.hayPorcentaje = hayPorcentaje;
//    }
    // </editor-fold>
}