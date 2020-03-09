package chatapp.cliente.model;

import chatapp.cliente.view.ClienteView;
import chatapp.cliente.view.UsuarioPanel;
import java.io.IOException;
import static java.lang.Thread.yield;
/**
 *
 * @author Kevin
 * 
 * Clase encargada de escuchar los mensajes del servidor. Es necesario 
 * que extienda de Thread, ya que si no fuera así se quedaria
 * 'congelado' hasta la llegada de un mensaje del servidor. Obtiene las 
 * referencias del Cliente(modelo) y ClienteView(vista) para mostrar al
 * cliente los mensajes del servidor en su interfaz gráfica.
 * 
 */
public class Escucha extends Thread {
    private final ClienteView cv;
    private final Cliente c;
    
    public Escucha(ClienteView cv, Cliente c) {
        this.cv = cv;
        this.c = c;
    }
        
    /**
     * Bucle encargado de escuchar los mensajes del servidor entrantes por
     * el flujo de entrada del cliente mediante el método readUTF(). Se 
     * gestiona los mensajes mediante el método gestionarMensaje(String).
     * Si el servidor se desconecta se capturará una IOException y el cliente
     * terminará su ejecución. Usted puede cambiar este comportamiento para
     * que intente conectarse nuevamente.
     */
    @Override
    public void run() {
        while (true) {                
            try {
                String str = c.getDis().readUTF();
                gestionarMensaje(str);
                yield();
            } catch (IOException e) {
                System.err.println("Servidor desconectado: "
                        +e.getMessage()+". Intente conectarse más tarde.");
                System.exit(0);
            }
        }
    }

    /**
     * Método encargado de gestionar los mensajes del servidor. La gestión
     * se realiza mediante un caracter especial al inicio del mensaje.
     * Este es el significado de cada mensaje recibido por el servidor:
     * !{id}: solicitud entrante de otro cliente para un chat.
     * *{id}: solicitud aceptada del cliente con id = {id}. Es necesario para
     *      que un cliente pueda seguir sus operaciones hasta que reciba la 
     *      confirmación de un cliente al que envio solicitud de chat
     * ?{nro}: número de clientes conectados. Sirve para la actualización de los
     *      clientes en linea con los que usted puede iniciar un chat. Luego de
     *      este mensaje el servidor envia los id's de los clientes conectados.
     * 
     */
    private void gestionarMensaje(String msj) {
        if(msj.startsWith("!")) {
            try {
                int nroContacto = Integer.parseInt(msj.substring(1));
                boolean aceptado = cv.mostrarSolicitudChat(nroContacto);
                if(aceptado) {
                    c.enviarMensaje("*"+nroContacto);
                    cv.setNroContacto(nroContacto);
                }
                return;
            } catch (NumberFormatException e) {
                System.err.println("Número no válido: "+e.getMessage());
            }
        }
        if(msj.startsWith("*")) {
            int nroContact = Integer.parseInt(msj.substring(1));
            cv.setNroContacto(nroContact);
            cv.limpiarChat();
            return;
        }
        if(msj.startsWith("?")){
            int nroUsuarios;
            try {
                nroUsuarios = Integer.parseInt(msj.substring(1));
                cv.limpiarPanelUsuariosEnLinea();
                int nro;
                for (int i = 0; i < nroUsuarios; i++) {
                    nro = c.getDis().readInt();
                    if(nro != c.getNroCliente())
                        cv.agregarUsuarioLinea(new UsuarioPanel(nro, c));
                }
            } catch (NumberFormatException e) {
                System.err.println("Numero no válido");
            } catch (IOException ioe) {
                System.err.println("Error al leer usuarios en linea: "
                        +ioe.getMessage());
            }
            return;
        }
        cv.agregarChat(msj);
    }
}

