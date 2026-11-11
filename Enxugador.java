import java.util.logging.Logger;

public class Enxugador implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Enxugador.class.getName());
    
    private Escorredor escorredor;
    private String nome;
    private volatile boolean done = false;

    public Enxugador(String nome, Escorredor escorredor) {
        this.nome = nome;
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!done || escorredor.getQuantidade() > 0) {
            try {
                if (done && escorredor.getQuantidade() == 0) {
                    break;
                }

                Prato prato = escorredor.retirar();
                LOGGER.fine(nome + " retirou o prato #" + prato.getNumeroSerie() + " para enxugar.");
                enxugar();
                
            } catch (InterruptedException e) {
                if (escorredor.getQuantidade() > 0) {
                    continue; 
                } else {
                    break; 
                }
            }
        }
        LOGGER.info(nome + " encerrou o expediente.");
    }

    private void enxugar() {
        int passosDe10M = (int) (Math.random() * 8) + 3; 
        long limite = passosDe10M * 10_000_000L;

        for (long i = 0; i < limite; i++) {
        }
    }

    public void parar() {
        this.done = true;
    }
}