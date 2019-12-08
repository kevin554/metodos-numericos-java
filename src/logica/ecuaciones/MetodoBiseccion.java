package logica.ecuaciones;

import java.text.DecimalFormat;

/**
Este metodo toma como valores iniciales dos valores que son 
los limites de un intervalor que son los limites de un intervalo cerrado [X, Xs]

La raiz se obtiene por aproximacion de la biseccion de los limites del 
intervalo, es decir Xr = (Xi + Xs) / 2

1. Dar los valores iniciales Xi, Xs
2. Verificar si Xi, Xs contiene la raiz
     Si f(Xi) * f(Xs) < 0 ir a 3
     caso contrario ir a 1
3. Primera raiz Xr = (Xi + Xs) / 2
4.   f(Xi) * f(Xr) < 0     Hacer Xs = Xr   ir a 5
     f(Xi) * f(Xr) > 0     Hacer Xi = Xr   ir a 5
     f(Xi) * f(Xr) = 0     La raiz es Xr -> FIN
5. Xr = (Xi + Xs) / 2
6. Criterio de Paro      Ea = | (Xr act - Xr ant) / Xr ant | * 100
7. Si Ea < Es la raiz es Xr con Es -> FIN
8. Xr ant = Xr act   Ir a 4

Ejemplo: Resolver la ecuacion (e^-x) - x = 0, entre 0 y 1 con un error estimado
del 5% y 3 decimales significativos

1.  Xi = 0
    Xs = 1
    Es = 5%

2. f(Xi) * f(Xs)
   f(0) * f(1)
   ((e^-0) - 0) * ((e^-1) - 1)
   1 * -0,632 < 0

   Hay cambio de signo, se aceptan los valores iniciales

3. Xr = (Xi + Xs) / 2   ->  Xr = (0 + 1) / 2 = 0,5

4. f(Xi) * f(Xi)
   f(0) * f(0,5)
   1 * 0,107 > 0    Xi <- Xr   Xi = 0,5

5. Xr = (Xi + Xs) / 2   ->  Xr = (0,5 + 1) / 2 = 0,75

6. Ea = | (0,75 - 0,5) / 0,75 | * 100% = 33,333% > 5%


4. f(Xi) * f(Xs)
   f(0,5) * f(0,75)
   0,107 * -0,278 < 0   Xs <- Xr    Xs = 0,75

5. Xr = (Xi + Xs) / 2   ->  Xr = (0,5 + 0,75) / 2 = 0,625

6. Ea = | (0,625 - 0,75) / 0,625 | * 100% = 20% > 5%


4. f(Xi) * f(Xs)
   f(0,5) * f(0,625)
   0,107 * -0,090 < 0   Xs <- Xr    Xs = 0,625

5. Xr = (Xi + Xs) / 2   ->  Xr = (0,5 + 0,625) / 2 = 0,563

6. Ea = | (0,563 - 0,625) / 0,563 | * 100% = 11% > 5%
   

4. f(Xi) * f(Xs)
   f(0,5) * f(0,563)
   0,107 * 0,006 > 0   Xi <- Xr    Xi = 0,563

5. Xr = (Xi + Xs) / 2   ->  Xr = (0,563 + 0,625) / 2 = 0,594

6. Ea = | (0,594 - 0,563) / 0,594 | * 100% = 5,21% > 5%


4. f(Xi) * f(Xs)
   f(0,563) * f(0,594)
   0,006 * -0,042 < 0   Xs <- Xr    Xs = 0,594

5. Xr = (Xi + Xs) / 2   ->  Xr = (0,563 + 0,594) / 2 = 0,579

6. Ea = | (0,579 - 0,594) / 0,579 | * 100% = 2,54% > 5%

La raiz de la ecuacino es 0,579 con un error aproximado del 5%
 */
public class MetodoBiseccion {

    protected DecimalFormat formato;
    protected double primeraRaiz;
    protected double segundaRaiz;
    protected double errorEstimado;
    protected double errorPorcentual;
    protected int iteraciones;
    protected boolean hayPorcentaje;

    public MetodoBiseccion() {
        formato = new DecimalFormat();
        errorEstimado = 0.3;
        errorPorcentual = 100;
        iteraciones = 0;
        hayPorcentaje = true;
    }

    public String aplicarEcuacion(double Xi, double Xs) {
        // PASO 2
        double funcionXi = aplicarFuncion(Xi);
        double funcionXs = aplicarFuncion(Xs);
        
        System.out.println("fXi " + funcionXi);
        System.out.println("fXs " + funcionXs);

        if (funcionXi * funcionXs > 0) 
            return "El intervalo no contiene la raiz, ingrese otros valores";
        
        // PASO 3
        primeraRaiz = encontrarRaiz(Xi, Xs);
        System.out.println("Xr " + primeraRaiz);

        // PASO 4
        while (errorPorcentual > errorEstimado) {
            iteraciones++;
            
            double funcionXr = aplicarFuncion(primeraRaiz);
            System.out.println("fXr " + funcionXr);

            if (funcionXr * funcionXi == 0) 
                return "La raiz es Xr " + primeraRaiz;

            if (funcionXr * funcionXi > 0) 
                Xi = primeraRaiz;
             else if (funcionXr * funcionXi < 0) 
                Xs = primeraRaiz;

            System.out.println("Xi " + Xi);
            System.out.println("Xs " + Xs);
            
            // PASO 5
            segundaRaiz = encontrarRaiz(Xi, Xs);
            System.out.println("Xr " + segundaRaiz);

            // PASO 6
            errorPorcentual = encontrarErrorPorcentual(primeraRaiz, segundaRaiz);
            errorPorcentual = Double.parseDouble(new DecimalFormat("#.0")
                    .format(errorPorcentual).replace(",", "."));
            System.out.println("Ea " + errorPorcentual);
            
            primeraRaiz = segundaRaiz;
            
            if (errorPorcentual < errorEstimado) {
                break;
            }
        }
        
        return "La raiz de la ecuacion es " + primeraRaiz + 
                "\nCon un error aproximado de " + errorPorcentual + 
                "\nSe realizaron " + iteraciones + " iteraciones";
    }

    protected double aplicarFuncion(double numero) {
        Double resultado = Math.pow(Math.E, -numero) - numero;
//        Double resultado = Math.pow(numero, 2) + numero - 0.5;
//        Double resultado = (0.1 * Math.pow(numero, 2)) - (numero * Math.log10(numero));
//        Double resultado = Math.log(Math.pow(numero, 2)) + 0.7;

//        Double resultado = Math.log10(Math.pow(numero, 2) - 2) + 0.9;
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    protected double encontrarRaiz(double primerNumero, double segundoNumero) {
        Double resultado = (primerNumero + segundoNumero) / 2;
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    protected double encontrarErrorPorcentual(double primeraRaiz, double segundaRaiz) {
        Double resultado;
        
        if (hayPorcentaje) {
            resultado = (Math.abs((segundaRaiz - primeraRaiz) / segundaRaiz)) 
                    * 100;
        } else {
            resultado = Math.abs((segundaRaiz - primeraRaiz) / segundaRaiz);
        }
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public double getPrimeraRaiz() {
        return primeraRaiz;
    }

    public void setPrimeraRaiz(double primeraRaiz) {
        this.primeraRaiz = primeraRaiz;
    }

    public double getSegundaRaiz() {
        return segundaRaiz;
    }

    public void setSegundaRaiz(double segundaRaiz) {
        this.segundaRaiz = segundaRaiz;
    }

    public DecimalFormat getFormato() {
        return formato;
    }

    public void setFormato(DecimalFormat formato) {
        this.formato = formato;
    }

    public double getErrorEstimado() {
        return errorEstimado;
    }

    public void setErrorEstimado(double errorEstimado) {
        this.errorEstimado = errorEstimado;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(int iteraciones) {
        this.iteraciones = iteraciones;
    }

    public double getErrorPorcentual() {
        return errorPorcentual;
    }

    public void setErrorPorcentual(double errorPorcentual) {
        this.errorPorcentual = errorPorcentual;
    }

    public boolean isHayPorcentaje() {
        return hayPorcentaje;
    }

    public void setHayPorcentaje(boolean hayPorcentaje) {
        this.hayPorcentaje = hayPorcentaje;
    }
    // </editor-fold>
}
