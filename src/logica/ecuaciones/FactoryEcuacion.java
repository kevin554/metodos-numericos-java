package logica.ecuaciones;

public class FactoryEcuacion {

    /**
     * Devuelve el formulario correspondiente al menu seleccionado
     * @param menu El comando del menu que invoca al metodo
     * @return El nombre del panel correspondiente
     */
    public static String obtenerFormulario(String menu) {
        switch (menu) {
            case "Metodo Grafico": // Ecuacion cuadratica/Parabola
                return "Metodo Grafico";
            
            case "Metodo Biseccion":
                return "Metodo de la Biseccion";
                    
            case "Metodo Falsa Posicion":
                return "Metodo Falsa Posicion";
                
            case "Metodo Primer Orden":
                return "Metodo Primer Orden";
                
            case "Metodo Segundo Orden":
                return "Metodo Segundo Orden";
                
            case "Metodo Newton Von Mises":
                return "Metodo Newton Von Mises";
                
            case "Metodo La-Secante":
                return "Metodo de la Secante";
            
            default:
                return null;
        }
    }
}