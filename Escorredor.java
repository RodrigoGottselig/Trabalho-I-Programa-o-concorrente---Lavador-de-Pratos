import java.util.logging.Logger;

public class Escorredor {
    private static final Logger LOGGER = Logger.getLogger(Escorredor.class.getName());
    
    private Prato[] fila;
    private int inicio;
    private int fim;
    private int quantidade;

    public Escorredor(int capacidade) {
        this.fila = new Prato[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.quantidade = 0;
    }

    public synchronized void colocar(Prato prato) throws InterruptedException {
        while (quantidade == fila.length) {
            notify(); 
            wait();
        }
        
        boolean estavaVazio = (quantidade == 0);
        
        fila[fim] = prato;
        fim = (fim + 1) % fila.length;
        quantidade++;
        
        if (quantidade == fila.length) {
            LOGGER.info("ESCORREDOR CHEIO! Quantidade: " + quantidade);
        }

        if (estavaVazio) {
            notify();
        }
    }

    public synchronized Prato retirar() throws InterruptedException {
        while (quantidade == 0) {
            notify(); 
            wait();
        }
        
        boolean estavaCheio = (quantidade == fila.length);
        
        Prato prato = fila[inicio];
        inicio = (inicio + 1) % fila.length;
        quantidade--;
        
        if (quantidade == 0) {
            LOGGER.info("ESCORREDOR VAZIO! Quantidade: " + quantidade);
        }
        
        if (estavaCheio) {
            notify();
        }
        
        return prato;
    }

    public synchronized int getQuantidade() {
        return quantidade;
    }
}