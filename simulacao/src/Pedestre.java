
public class Pedestre extends Item {
    private Localizacao localizacaoDestino;

    public Pedestre(Localizacao localizacao) {
        super(localizacao, "imagens/Pedestre.png");
        localizacaoDestino = null;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public boolean executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            if (proximaLocalizacao != null) {
                setLocalizacaoAtual(proximaLocalizacao);
                return true;
            }
        }

        return false;
    }

    @Override
        public String toString(){
            return "Pedestre no Ponto : ";
        }

}
