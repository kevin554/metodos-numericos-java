package logica.ecuaciones;

import java.text.DecimalFormat;

/*

*/
public class MetodoNewtonRaphsonSegundoOrden {

    private int iteraciones;
    private DecimalFormat formato;
    
    public MetodoNewtonRaphsonSegundoOrden() {
        formato = new DecimalFormat();
    }

    public void aplicarEcuacion(double numero) { // 1,165
        double delta = (-(1 / Math.tan(numero))) - ((0.5) * Math.tan(numero));
        System.out.println(delta);
        delta = 1 / delta;
//        delta = Double.parseDouble(formato.format(delta).replace(",", "."));
        System.out.println(delta);
        
        double resultado = numero + delta;
        System.out.println("X1 " + resultado);
        
        System.out.println();
        System.out.println("i = 1");
        delta = -(1 / Math.tan(resultado)) - (0.5) * Math.tan(resultado);
        System.out.println(delta);
        delta = 1 / delta;
//        delta = Double.parseDouble(formato.format(delta).replace(",", "."));
        System.out.println(delta);
        
        resultado = resultado + delta;
        System.out.println("X2 " + resultado);
        
        System.out.println();
        System.out.println("i = 2");
        delta = -(1 / Math.tan(resultado)) - (0.5) * Math.tan(resultado);
        System.out.println(delta);
        delta = 1 / delta;
//        delta = Double.parseDouble(formato.format(delta).replace(",", "."));
        System.out.println(delta);
        
        resultado = resultado + delta;
        System.out.println("X3 " + formato.format(resultado));
        
        
//        double delta = -(aplicarFuncionDerivada(numero) / 
//                aplicarFuncion(numero)) + ((1 / 2) * 
//                (aplicarDobleFuncionDerivada(numero) / 
//                aplicarFuncionDerivada(numero)));
//        
//        delta = 1 / Double.parseDouble(formato.format(delta).replace(",", "."));
//        System.out.println(formato.format(delta));
//        
//        double resultado = numero + delta;
//        System.out.println(resultado);
    }
    
    public double resolver(double numero) {
        double resultado = 0;
        
        return Double.parseDouble(formato.format(resultado).replace(",", ".")); 
    }
    
    public double aplicarFuncionDerivada(double numero) {
        double resultado = Math.cos(numero);
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double aplicarDobleFuncionDerivada(double numero) {
        double resultado = -Math.sin(numero);
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double aplicarFuncion(double numero) {
        double resultado = Math.sin(numero);
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double encontrarRaiz(double numero) {
        double resultado = 0;
        
        return Double.parseDouble(formato.format(resultado).replace(",", "."));
    }
    
    public double encontrarErrorPorcentual(double primeraRaiz, double segundaRaiz) {
        double resultado = 0;
        
        return resultado;
    }
}