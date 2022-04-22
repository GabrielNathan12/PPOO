
/**
 * @author Gabriel Nathan, Mateus Margotti, e Vitor Melo.
 */
public class Pedestre extends Item {
    private Localizacao localizacaoDestino;

    /**
     * Construtor da classe Pedestre
     * @param localizacao
     */
    public Pedestre(Localizacao localizacao) {
        super(localizacao, "imagens/Pedestre.png");
        localizacaoDestino = null;
    }

    /**
     * Método que retorna a Localização Destino
     * @return localizacao de destino;
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Método que define a Localizacao Destino
     * @param localizacaoDestino
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Método que executa a ação
     * 
     * @return true: se ação
     *         false: se ação
     */
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

    /**
     * Método toString que retorna a impressao na tela da quantidade de Pedestres no ponto.
     * @return impressao da quantidade de Pedestres no ponto.
     */
    @Override
        public String toString(){
            return "Pedestre no Ponto : ";
        }

}
