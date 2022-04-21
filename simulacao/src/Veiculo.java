// package simulacao;


/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item {
    private Localizacao localizacaoDestino;

    public Veiculo(Localizacao localizacao) {
        super(localizacao, "Imagens/130262.png");
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

}
