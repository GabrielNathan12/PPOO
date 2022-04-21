import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class  Item {
    private Image imagem;
    private Localizacao localizacaoAtual;

    public Item(Localizacao localizacao, String nomeImg) {
        this.localizacaoAtual = localizacao;
        this.imagem = new ImageIcon(getClass().getResource(nomeImg)).getImage();

    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public boolean comparePosition(Item i) {
        int x1 = i.getLocalizacaoAtual().getX();
        int y1 = i.getLocalizacaoAtual().getY();
        int x2 = this.getLocalizacaoAtual().getX();
        int y2 = this.getLocalizacaoAtual().getY();

        if (x1 == x2 && y1 == y2) {
            return true;
        }

        return false;
    }

    public boolean comparePositionSem(Semaforo i) {
        int x1 = i.getLocalizacaoAtual().getX();
        int y1 = i.getLocalizacaoAtual().getY();
        int x2 = this.getLocalizacaoAtual().getX();
        int y2 = this.getLocalizacaoAtual().getY();

        if (x1 == x2 && y1 == y2) {
            return true;
        }

        return false;
    }

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
