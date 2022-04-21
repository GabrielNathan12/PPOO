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
   // private int pontoDestino;
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
       // gerarRelatorioDeRota();
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
                esperar(100);
            }
        }       
    }

    private void instanciaOnibus() {
        Veiculo onibus;
        for (int i = 0; i < 4; i++) {
            onibus = new Veiculo(new Localizacao(i, i), i + 1);//Cria um veiculo em uma posicao aleatoria
            onibus.setLocalizacaoDestino(pontos.get(i).getLocalizacaoAtual());//Define a posicao destino aleatoriamente
            mapa.adicionarItem(onibus);//Inicializando o mapa com o veículo
            veiculos.add(onibus);
        }
        
    }

    private void intanciarPedrestre(){

        for (int x = 0; x < 5; x++) {
            Pedestre pedestre = new Pedestre(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            pedestre.setLocalizacaoDestino(pontos.get(rand.nextInt(pontos.size())).getLocalizacaoAtual());
            pedestres.add(pedestre);
            mapa.adicionarItem(pedestre);
        }
        
    
    }

    private void instanciarPontosOnibus(){
        for (int x = 0; x < 8; x++) {
            Ponto ponto = new Ponto(
                new Localizacao(rand.nextInt(largura), rand.nextInt(altura))
            );
            pontos.add(ponto);
            mapa.adicionarItem(ponto);
        }
    }

    private void intanciarObstaculos(){
        int auxX;
        int auxY;
        for (int x = 0; x < 10; x++) {
            do {
                auxX = rand.nextInt(largura);
                auxY = rand.nextInt(altura);
            } while(mapa.getItem(auxX, auxY) != null);

            Obstaculo obstaculo = new Obstaculo(
                new Localizacao(auxX, auxY)
            );
            obstaculos.add(obstaculo);
            mapa.adicionarItem(obstaculo);
        }
    }

    private void instanciarSemaforos(){
        int auxX;
        int auxY;
        for (int y = 0 ; y < 20 ; y++){
        
            do{
                auxX = rand.nextInt(largura);
                auxY = rand.nextInt(altura);
            } while(mapa.getItem(auxX, auxY) != null);
            
            Semaforo semaforo = new Semaforo(
                new Localizacao(auxX, auxY)
            );
            semaforos.add(semaforo);
            mapa.adicionarItem(semaforo);
        }
        
    }

    private void executarUmPasso() {
        int pontoDestino;
        
        for (Veiculo onibus: veiculos) {
            pontoDestino = onibus.getPontoDestino();
            if (onibus.comparePositionPos(pontos.get(pontoDestino-1)) ) {
                mapa.adicionarItem(pontos.get(pontoDestino-1));
            }

            boolean andando = onibus.executarAcao();

            if (!andando) {
               if (pontoDestino == pontos.size()) pontoDestino = 0;
                if (onibus.getContadorPontosPassados() >= pontos.size() * 3) {
                    onibus.setPontoDestino(pontoDestino + 1);
                    onibus.setLocalizacaoDestino(new Localizacao(29, 29));

                } else {

                    onibus.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
                    onibus.setPontoDestino(pontoDestino);
                }
            }

            mapa.adicionarItem(onibus);
        }

        for (Pedestre pedestre: pedestres) {
            pedestre.executarAcao();
        }
    }

    public void gerarRelatorioDeRota(){
        for(Veiculo v: veiculos){
            System.out.println("Veiculos " + v.toString());
        }
        for(Pedestre p : pedestres){
            System.out.println(p.toString());
        }
        for(Ponto p : pontos){
            System.out.println(p.toString());
        }
        for(Obstaculo o : obstaculos){
            System.out.println(o.toString());
        }
        for(Semaforo s : semaforos){
            System.out.println(s.toString());
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

