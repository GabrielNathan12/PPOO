// package simulacao;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    private Item[][] itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 30;
    private static final int ALTURA_PADRAO = 30;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public void adicionarItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    public void removerItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
}
