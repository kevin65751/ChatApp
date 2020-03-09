package chatapp.cliente.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class ClienteView extends javax.swing.JFrame {
    
    public ClienteView() {
        initComponents();
    }
    
    public void agregarChat(String mnsj) {
        jtaChat.append(mnsj+"\n");
    }
    
    public void limpiarMensaje() {
        jtfMensaje.setText(null);
    }
    
    public void limpiarChat() {
        jtaChat.setText(null);
    }
    
    public void limpiarPanelUsuariosEnLinea() {
        jpUsuariosEnLinea.removeAll();
    }
    
    public void agregarEnvioListener(ActionListener al) {
        jbEnviar.addActionListener(al);
        jtfMensaje.addActionListener(al);
    }
    
    public void setNroUsuario(int nro) {
        jlNroUsuario.setText("Nro. usuario: "+nro);
        setTitle("Cliente nro."+nro);
    }
    
    public void setNroContacto(int nro) {
        jlContacto.setText("Chateando con: "+nro);
    }
    
    public void agregarUsuarioLinea(UsuarioPanel up) {
        jpUsuariosEnLinea.add(up);
        jpUsuariosEnLinea.validate();
    }
    
    public boolean mostrarSolicitudChat(int nro) {
        return JOptionPane.showConfirmDialog(this, "El usuario nro. "+nro
                +" desea abrir un chat contigo.") == 0;
    }
    
    public void mostrarConfirmacion(int nro) {
        JOptionPane.showMessageDialog(this, "El usuario nro. "+nro
                +" ha aceptado chatear contigo. Se ha creado una sala privada.");
    }
    
    public void mostrarError(String err) {
        jlMensaje.setForeground(Color.RED);
        jlMensaje.setText(err);
    }
    
    public void mostrarConfirmacion(String confir) {
        jlMensaje.setForeground(Color.GREEN);
        jlMensaje.setText(confir);
    }
    
    public String getMensaje() {
        return jtfMensaje.getText();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaChat = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jtfMensaje = new javax.swing.JTextField();
        jbEnviar = new javax.swing.JButton();
        jlMensaje = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jlNroUsuario = new javax.swing.JLabel();
        jlContacto = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpUsuariosEnLinea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jtaChat.setEditable(false);
        jtaChat.setColumns(20);
        jtaChat.setLineWrap(true);
        jtaChat.setRows(5);
        jScrollPane1.setViewportView(jtaChat);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jtfMensaje, java.awt.BorderLayout.CENTER);

        jbEnviar.setText("Enviar");
        jPanel3.add(jbEnviar, java.awt.BorderLayout.EAST);
        jPanel3.add(jlMensaje, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jlNroUsuario.setText("Nro. Usuario: ");
        jPanel6.add(jlNroUsuario);

        jlContacto.setText("Chateando con ");
        jPanel6.add(jlContacto);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios en linea"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jpUsuariosEnLinea.setPreferredSize(new java.awt.Dimension(130, 275));
        jpUsuariosEnLinea.setLayout(new javax.swing.BoxLayout(jpUsuariosEnLinea, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(jpUsuariosEnLinea);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JLabel jlContacto;
    private javax.swing.JLabel jlMensaje;
    private javax.swing.JLabel jlNroUsuario;
    private javax.swing.JPanel jpUsuariosEnLinea;
    private javax.swing.JTextArea jtaChat;
    private javax.swing.JTextField jtfMensaje;
    // End of variables declaration//GEN-END:variables
}
