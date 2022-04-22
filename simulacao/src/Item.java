import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Superclasse que representa todos os itens do Mapa.
 * @author Gabriel Nathan, Mateus Margotti, Vitor Melo
 */
public class  Item {

    private Image imagem;
    protected Localizacao localizacaoAtual;

    /**
     * Contrutor da classe Item
     * @param localizacao
     * @param nomeImg
     */
    public Item(Localizacao localizacao, String nomeImg) {
        this.localizacaoAtual = localizacao;
        this.imagem = new ImageIcon(getClass().getResource(nomeImg)).getImage();

    }

    /**
     * Método que retorna a localização atual dos itens no mapa.
     * @return localizacaoAtual
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Método que define a localização atual dos itens no mapa.
     * @param localizacaoAtual
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
    /**
     * Método que retorna a imagem dos itens do mapa.
     * @return
     */
    public Image getImagem(){
        return imagem;
    }

    /**
     * Método que compara posição dos itens do mapa
     * @param i
     * @return true: se
     *         false: se
     */
    public boolean comparePositionPos(Ponto i) {
        int x1 = i.getLocalizacaoAtual().getX();
        int y1 = i.getLocalizacaoAtual().getY();
        int x2 = this.getLocalizacaoAtual().getX();
        int y2 = this.getLocalizacaoAtual().getY();

        if (x1 == x2 && y1 == y2) {
            return true;
        }

        return false;
    }

}
