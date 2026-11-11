import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    private Escorredor escorredor;
    private List<Lavador> lavadores;
    private List<Enxugador> enxugadores;
    private List<Thread> threads;

    public App() {
        this.escorredor = new Escorredor(10);
        this.lavadores = new ArrayList<>();
        this.enxugadores = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    public void iniciar(int qtdLavadores, int qtdEnxugadores) {
        LOGGER.info("Iniciando os trabalhos com " + qtdLavadores + " lavadores e " + qtdEnxugadores + " enxugadores...");

        for (int i = 1; i <= qtdLavadores; i++) {
            Lavador lavador = new Lavador("Lavador " + i, escorredor);
            lavadores.add(lavador);
            Thread t = new Thread(lavador);
            threads.add(t);
            t.start();
        }

        for (int i = 1; i <= qtdEnxugadores; i++) {
            Enxugador enxugador = new Enxugador("Enxugador " + i, escorredor);
            enxugadores.add(enxugador);
            Thread t = new Thread(enxugador);
            threads.add(t);
            t.start();
        }
    }

    public void parar() {
        LOGGER.info("Solicitando encerramento dos trabalhos...");
        
        for (Lavador l : lavadores) l.parar();
        for (Enxugador e : enxugadores) e.parar();

        for (Thread t : threads) {
            t.interrupt();
        }
    }

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
        rootLogger.getHandlers()[0].setLevel(Level.INFO);

        Scanner scanner = new Scanner(System.in);
        
        LOGGER.info("Digite a quantidade de Lavadores: ");
        int qtdLavadores = scanner.nextInt();
        
        LOGGER.info("Digite a quantidade de Enxugadores: ");
        int qtdEnxugadores = scanner.nextInt();
        
        scanner.close();

        App app = new App();
        app.iniciar(qtdLavadores, qtdEnxugadores);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.parar();
    }
}