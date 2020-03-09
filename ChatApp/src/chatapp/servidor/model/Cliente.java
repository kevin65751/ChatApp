package chatapp.servidor.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import java.net.Socket;

/**
 *
 * @author Kevin
 * 
 * Clase que representa un cliente del servidor.
 * 
 */
public class Cliente extends Thread {
    private final int nro;
    private final Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    
    private final Comunidad refComunidad;
    private Chat chat;
    private boolean conected;
    
    /**
     * Se crea un cliente y se configura
     * @param nro: identificador del cliente.
     * @param socket: socket asociado al cliente.
     * @param c: referencia a la comunidad que pertenece el cliente.
     * 
     */
    public Cliente(int nro, Socket socket, Comunidad c) {
        this.nro = nro;
        this.s = socket;
        this.refComunidad = c;
        config();
    }
    
    /**
     * Método que configura la conexion con el cliente.
     * Crea los flujos de entrada y salida con el cliente. Y envia su número
     * de cliente. Además le establece un tiempo de inactividad de 30 min.
     * Si el cliente no envia ningún mensaje en ese tiempo se cierrar el socket.
     * 
     */
    private void config() {
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(nro);
            s.setSoTimeout(30*1000*60);
        } catch (IOException e) {
            System.err.println("Error al obtener flujos del cliente "+nro
                    +": "+e.getMessage());
        }
    }

    /**
     * Método encargado de escuchar los mensajes del cliente.
     */
    @Override
    public void run() {
        
        /**
         * Hilo encargado de enviar los clientes conectados a un cliente cada
         * 3 segundos.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {                    
                    try {
                        dos.writeUTF("?"+refComunidad.getUsuariosConectados().size());
                        for (Cliente c : refComunidad.getUsuariosConectados())
                            dos.writeInt(c.getNro());
                        sleep(3000);
                    } catch (IOException e) {
                        System.err.println("Error al enviar usuarios conectados: "
                                +e.getMessage());
                        break;
                    } catch (InterruptedException ie) {
                        System.out.println("Hilo interrumpido: "
                                +ie.getMessage());
                        break;
                    }
                }
            }
        }).start();
        
        conected = true;
        
        while (true) {            
            try {
                System.out.println("Esperando mensaje de cliente "+nro+"...");
                String str = dis.readUTF();
                System.out.println("Mensaje del cliente "+nro+" recibido: "+str);
                gestionarMensaje(str);
                yield();
            } catch (IOException e) {
                System.err.println("Error al leer entrada del cliente nro."
                        +nro+". "+e.getMessage());
                cerrar();
                break;
            }
        }
        
        conected = false;
    }
    
    /**
     * Método que permite comunicar la solicitud de un usuario a otro ante
     * poniendo el caracter especia '!'.
     * 
     * @param nro: identificador del solicitante.
     */
    public void comunicarSolicitud(int nro) {
        try {
            dos.writeUTF("!"+nro);
        } catch (IOException e) {
            System.err.println("Error al comunicar solicitud chat: "
                    +e.getMessage());
        }
    }
    
    /**
     * Método que permite comunicar la confirmación de un usuario al cual
     * se le envió una solicitud de chat.
     * 
     * @param nro: identificar del usuario que aceptó la solicitud.
     */
    public void comunicarConfirmacion(int nro) {
        try {
            dos.writeUTF("*"+nro);
        } catch (IOException e) {
            System.err.println("Error al comunicar confirmación: "
                    +e.getMessage());
        }
    }
    
    public void enviarMensaje(String msj) {
        try {
            dos.writeUTF(msj);
        } catch (IOException e) {
            System.err.println("No se pudo enviar msj porque "+e.getMessage());
            cerrar();
        }
    }
    
    /**
     * Método encargado de gestionar los mensajes recibidos del cliente
     * mediante caracteres especiales.
     */
    private void gestionarMensaje(String msj) {
        if(msj.startsWith("#")) {
            try {
                int nroContacto = Integer.parseInt(msj.substring(1));
                refComunidad.enviarSolicitudChat(nro, nroContacto);
            } catch (NumberFormatException e) {
                System.err.println("Nro cliente no válido");
            }
            return;
        }
        if(msj.startsWith("*")) {
            try {
                int nroContacto = Integer.parseInt(msj.substring(1));
                refComunidad.crearChat(nro, nroContacto);
            } catch (NumberFormatException e) {
                System.err.println("Número no válido!");
            }
            return;
        }
        if(chat != null) 
            chat.comunicar("U"+nro+": "+msj);
    }
    
    
    /**
     * Establece el chat actual del cliente.
     * @param chat: un chat.
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    
    /**
     * Método para cerrar el socket asociado a un cliente de forma segura.
     */
    private void cerrar() {
        try {
            s.close();
        } catch (IOException e) {
            System.err.println("Error al intentar cerrar el socket del "
                    + "cliente nro."+nro+": "+e.getMessage());
        }
    }

    public int getNro() {
        return nro;
    }

    public boolean isConected() {
        return conected;
    }
}
