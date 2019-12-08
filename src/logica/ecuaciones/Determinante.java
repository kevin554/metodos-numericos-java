package logica.ecuaciones;

public class Determinante {

    private double[][] matriz;

    public Determinante(int dimensiones) {
        this.matriz = new double[dimensiones][dimensiones];
    }

    public void rellenar(double... n) {
        int indice = 0;
        
        for (double[] matriz1 : matriz) {
            for (int k = 0; k < matriz.length; k++) {
                matriz1[k] = n[indice++];
            }
        }
    }

    public double resolver2x2() {
        double n = 1;

        for (int i = 0; i < matriz.length; i++) {
            double numero = matriz[i][i];
            n = n * numero;
        }

        double n2 = 1;
        for (int i = 1, j = 0; i >= 0 || j <= 1; i--, j++) {
            double numero = matriz[i][j];
            n2 = n2 * numero;
        }

        return n - n2;
    }

    public double resolver3x3() {
        double d1 = 1;
        
        for (int i = 0; i < matriz.length; i++) {
            double numero = matriz[i][i];
            d1 *= numero;
        }
        
        double d2 = 1;
        for (int i = 0, j = 1; i < matriz.length; i++, j++) {
            double numero = matriz[i][j];
            d2 *= numero;
            
            if (j == matriz.length-1) {
                j = -1;
            }
        }
        
        double d3 = 1;
        for (int i = 0, j = 2; i < matriz.length; i++, j++) {
            double numero = matriz[i][j];
            d3 *= numero;
            
            if (j == matriz.length-1) {
                j = -1;
            }
        }
        
        double positivo = d1 + d2 + d3;
        d1 = 1;
        d2 = 1;
        d3 = 1;
        
        for (int i = 0, j = 2; i < matriz.length; i++, j--) {
            double numero = matriz[i][j];
            d1 *= numero;
        }
        
        for (int i = 0, j = 1; i < matriz.length; i++, j--) {
            double numero = matriz[i][j];
            d2 *= numero;
            
            if (j == 0) {
                j = 3;
            }
        }
        
        for (int i = 0, j = 0; i < matriz.length; i++, j--) {
            double numero = matriz[i][j];
            d3 *= numero;
            
            if (j == 0) {
                j = 3;
            }
        }
        
        double negativo = d1 + d2 + d3;
        
        return positivo - negativo;
    }
    
    public boolean esMatriz2x2() {
        return this.matriz.length == 2;
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters, setters y toString">
    public double[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(double[][] matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        String string = "";
        
        for (double[] matriz1 : matriz) {
            for (int j = 0; j < matriz.length; j++) {
                string = string.concat(Double.toString(matriz1[j]));
                string = string.concat(" ");
            }
            string = string.concat("\n");
        }
        
        return string;
    }
    // </editor-fold>
    
}
