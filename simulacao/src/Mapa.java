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

    /**
     * Método para adicionar itens no mapa.
     * @param v
     */
    public void adicionarItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    /**
     * Método para remover itens do mapa.
     * @param v
     */
    public void removerItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    /**
     * Método para efetuar a atualização do Mapa.
     * Remove e adiciona itens do mapa para representar a movimentação
     * @param v
     */
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    /**
     * Método para retornar um item
     * @param x
     * @param y
     * @return itens
     */
    public Item getItem(int x, int y){
        return itens[x][y];
    }

    /**
     * Método para retornar a largura da área
     * @return largura da area de simulacao
     */
    public int getLargura() {
        return largura;
    }

    /**
     * Método para retornar a altura da área
     * @return altura da area de simulacao
     */
    public int getAltura() {
        return altura;
    }
}
