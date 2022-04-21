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
    private ArrayList<Item> obstaculos;
    private ArrayList<Pedestre> pedestres;
    private ArrayList<Semaforo> semaforos;
    private Random rand;
    private int pontoDestino;
    private int largura;
    private int altura;
    //private ArrayList<Veiculo> Onibus;

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
        obstaculos = new ArrayList<Item>();
        pedestres = new ArrayList<Pedestre>();
        semaforos = new ArrayList<Semaforo>();

       // Onibus = new ArrayList<Veiculo>();

        for (int x = 0; x < 10; x++) {
            Ponto ponto = new Ponto(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            Item obstaculo = new Item(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura)),
                "Imagens/1141794.png"
            );
            Pedestre pedestre = new Pedestre(
                new Localizacao(rand.nextInt(largura),rand.nextInt(altura))
            );
            

            for (int y = 0 ; y < 5 ; y++){
                Semaforo semaforo = new Semaforo(
                    new Localizacao(rand.nextInt(largura), rand.nextInt(altura))
                );
            

          //  Veiculo onibus = new Veiculo(
            //    new Localizacao(rand.nextInt(largura), rand.nextInt(altura))
            //);

            //Onibus.add(onibus);
            pontos.add(ponto);
            obstaculos.add(obstaculo);
            pedestres.add(pedestre);
            semaforos.add(semaforo);

            mapa.adicionarItem(ponto);
            mapa.adicionarItem(obstaculo);
            mapa.adicionarItem(pedestre);
            mapa.adicionarItem(semaforo);
            //mapa.adicionarItem(onibus);
            
            }
        }
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
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            //pedestresSeMovem();
            colocarObstaculos();
            colocarPontos();
            colocarSemaforo();
            janelaSimulacao.executarAcao();
            esperar(500);
           
        }        
    }

    private void executarUmPasso() {
        
        //mapa.removerItem(veiculo);
        //mapa.removerItem(veiculo2);
        //mapa.removerItem(veiculo3);
        //mapa.removerItem(veiculo4);
   
        if (veiculo.comparePosition(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }

        if (veiculo2.comparePosition(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }

        if (veiculo3.comparePosition(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }

        if (veiculo4.comparePosition(pontos.get(pontoDestino-1))) {
            mapa.adicionarItem(pontos.get(pontoDestino-1));
        }

        boolean andando = veiculo.executarAcao();
        boolean andando1 = veiculo2.executarAcao();
        boolean andando2 = veiculo3.executarAcao();
        boolean andando3 = veiculo4.executarAcao();

        if (!andando) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
           // esperarSemaforo(1000);
            veiculo.executarAcao();
        }

        if (!andando1) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo2.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            //esperarSemaforo(1000);
            veiculo2.executarAcao();
        }

        if (!andando2) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo3.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            //esperarSemaforo(1000);
            veiculo3.executarAcao();
        }

        if (!andando3) {
            if (pontoDestino == pontos.size()) pontoDestino = 0;
            veiculo4.setLocalizacaoDestino(pontos.get(pontoDestino++).getLocalizacaoAtual());
            //esperarSemaforo(1000);
            veiculo4.executarAcao();
            
        }

        mapa.adicionarItem(veiculo);
        mapa.adicionarItem(veiculo2);
        mapa.adicionarItem(veiculo3);
        mapa.adicionarItem(veiculo4);

    }

   /* public void pedestresSeMovem() {
        for (Pedestre pedestre : pedestres) {
            mapa.removerItem(pedestre);
            pedestre.movimentar();
            mapa.adicionarItem(pedestre);
        }
    }
    */

    public void colocarObstaculos(){
        for(Item obs : obstaculos ){
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

    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void esperarSemaforo(int segundos){
        try{
            Thread.sleep(segundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void esperarNoPonto(int segundos){
        try{
            Thread.sleep(segundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    public int getAltura(){
        return altura;
    }
    public int getLargura(){
        return largura;
    }
    
}
