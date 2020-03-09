package chatapp.servidor.model;

import chatapp.ConexionInfo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Kevin
 * 
 * Clase que representa al servidor. Realiza la creación de un serverSocket
 * con el puerto especificado de la clase ConexionInfo.
 * 
 */
public class Servidor {
    private final Comunidad c;
    private ServerSocket ss;
    
    /**
     * Crea el servidor y una comunidad de usuarios(cliente) vacia.
     */
    public Servidor() {
        initServer();
        c = new Comunidad();
    }
    
    
    /**
     * Método encargado de crear el ServerSocket. Si el puerto no está disponible
     * se captura una IOException y se cierra el programa. Usted puede cambiar
     * esta funcionalidad en el ámbito catch(IOException) {}.
     */
    private void initServer() {
        try {
            ss = new ServerSocket(ConexionInfo.PORT);
            System.out.println("Servidor configurado.");
        } catch (IOException ioe) {
            System.err.println("No se pudo inciar el servidor. "
                    +ioe.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Método encargado de recibir a los clientes y crear una instancia
     * de socket para la comunicación con este. Luego se agregar a dicho
     * cliente a la comunidad(conjunto de clientes) del servidor con un
     * identificador.
     */
    public void startServer() {
        int contador = -1;
        Socket s;
        System.out.println("Servidor iniciado!");
        while (true) {            
            try {
                s = ss.accept();
                c.agregarCliente(++contador, s);
                System.out.println("Cliente nro. "+contador+" conectado!");
            } catch (IOException e) {
                System.err.println("No se pudo atender petición. "
                        +e.getMessage());
            }
        }
    }
    
}
