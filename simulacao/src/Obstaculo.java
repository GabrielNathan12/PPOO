public class Obstaculo extends Item {

    
    public Obstaculo(Localizacao localizacao) {
        super(localizacao, "Imagens/1141794.png");
    }
    
    @Override
        public String toString(){
            return "Obstaculo Encontrado : " ;
        }
}
