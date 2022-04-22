/**
 * @author Gabriel Nathan, Mateus Margotti, e Vitor Melo.
 */
public class Semaforo extends Item{

    /**
     * Construtor da classe Semáforo
     * @param localizacao
     */
    public Semaforo(Localizacao localizacao) {
        super(localizacao, "Imagens/Semaforo.png");
    }

    /**
     * Método toString que imprime na tela informando que o ônibus está parado no semáforo.
     */
    @Override
    public String toString(){
        return "Parado no Semaforo : " ;
    }
}
