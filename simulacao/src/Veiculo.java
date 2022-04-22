

import java.util.ArrayList;
import java.util.Random;
/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item {
    private Localizacao localizacaoDestino;
    private int contador, contadorSemaforo , contadorPontosPassados;
    private static int[][] semaforos = new int[30][30];
    private Random rand;

    private int passageirosABordo;
    private int pontoDestino;
    
    public Veiculo(Localizacao localizacao , int pontoDestino) {
        super(localizacao, "Imagens/130262.png");
        localizacaoDestino = null;
        contador = 0;
        rand = new Random();
        passageirosABordo = rand.nextInt(5);
        this.pontoDestino = pontoDestino;
    }
    
    /**
     * Método que retorna os Passageiros a bordo do ônibus.
     * @return passageiros a bordo do ônibus
     */
    public int getPassageirosAbordo(){
        return passageirosABordo;
    }

    /**
     * Método que retorna a localização de destino.
     * @return localização de destino
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Método que retorna o Ponto de destino.
     * @return ponto de destino
     */
    public int getPontoDestino() {
        return pontoDestino;
    }

    /**
     * Método que define o ponto destino.
     * @param pontoDestino
     */
    public void setPontoDestino(int pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    /**
     * Método que define a localização destino.
     * @param localizacaoDestino
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Método que retorna o contador de Pontos que o ônibus já passou.
     * @return contador de pontos passados
     */
    public int getContadorPontosPassados() {
        return contadorPontosPassados;
    }

    /**
     * Método que executa a ação.
     * 
     * @return boolean
     */
    public boolean executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        int x = localizacaoAtual.getX();
        int y = localizacaoAtual.getY();
        if(destino != null){
            if (semaforos[x][y] != 1 || contadorSemaforo == 10) {
                contadorSemaforo = 0;
                Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
                if (proximaLocalizacao != null) {
                    contador = 0;
                    setLocalizacaoAtual(proximaLocalizacao);
                    return true;
                }
                else {
                    contador++;
                    if(contador == 10){
                        contadorPontosPassados++;
                        return false;
                    }

                    return true;
                }
            }
            else {
                contadorSemaforo++;
                if (contador == 10) {
                    return executarAcao();
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método que define os semáforos.
     * @param s
     */
    public static void setSemaforos(ArrayList<Semaforo> s) {
        for (Semaforo semaforo: s) {
            int x = semaforo.getLocalizacaoAtual().getX();
            int y = semaforo.getLocalizacaoAtual().getY();

            semaforos[x][y] = 1;
        }
    }

    /**
     * Método toString que imprime na tela a quantidade de pedestres que subiram a bordo do ônibus.
     * @return impressão na tela da quantidade de pedestres que subiram a bordo do ônibus
     */
    @Override
    public String toString(){
        
        return "Subiram a bordo : " + getPassageirosAbordo() + " " + "passageiros" + "\n";
    }
}
