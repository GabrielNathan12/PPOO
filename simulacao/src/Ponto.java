public class Ponto extends Item {

    public Ponto(Localizacao localizacao) {
        super(localizacao, "Imagens/1042263.png");
    }
    
    @Override
        public String toString(){
            return "Esperando pedestre embarcar : ";
        }
}
