package logica.ecuaciones;

import java.text.DecimalFormat;

/**
Este metodo, para aproximarse a la raiz de una ecuacion necesita un valor 
inicial proximo a la raiz Xi

La formula recursiva es:
    Xi+1 = Xi - f(Xi)
                f'(Xi)

Xi = Valor inicial
f'(Xi) = Derivada de f(Xi)
 */
public class MetodoNewtonRaphsonPrimerOrden {

    private int iteraciones;
    private double valorInicial;
    private double resultadoAnterior;
    private DecimalFormat formato;

    public MetodoNewtonRaphsonPrimerOrden() {
        formato = new DecimalFormat();
        
//        valorInicial = 0.577; // ejemplo 1
//        valorInicial = 1.165; // ejemplo y tarea 2
        valorInicial = 0.2; // tarea 1
    }

    public String aplicarEcuacion() {
        while (true) {
            valorInicial = aplicarFuncion3(valorInicial);
            System.out.println(valorInicial);
        
            if (valorInicial == resultadoAnterior) {
                break;
            }
            
            iteraciones++;
            
            resultadoAnterior = valorInicial;
        }
        
        return "La raiz de la ecuacion es " + valorInicial + 
                "\nTomó " + iteraciones + " iteraciones";
    }
    
    public double aplicarFuncion(double numero) {
        Double resultado = numero - (Math.pow(numero, 2) - 0.5) / (2 * numero);
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double aplicarFuncion2(double numero) { // ejemplo y tarea 2
        Double resultado = numero - (Math.sin(numero) / Math.cos(numero));
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double aplicarFuncion3(double numero) { // tarea 1
        double resultado = numero - 
                (((2 * Math.pow(numero, 3)) - (9 * Math.pow(numero, 2)) + (12 * numero) - 3) / 
                (6 * Math.pow(numero, 2) - (18 * numero) + 12));
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
}
