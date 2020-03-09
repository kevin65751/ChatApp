package chatapp;

/**
 *
 * @author Kevin
 */
public class ChatApp {
    
    /**
     * Usted puede ejecutar este método para crear el servidor
     * y 4 clientes simultaneamente, para realizar una prueba rápida.
     */
    
    public static void main(String[] args) {
        int nroClientes = 4;
        if(args != null && args.length == 1) {
            try {
                nroClientes = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("El argumento (cantidad de clientes) tiene "
                        + "que ser un número. Se creará 4 clientes por defecto.");
            }
        }
        
        new Thread(() -> { ServidorMain.main(args); }).start();
        
        for (int i = 0; i < nroClientes; i++) 
            new Thread(() -> { ClienteMain.main(args); }).start();
    }
    
}
