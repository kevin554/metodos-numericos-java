package grafica;

import javax.swing.JOptionPane;
import logica.ecuaciones.Determinante;

public class PanelMatriz extends javax.swing.JPanel {

    public PanelMatriz() {
        initComponents();
        init();
    }

    private void init() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMatriz = new javax.swing.JPanel();
        tf0x0 = new javax.swing.JTextField();
        tf0x1 = new javax.swing.JTextField();
        tf0x2 = new javax.swing.JTextField();
        tf1x0 = new javax.swing.JTextField();
        tf1x1 = new javax.swing.JTextField();
        tf1x2 = new javax.swing.JTextField();
        tf2x0 = new javax.swing.JTextField();
        tf2x1 = new javax.swing.JTextField();
        tf2x2 = new javax.swing.JTextField();
        btnresolver = new javax.swing.JButton();

        panelMatriz.setLayout(new java.awt.GridLayout(3, 3));

        tf0x0.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf0x0.setText("0");
        panelMatriz.add(tf0x0);

        tf0x1.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf0x1.setText("0");
        panelMatriz.add(tf0x1);

        tf0x2.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf0x2.setText("0");
        panelMatriz.add(tf0x2);

        tf1x0.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf1x0.setText("0");
        panelMatriz.add(tf1x0);

        tf1x1.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf1x1.setText("0");
        panelMatriz.add(tf1x1);

        tf1x2.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf1x2.setText("0");
        panelMatriz.add(tf1x2);

        tf2x0.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf2x0.setText("0");
        panelMatriz.add(tf2x0);

        tf2x1.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf2x1.setText("0");
        panelMatriz.add(tf2x1);

        tf2x2.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        tf2x2.setText("0");
        panelMatriz.add(tf2x2);

        btnresolver.setText("resolver");
        btnresolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolverMatriz(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 302, Short.MAX_VALUE)
                        .addComponent(btnresolver)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(btnresolver)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resolverMatriz(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolverMatriz
        double f0c0;
        double f0c1;
        double f0c2;
        double f1c0;
        double f1c1;
        double f1c2;
        double f2c0;
        double f2c1;
        double f2c2;

        try {
            f0c0 = Double.parseDouble(tf0x0.getText().trim());
            f0c1 = Double.parseDouble(tf0x1.getText().trim());
            f0c2 = Double.parseDouble(tf0x2.getText().trim());
            f1c0 = Double.parseDouble(tf1x0.getText().trim());
            f1c1 = Double.parseDouble(tf1x1.getText().trim());
            f1c2 = Double.parseDouble(tf1x2.getText().trim());
            f2c0 = Double.parseDouble(tf2x0.getText().trim());
            f2c1 = Double.parseDouble(tf2x1.getText().trim());
            f2c2 = Double.parseDouble(tf2x2.getText().trim());
        } catch (NumberFormatException ex) {
            String mensaje = "los campos solo pueden contener numeros";
            JOptionPane.showMessageDialog(this, mensaje);
            
            return;
        }

        Determinante matriz;
        
        if (f0c2 == 0 || f1c2 == 0 || f2c0 == 0 || f2c1 == 0 || f2c2 == 0) {
            matriz = new Determinante(2);
            matriz.rellenar(f0c0, f0c1, f1c0, f1c1);
        } else {
            matriz = new Determinante(3);
            matriz.rellenar(f0c0, f0c1, f0c2, f1c0, f1c1, f1c2, f2c0, f2c1, f2c2);
        }
        
        if (matriz.esMatriz2x2()) {
            JOptionPane.showMessageDialog(this, matriz.resolver2x2());
        } else {
            JOptionPane.showMessageDialog(this, matriz.resolver3x3());
        }
    }//GEN-LAST:event_resolverMatriz

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnresolver;
    private javax.swing.JPanel panelMatriz;
    private javax.swing.JTextField tf0x0;
    private javax.swing.JTextField tf0x1;
    private javax.swing.JTextField tf0x2;
    private javax.swing.JTextField tf1x0;
    private javax.swing.JTextField tf1x1;
    private javax.swing.JTextField tf1x2;
    private javax.swing.JTextField tf2x0;
    private javax.swing.JTextField tf2x1;
    private javax.swing.JTextField tf2x2;
    // End of variables declaration//GEN-END:variables

}
