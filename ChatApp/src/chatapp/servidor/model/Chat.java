package chatapp.servidor.model;

/**
 *
 * @author Kevin
 */
public class Chat {
    private final Cliente c1, c2;

    public Chat(Cliente c1, Cliente c2) {
        this.c1 = c1;
        this.c2 = c2;
        config();
    }
    
    private void config() {
        c1.setChat(this);
        c2.setChat(this);
        c2.comunicarConfirmacion(c1.getNro());
    }
    
    public void comunicar(String msj) {
        c1.enviarMensaje(msj);
        c2.enviarMensaje(msj);
    }
}
