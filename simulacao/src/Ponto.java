/**
 * @author Gabriel Nathan, Mateus Margotti, e Vitor Melo.
 */
public class Ponto extends Item {
    /**
     * Construtor da classe Ponto
     * @param localizacao
     */
    public Ponto(Localizacao localizacao) {
        super(localizacao, "Imagens/1042263.png");
    }
    
    /**
     * Método toString que imprime na tela
     * @return impressão na tela informando que o ônibus está parado esperando o pedestre embarcar.
     */
    @Override
        public String toString(){
            return "Esperando pedestre embarcar : ";
        }
}
