
// package simulacao;


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
    
    public int getPassageirosAbordo(){
        return passageirosABordo;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    public int getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(int pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public int getContadorPontosPassados() {
        return contadorPontosPassados;
    }

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
    
    public static void setSemaforos(ArrayList<Semaforo> s) {
        for (Semaforo semaforo: s) {
            int x = semaforo.getLocalizacaoAtual().getX();
            int y = semaforo.getLocalizacaoAtual().getY();

            semaforos[x][y] = 1;
        }
    }

    @Override
    public String toString(){
        
        return "Subiram a bordo : " + getPassageirosAbordo() + " " + "passageiros" + "\n";
    }
}
