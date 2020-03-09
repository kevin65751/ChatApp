package chatapp;

import chatapp.cliente.controller.ClienteController;

/**
 *
 * @author Kevin
 */
public class ClienteMain {
    /**
     * Puede ejecutar este método para crear un cliente. Recomendable ejecutar
     * después de inicar el servidor. El cliente intentará conectarse
     * con el servidor. Puede modificar el método privado conectarse() en la
     * clase Cliente para cambiar esta funcionalidad.
     */
    public static void main(String[] args) {
        new ClienteController();
    }
}
