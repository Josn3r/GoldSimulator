package store.j3studios.project.goldsimulator;

import java.text.DecimalFormat;
import java.util.Random;

public class Frame extends javax.swing.JFrame {

    public Frame() {
        initComponents();
    }
    
    public Double precioOro = 33450.0;
    
    public Integer unidadesTotalesOro = 10000;
    public Integer unidadesCirculando = 0;
    public Integer totalProduction = 0;
    
    public Integer seconds = 0;
    
    public Boolean started = false;
    public Runnable hiloSimulacion;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        secondsText = new javax.swing.JLabel();
        boton = new javax.swing.JButton();
        totalGolds = new javax.swing.JLabel();
        compraOro = new javax.swing.JLabel();
        ventaOro = new javax.swing.JLabel();
        unitsOro = new javax.swing.JLabel();
        lastEvent = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        circulatingOro = new javax.swing.JLabel();
        totalProduced = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gold's Simulator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gold's Simulator - Alpha Tests");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 6, 560, 40));

        secondsText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondsText.setText("Gold's Simulations - 0 seconds");
        jPanel1.add(secondsText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 20));

        boton.setBackground(new java.awt.Color(0, 102, 0));
        boton.setForeground(new java.awt.Color(255, 255, 255));
        boton.setText("Start Simulation");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });
        jPanel1.add(boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 160, 30));

        totalGolds.setText("Total Gold: 10000");
        jPanel1.add(totalGolds, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        compraOro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        compraOro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        compraOro.setText("Gold's Buy Price: $33.450");
        jPanel1.add(compraOro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 540, 40));

        ventaOro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ventaOro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventaOro.setText("Gold's Sell Price: $30.000");
        jPanel1.add(ventaOro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 540, 40));

        unitsOro.setText("Gold's Bank Units: 10000");
        jPanel1.add(unitsOro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        lastEvent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastEvent.setText("...");
        jPanel1.add(lastEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 540, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Last Event");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 540, -1));

        circulatingOro.setText("Gold's Circulating: 0");
        jPanel1.add(circulatingOro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        totalProduced.setText("Total Gold Produced: 0");
        jPanel1.add(totalProduced, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        if (!started) {
            started = true;
            boton.setBackground(new java.awt.Color(102, 0, 0));
            boton.setText("Stop Simulation");
            iniciarSimulacion();
        } else {
            started = false;
            boton.setBackground(new java.awt.Color(0, 102, 0));
            boton.setText("Start Simulation");
            new Thread(hiloSimulacion).interrupt();
        }
    }//GEN-LAST:event_botonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Frame().setVisible(true);
        });
    }
    
    private void iniciarSimulacion() {
        comprarOro(100);
        actualizarInterfazGrafica();
        
        hiloSimulacion = () -> {
            while (started) {
                try {
                    Thread.sleep(1000); // Espera 5 segundos                    
                    // Genera evento aleatorio
                    int tipoEvento = (int) (Math.random() * 3); // 0: Compra, 1: Venta, 2: Producción
                    int cantidadOroCompra = (int) (Math.random() * 100) + 1; // Cantidad aleatoria entre 1 y 100                    
                    int cantidadOroVenta = (int) (Math.random() * 50) + 1; // Cantidad aleatoria entre 1 y 10                    
                    int cantidadOroProduction = (int) (Math.random() * 5) + 1; // Cantidad aleatoria entre 1 y 5                    
                    switch (tipoEvento) {
                        case 0 -> {
                            if (unidadesTotalesOro > 0) {
                                if (cantidadOroCompra > unidadesTotalesOro) {
                                    cantidadOroCompra = unidadesTotalesOro;
                                }
                                comprarOro(cantidadOroCompra);
                                lastEvent.setText("Compra de Oro al Banco - Cantidad: " + cantidadOroCompra);
                            }                            
                        }
                        case 1 -> {
                            if (unidadesCirculando > 0) {
                                if (cantidadOroVenta > unidadesCirculando) {
                                    cantidadOroVenta = unidadesCirculando;
                                }
                                venderOro(cantidadOroVenta);
                                lastEvent.setText("Venta de Oro al Banco - Cantidad: " + cantidadOroVenta);
                            }                            
                        }
                        case 2 -> {
                            producirOro(cantidadOroProduction);
                            lastEvent.setText("Producción de Oro (Entra en circulación) - Cantidad: " + cantidadOroProduction);
                        }
                    }
                    actualizarInterfazGrafica();
                    seconds += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(hiloSimulacion).start();
    }
    
    private void comprarOro(int unidades) {
        unidadesTotalesOro -= unidades;
        unidadesCirculando += unidades;
        for (int i = 0; i<unidades; ++i) {
            precioOro += new Random().nextDouble(2.25, 4.0);
        }
    }
    
    private void venderOro(int unidades) {
        unidadesCirculando -= unidades;
        unidadesTotalesOro += unidades;
        for (int i = 0; i<unidades; ++i) {
            precioOro -= new Random().nextDouble(1.50, 3.0);
        }
    }

    private void producirOro(int unidades) {
        unidadesCirculando += unidades;
        totalProduction += unidades;
        for (int i = 0; i<unidades; ++i) {
            precioOro -= new Random().nextDouble(2.75, 5.5);
        }
    }
        
    private void actualizarInterfazGrafica() {
        compraOro.setText("Gold's Buy Price: $" + new DecimalFormat("###,###.##").format(precioOro));
        ventaOro.setText("Gold's Buy Price: $" + new DecimalFormat("###,###.##").format(precioOro-500.0));
        unitsOro.setText("Gold's Bank Units: " + unidadesTotalesOro);
        circulatingOro.setText("Gold's Circulating: " + unidadesCirculando);
        totalProduced.setText("Total Gold Produced: " + totalProduction);
        totalGolds.setText("Total Golds: " + (unidadesTotalesOro+unidadesCirculando));
        
        secondsText.setText("Gold's Simulations - "+seconds+" seconds");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton;
    private javax.swing.JLabel circulatingOro;
    private javax.swing.JLabel compraOro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lastEvent;
    private javax.swing.JLabel secondsText;
    private javax.swing.JLabel totalGolds;
    private javax.swing.JLabel totalProduced;
    private javax.swing.JLabel unitsOro;
    private javax.swing.JLabel ventaOro;
    // End of variables declaration//GEN-END:variables
}
