import java.util.Random;

public class Pedestre extends Item {
    public Pedestre(Localizacao localizacao) {
        super(localizacao, "imagens/Pedestre.png");
    }

    public void movimentar() {
        Random rand = new Random();
        int direcaoMovimento = rand.nextInt(4);
        int atualX = getLocalizacaoAtual().getX();
        int atualY = getLocalizacaoAtual().getY();

        switch (direcaoMovimento) {
            case 0:
                if (atualX < 19) {
                    setLocalizacaoAtual(new Localizacao(atualX + 1, atualY));
                }
                break;
            case 1:
                if (atualY < 19) {
                    setLocalizacaoAtual(new Localizacao(atualX, atualY + 1));
                }
                break;
            case 2:
                if (atualX > 0) {
                    setLocalizacaoAtual(new Localizacao(atualX - 1, atualY));
                }
                break;
            case 3:
                if (atualY > 0) {
                    setLocalizacaoAtual(new Localizacao(atualX, atualY - 1));
                }
                break;
        }
    }
}
