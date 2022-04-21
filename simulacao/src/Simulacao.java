// package simulacao;

import java.util.ArrayList;
import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private ArrayList<Veiculo> veiculos;
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
        
        pontos = new ArrayList<Ponto>();
        obstaculos = new ArrayList<Obstaculo>();
        pedestres = new ArrayList<Pedestre>();
        semaforos = new ArrayList<Semaforo>();
        veiculos = new ArrayList<Veiculo>();
        
        instanciarPontosOnibus();
        intanciarObstaculos();
        instanciarSemaforos();
        intanciarPedrestre();
        instanciaOnibus();
        Veiculo.setSemaforos(semaforos);

        Localizacao.setObstaculos(obstaculos);
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

    private void instanciaOnibus() {
        Veiculo onibus;
        for (int i = 0; i < 4; i++) {
            onibus = new Veiculo(new Localizacao(i, i));//Cria um veiculo em uma posicao aleatoria
            onibus.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());//Define a posicao destino aleatoriamente
            mapa.adicionarItem(onibus);//Inicializando o mapa com o veículo
            veiculos.add(onibus);
        }
    }

    private void intanciarPedrestre(){

        for (int x = 0; x < 10; x++) {
            Pedestre pedestre = new Pedestre(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            pedestre.setLocalizacaoDestino(pontos.get(rand.nextInt(10)).getLocalizacaoAtual());
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
        int cont = 1;

        for (Veiculo onibus: veiculos) {
            if (onibus.comparePositionPos(pontos.get(pontoDestino-1))) {
                mapa.adicionarItem(pontos.get(pontoDestino-1));
            }

            boolean andando = onibus.executarAcao();

            if (!andando) {
                if (pontoDestino == pontos.size()) pontoDestino = 0;
                onibus.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());      
                gerarRelatorioDeRota(cont);
            }

            cont++;
            mapa.adicionarItem(onibus);
        }

        for (Pedestre pedestre: pedestres) {
            pedestre.executarAcao();
        }

    }

    public void gerarRelatorioDeRota(int cont){
        for(Veiculo v: veiculos){
            System.out.println("Veiculos " + cont + " " + v);
        }
    }

    private void colocarObstaculos(){
        for(Obstaculo obs : obstaculos ){
            mapa.removerItem(obs);
            mapa.adicionarItem(obs);
        }
    }

    private void colocarSemaforo(){
        for(Semaforo sem : semaforos){
            mapa.removerItem(sem);
            mapa.adicionarItem(sem);
        }
    }

    private void colocarPontos(){
        for(Ponto p : pontos){
            mapa.adicionarItem(p);
            mapa.adicionarItem(p);
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

