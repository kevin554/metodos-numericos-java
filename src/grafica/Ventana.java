package grafica;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logica.ecuaciones.FactoryEcuacion;
import logica.PageViewer;

public class Ventana extends JFrame {
    
    private FormularioEcuacionCuadratica formularioEcuacionCuadratica;
    private FormularioMetodoBiseccion panelMetodoBiseccion;
    private FormularioFalsaPosicion panelMetodoFalsaPosicion;
    private PanelPortada panelPortada;
    
    private CardLayout layout;
    private JPanel panelPrincipal;
    private VentanaCoordenadas ventanaCoordenadas;
        
    public Ventana() {
        init();
    }

    private void init() {
        layout = new PageViewer();
        panelPrincipal = new JPanel(layout);
        
        //CONFIGURAMOS LA VENTANA
        panelPortada = new PanelPortada();
        panelPrincipal.add(panelPortada, "Portada");
        formularioEcuacionCuadratica = new FormularioEcuacionCuadratica(this);
        panelPrincipal.add(formularioEcuacionCuadratica, "Metodo Grafico");
        panelMetodoBiseccion = new FormularioMetodoBiseccion(this);
        panelPrincipal.add(panelMetodoBiseccion, "Metodo de la Biseccion");
        panelMetodoFalsaPosicion = new FormularioFalsaPosicion(this);
        panelPrincipal.add(panelMetodoFalsaPosicion, "Metodo Falsa Posicion");
        
        this.add(panelPrincipal, BorderLayout.CENTER);
        
        JMenuBar barra = new JMenuBar();
        JMenu mnNuevo = new JMenu("Nuevo");
        JMenu mnOpciones = new JMenu("Opciones");
        JMenu mnMetodosCerrados = new JMenu("Metodos Cerrados");
        JMenu mnMetodosAbiertos = new JMenu("Metodos Abiertos");
        JMenuItem itmMetodoGrafico = new JMenuItem("Metodo Grafico");
        JMenuItem itmMetodoBiseccion = new JMenuItem("Metodo Biseccion");
        JMenuItem itmMetodoFalsaPosicion = new JMenuItem("Metodo Falsa Posicion");
        JMenuItem itmMetodoPrimerOrder = new JMenuItem("Metodo Primer Orden");
        JMenuItem itmMetodoSegundoOrder = new JMenuItem("Metodo Segundo Orden");
        JMenuItem itmMetodoNewtonVonMises = new JMenuItem("Metodo Newton Von Mises");
        JMenuItem itmMetodoLaSecante = new JMenuItem("Metodo La-Secante");
        JMenuItem itmSalir = new JMenuItem("Salir");
        JMenuItem itmFraseCelebre = new JMenuItem("Frase Celebre");
        JMenuItem itmAcercaDe = new JMenuItem("Acerca De");

        //AÑADIMOS LA BARRA DE MENÚ
        setJMenuBar(barra);
        barra.add(mnNuevo);
        barra.add(mnOpciones);
        mnNuevo.add(mnMetodosCerrados);
        mnMetodosCerrados.add(itmMetodoGrafico);
        mnMetodosCerrados.add(itmMetodoBiseccion);
        mnMetodosCerrados.add(itmMetodoFalsaPosicion);
        mnNuevo.add(mnMetodosAbiertos);
        mnMetodosAbiertos.add(itmMetodoPrimerOrder);
        mnMetodosAbiertos.add(itmMetodoSegundoOrder);
        mnMetodosAbiertos.add(itmMetodoNewtonVonMises);
        mnMetodosAbiertos.add(itmMetodoLaSecante);
        mnNuevo.addSeparator();
        mnNuevo.add(itmSalir);
        mnOpciones.add(itmFraseCelebre);
        mnOpciones.add(itmAcercaDe);
        

        //AÑADIMOS ACTIONLISTENER Y KEYLISTENER A LOS COMPONENTES QUE LO REQUIERAN
        itmMetodoGrafico.addActionListener(this::mostrarPanel);
        itmMetodoBiseccion.addActionListener(this::mostrarPanel);
        itmMetodoFalsaPosicion.addActionListener(this::mostrarPanel);
        itmSalir.addActionListener(this::salir);
        itmFraseCelebre.addActionListener(this::mostrarFraseCelebre);
        itmAcercaDe.addActionListener(this::mostrarInformacionPrograma);
        
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        ventanaCoordenadas = new VentanaCoordenadas();
    }

    /**
     * Muestra en pantalla el formulario correspondiente para el menu escogido
     * @param e 
     */
    public void mostrarPanel(ActionEvent e) {
        String tipo = e.getActionCommand();
        String tipoEcuacion = FactoryEcuacion.obtenerFormulario(tipo);
        layout.show(panelPrincipal, tipoEcuacion);
        formularioEcuacionCuadratica.getTaLimites().grabFocus();
        
        this.pack();
    }

    /**
     * Despliega una ventana con una frase celebre
     * @param e
     */
    public void mostrarFraseCelebre(ActionEvent e) {
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/Frase.jpg"));
        JOptionPane.showMessageDialog(this, imagen, "Frase", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Despliega una ventana con la informacion acerca del programa
     * @param e
     */
    public void mostrarInformacionPrograma(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new PanelAcercaDe(), "App",
                JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Sale del programa
     * @param e 
     */
    public void salir(ActionEvent e) {
        System.exit(0);
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public FormularioEcuacionCuadratica getFormularioEcuacionCuadratica() {
        return formularioEcuacionCuadratica;
    }

    public void setFormularioEcuacionCuadratica(FormularioEcuacionCuadratica formularioEcuacionCuadratica) {
        this.formularioEcuacionCuadratica = formularioEcuacionCuadratica;
    }

    public PanelPortada getPanelPortada() {
        return panelPortada;
    }

    public void setPanelPortada(PanelPortada panelPortada) {
        this.panelPortada = panelPortada;
    }

    public void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    
    public VentanaCoordenadas getVentanaCoordenadas() {
        return ventanaCoordenadas;
    }

    public void setVentanaCoordenadas(VentanaCoordenadas ventanaCoordenadas) {
        this.ventanaCoordenadas = ventanaCoordenadas;
    }
    // </editor-fold>
}
