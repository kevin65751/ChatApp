package chatapp;

import chatapp.servidor.model.Servidor;

/**
 *
 * @author Kevin
 */
public class ServidorMain {
    public static void main(String[] args) {
        new Servidor().startServer();
    }
}
