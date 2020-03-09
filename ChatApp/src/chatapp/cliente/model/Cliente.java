package chatapp.cliente.model;

import chatapp.ConexionInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Kevin
 * 
 * Clase que representa un cliente y tiene cierta lógica como la conexion
 * y un método que permite enviar mensajes al servidor.
 * 
 */
public class Cliente {
    private int nro;
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    
    private boolean conectado = false;
    private boolean enChat;
    private int nroContact;
    
    public Cliente() {
        conectar();
        System.out.println("Cliente conectado!");
    }
    
    /**
     * Crea un socket cliente con los parámetros especificados en la clase
     * ConexionInfo. Obtiene los flujos de entrada y salida. Recibe el número
     * de cliente del servidor y coloca un flag de conectado. Si surge un error
     * en la conexion, intenta conectarse nuevamente.
     */
    private void conectar() {
        try {
            s = new Socket(ConexionInfo.HOST, ConexionInfo.PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            nro = dis.readInt();
            conectado = true;
        } catch (IOException ioe) {
            System.err.println("No se pudo establecer conexión "
                    + "con el servidor. "+ioe.getMessage());
            System.out.println("Intentando conectarse nuevamente...");
            conectar();
        }
    }

    
    /**
     * Método que permite enviar un mensaje a el servidor.
     * @param msj: mensaje a enviar.
     * @return verdadero si si envió el mensaje de manera correcta. False, si no.
     */
    public boolean enviarMensaje(String msj) {
        try {
            dos.writeUTF(msj);
            System.out.println("mensaje enviado: "+msj);
            return true;
        } catch (IOException ioe) {
            System.err.println("No se pudo enviar el mensaje. "
                    +ioe.getMessage());
        }
        return false;
    }
    
    // <editor-fold defaultstate="colapsed" desc="Getters and Setters">
    public int getNroCliente() {
        return nro;
    }
    
    public boolean isEnChat() {
        return enChat;
    }
    
    public int getNroContact() {
        return nroContact;
    }
    
    public boolean isConectado() {
        return conectado;
    }

    public DataInputStream getDis() {
        return dis;
    }
    // </editor-fold>
}
