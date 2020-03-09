package chatapp.servidor.model;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 * 
 * Clase que representa al conjunto de clientes y chats en el servidor.
 * Y que permite la interacción entre estos. No hay gestión de contactos, es
 * decir todos pueden ver a todos los usuarios.
 */
public class Comunidad {
    private final List<Cliente> clientes;
    private final List<Chat> chats;
    
    /**
     * Crea dos listas una que almacena las referencias a los clientes
     * y otras a los chats creados por estos.
     */
    public Comunidad() {
        this.clientes = new ArrayList<>();
        this.chats = new ArrayList<>();
    }
    
    /**
     * Método para agregar un cliente a la comunidad.
     * @param nro: identificador provisto por el servidor.
     * @param s: socket asociado al cliente.
     */
    public void agregarCliente(int nro, Socket s) {
        Cliente c = new Cliente(nro, s, this);
        clientes.add(c);
        c.start();
    }
    
    /**
     * Método que permite a un cliente solicitar a otro un chat.
     * @param emisor: identificador del emisor de la solicitud.
     * @param receptor: identificador del receptor de la solicitud.
     */
    public void enviarSolicitudChat(int emisor, int receptor) {
        clientes.get(receptor).comunicarSolicitud(emisor);
    }
    
    /**
     * Método que permite crear un nuevo chat. Entre dos clientes.
     * Se utiliza cuando un cliente acepta la solicitud de otro.
     * @param nro1: identificador de un cliente.
     * @param nro2: identificador de un cliente.
     */
    public void crearChat(int nro1, int nro2) {
        chats.add(new Chat(clientes.get(nro1), clientes.get(nro2)));
    }
    
    /**
     * Método que permite obtener los clientes conectados de la comunidad
     * de clientes.
     * @return retorna la lista de clientes conectados.
     */
    public List<Cliente> getUsuariosConectados() {
        List<Cliente> l = new ArrayList<>();
        for (Cliente c : clientes) 
            if(c.isConected())
                l.add(c);
        return l;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public int getSize() {
        return clientes.size();
    }
    
}
