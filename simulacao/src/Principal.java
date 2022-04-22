/**
 *
 * @author Luiz Merschmann
 */
public class Principal {

    /**
     * Método main
     * Responsável por criar um objeto da classe Simulação, e executar a Simulação, com 500 passos.
     * @param args
     */
    public static void main(String[] args) {
        Simulacao sim = new Simulacao();
        sim.executarSimulacao(500);

    }
}
