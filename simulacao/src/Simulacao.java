// package simulacao;

import java.util.ArrayList;
import java.util.Random;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Veiculo veiculo;
    private Veiculo veiculo2;
    private Veiculo veiculo3;
    private Veiculo veiculo4;

    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private ArrayList<Ponto> pontos;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Pedestre> pedestres;
    private ArrayList<Semaforo> semaforos;

    private Random rand;
    private int pontoDestino;
    private int largura;
    private int altura;

    public Simulacao() {
        rand = new Random();
        mapa = new Mapa();
        largura = mapa.getLargura();
        altura = mapa.getAltura();
        
        veiculo = new Veiculo(new Localizacao(0, 0));//Cria um veiculo em uma posicao aleatoria
        veiculo2 = new Veiculo(new Localizacao(5, 5));
        veiculo3 = new Veiculo(new Localizacao(10, 10));
        veiculo4 = new Veiculo(new Localizacao(15, 15));

        pontos = new ArrayList<Ponto>();
        obstaculos = new ArrayList<Obstaculo>();
        pedestres = new ArrayList<Pedestre>();
        semaforos = new ArrayList<Semaforo>();
        
        instanciarPontosOnibus();
        intanciarObstaculos();
        instanciarSemaforos();
        intanciarPedrestre();


        veiculo.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());//Define a posicao destino aleatoriamente
        veiculo2.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
        veiculo3.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
        veiculo4.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());

        mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo
        mapa.adicionarItem(veiculo2);
        mapa.adicionarItem(veiculo3);
        mapa.adicionarItem(veiculo4);
        

        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public final void executarSimulacao(int numPassos){
        try{
            janelaSimulacao.executarAcao();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            for (int i = 0; i < numPassos; i++) {
                executarUmPasso();
                colocarSemaforo();
                colocarObstaculos();
                colocarPontos();
                janelaSimulacao.executarAcao();
                esperar(300);
            }
        }       
    }

    private void intanciarPedrestre(){
        for (int x = 0; x < 10; x++) {
            Pedestre pedestre = new Pedestre(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            pedestres.add(pedestre);
            mapa.adicionarItem(pedestre);
           
        }
    }

    private void instanciarPontosOnibus(){
        for (int x = 0; x < 10; x++) {
            Ponto ponto = new Ponto(
                new Localizacao(rand.nextInt(largura), rand.nextInt(altura))
            );
            pontos.add(ponto);
            mapa.adicionarItem(ponto);
        }
    }

    private void intanciarObstaculos(){
        for (int x = 0; x < 10; x++) {
            Obstaculo obstaculo = new Obstaculo(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            obstaculos.add(obstaculo);
            mapa.adicionarItem(obstaculo);
        }
    }

    private void instanciarSemaforos(){
        for (int y = 0 ; y < 40 ; y++){
            Semaforo semaforo = new Semaforo(
                new Localizacao(rand.nextInt(largura), rand.nextInt(altura))
            );
            semaforos.add(semaforo);
            mapa.adicionarItem(semaforo);
        }
    }

    private void executarUmPasso() {

        if (veiculo.comparePositionPos(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }
        else
        if (veiculo2.comparePositionPos(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }
        else
        if (veiculo3.comparePositionPos(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
            
        }
        else
        if (veiculo4.comparePositionPos(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));

        }

        boolean andando = veiculo.executarAcao();
        boolean andando1 = veiculo2.executarAcao();
        boolean andando2 = veiculo3.executarAcao();
        boolean andando3 = veiculo4.executarAcao();

        if (!andando) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            Colisao();
            veiculo.executarAcao();
        }else

        if (!andando1) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo2.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            Colisao();
            veiculo2.executarAcao();
        }else

        if (!andando2) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo3.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            Colisao();
            veiculo3.executarAcao();
        }else

        if (!andando3) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo4.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            Colisao();
            veiculo4.executarAcao();
            
        }
        mapa.adicionarItem(veiculo);
        mapa.adicionarItem(veiculo2);
        mapa.adicionarItem(veiculo3);
        mapa.adicionarItem(veiculo4);

    }


    public void colocarObstaculos(){
        for(Obstaculo obs : obstaculos ){
            mapa.removerItem(obs);
            mapa.adicionarItem(obs);
        }
    }

    public void colocarSemaforo(){
        for(Semaforo sem : semaforos){
            mapa.removerItem(sem);
            mapa.adicionarItem(sem);
        }
    }

    public void colocarPontos(){
        for(Ponto p : pontos){
            mapa.adicionarItem(p);
            mapa.adicionarItem(p);
        }
    }

    private void Colisao(){
        if(veiculo.comparePositionPos(pontos.get(pontoDestino-1))){
            esperar(5000);
        }
        else if(veiculo.comparePositionSem(semaforos.get(pontoDestino -1))){
            esperar(4000);
        }
    }
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
