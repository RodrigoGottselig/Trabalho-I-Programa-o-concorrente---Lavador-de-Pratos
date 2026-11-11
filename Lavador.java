import java.util.logging.Logger;

public class Lavador implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Lavador.class.getName());
    
    private Escorredor escorredor;
    private String nome;
    private volatile boolean done = false; 

    public Lavador(String nome, Escorredor escorredor) {
        this.nome = nome;
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!done) {
            Prato prato = new Prato();
            lavar(prato);
            
            try {
                escorredor.colocar(prato);
                LOGGER.fine(nome + " colocou o prato #" + prato.getNumeroSerie() + " no escorredor.");
            } catch (InterruptedException e) {
                break;
            }
        }
        LOGGER.info(nome + " encerrou o expediente.");
    }

    private void lavar(Prato prato) {
        long limite = 0;
        switch (prato.getSujeira()) {
            case BAIXO: limite = 30_000_000L; break;
            case MEDIO: limite = 50_000_000L; break;
            case ENGORDURADO: limite = 100_000_000L; break;
        }
        
        for (long i = 0; i < limite; i++) {
        }
    }

    public void parar() {
        this.done = true;
    }
}