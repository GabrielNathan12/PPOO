
// package simulacao;


/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item {
    private Localizacao localizacaoDestino;
    private int contador;

    public Veiculo(Localizacao localizacao) {
        super(localizacao, "Imagens/130262.png");
        localizacaoDestino = null;
        contador = 0;
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
                contador = 0;
                setLocalizacaoAtual(proximaLocalizacao);
                return true;
            }
            else {
                contador++;
                if(contador == 10){
                    return false;
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return Veiculo.class.getName();
    }
}
