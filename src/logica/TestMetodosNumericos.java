package logica;

import grafica.FormularioFalsaPosicion;
import grafica.Ventana;
import logica.ecuaciones.MetodoFalsaPosicion;

public class TestMetodosNumericos {

    // Implementar clase para las distintas expresiones regulares (estaticas)
    // Separar clases abstractas ecuacion y metodos numericos
    // Colocar documentacion a los metodos
    public static void main(String[] args) {
        Ventana v = new Ventana();
        MetodoFalsaPosicion m = new MetodoFalsaPosicion();
        System.out.println(m.aplicarEcuacion(0, 1));
        
//        new logica.ecuaciones.MetodoNewtonRaphsonSegundoOrden().aplicarEcuacion(1.165);
//        System.out.println(new logica.ecuaciones.MetodoNewtonRaphsonPrimerOrden().aplicarEcuacion());
//        System.out.println(new logica.ecuaciones.MetodoBiseccion().aplicarEcuacion(9, 11));
//        java.awt.EventQueue.invokeLater(grafica.Ventana::new);
//        new logica.ecuaciones.MetodoFalsaPosicion().aplicarEcuacion(1.45, 2);
        
//        logica.ecuaciones.Determinante m = new logica.ecuaciones.Determinante(2);
//        m.rellenar(2, 0, -1, 3);
//        m.rellenar(5, -2, 4, 1);
//        System.out.println(m);
//        System.out.println(m.resolver2x2());

//        logica.ecuaciones.Determinante m = new logica.ecuaciones.Determinante(3);
//        m.rellenar(3, 2, -3, 7, -1, 0, 2, -4, 5);
//        m.rellenar(2, -3, 1, -3, 5, 7, 1, 7, -1);
//        System.out.println(m.resolver3x3());

    }
    
}