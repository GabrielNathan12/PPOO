/**
 * @author Gabriel Nathan, Mateus Margotti, e Vitor Melo.
 * 
 */
public class Obstaculo extends Item {

    /**
     * Construtor da classe Obstaculo
     * @param localizacao
     */
    public Obstaculo(Localizacao localizacao) {
        super(localizacao, "Imagens/1141794.png");
    }
    
    /**
     * Método toString que imprime os Obstaculos Encontrados.
     * @return String
     */
    @Override
        public String toString(){
            return "Obstaculo Encontrado : " ;
        }
}
