package chatapp.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kevin
 */
public class UsuarioPanel extends javax.swing.JPanel {
    
    public UsuarioPanel(int nro, chatapp.cliente.model.Cliente c) {
        initComponents();
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                c.enviarMensaje("#"+nro);
            }
        });
        jLabel1.setText("User nro."+nro);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("");
        add(jLabel1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Iniciar chat");
        add(jButton1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
