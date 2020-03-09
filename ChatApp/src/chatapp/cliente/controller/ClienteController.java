package chatapp.cliente.controller;

import chatapp.cliente.model.Cliente;
import chatapp.cliente.model.Escucha;
import chatapp.cliente.view.ClienteView;

/**
 *
 * @author Kevin
 * 
 * Clase controladora del cliente.
 */
public class ClienteController {
    private final ClienteView cv;
    private final Cliente c;
    private final Escucha e;

    
    /**
     * Se instancian dependencias y se configuran mediante un método
     * privado.
     */
    public ClienteController() {
        this.cv = new ClienteView();
        this.c = new Cliente();
        this.e = new Escucha(cv, c);
        configurar();
    }
    
    /**
     * Método que configura la vista con el modelo. En concreto, agregar
     * un Listener para escuchar el envio de mensajes de un cliente; 
     * muestra el número de cliente (identificador) en la vista; inicia 
     * un hilo que permite escuchar los mensajes del servidor.
     * 
     */
    private void configurar() {
        cv.agregarEnvioListener( ae -> {
            String msj = cv.getMensaje();
            if(msj == null || msj.isEmpty()) {
                cv.mostrarError("Ningún mensaje escrito");
                return;
            }
            if(!c.enviarMensaje(msj)) {
                cv.mostrarError("No se pudo enviar el mensaje.");
                return;
            }
            cv.limpiarMensaje();
        });
        cv.setNroUsuario(c.getNroCliente());
        e.start();
        cv.setVisible(true);
    }
    
}
