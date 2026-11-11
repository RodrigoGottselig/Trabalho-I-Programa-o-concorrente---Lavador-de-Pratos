public class Prato {
    private static int contadorGlobal = 1; 
    
    private int numeroSerie;
    private NivelSujeira sujeira;

    public Prato() {
        this.numeroSerie = contadorGlobal++;
        
        double sorteio = Math.random();
        
        if (sorteio <= 0.30) {
            this.sujeira = NivelSujeira.BAIXO;       
        } else if (sorteio <= 0.90) {
            this.sujeira = NivelSujeira.MEDIO;       
        } else {
            this.sujeira = NivelSujeira.ENGORDURADO; 
        }
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public NivelSujeira getSujeira() {
        return sujeira;
    }
}