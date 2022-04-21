public class Semaforo extends Item{

    public Semaforo(Localizacao localizacao) {
        super(localizacao, "Imagens/Semaforo.png");
    }
    @Override
    public String toString(){
        return "Parado no Semaforo : " ;
    }
}
