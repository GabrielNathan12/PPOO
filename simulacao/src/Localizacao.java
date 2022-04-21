// package simulacao;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa uma localização no mapa
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Localizacao {
    private int x;
    private int y;
    private static Random rand = new Random();
    private static int[][] obstaculos = new int[30][30];
    
    /**
     * Representa uma localização na cidade
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     */
    public Localizacao(int x, int y) {
        this.x = x;
        this.y = y;
      //  System.out.println(x + " " + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * Gera a localizacao para se mover visando alcançar o destino
     * @param localizacaoDestino: localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino){
        if(localizacaoDestino.equals(this)){//Verifica se já alcancou o destino
            return null;
        }else{
            int destX = localizacaoDestino.getX();
            int destY = localizacaoDestino.getY();
            int deslocX = x < destX ? 1 : x > destX ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em x
            int deslocY = y < destY ? 1 : y > destY ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em y
            Localizacao novaLocalizacao;

            int randdd = rand.nextInt(2);

            if (randdd == 0 && deslocX != 0 && x+deslocX >= 0 && x+deslocX <= 29 && obstaculos[x+deslocX][y] != 1) {

                novaLocalizacao = new Localizacao(x + deslocX, y);

            } else if (deslocY != 0 && y+deslocY >= 0 && y+deslocY <= 29 && obstaculos[x][y+deslocY] != 1) {
                
                novaLocalizacao = new Localizacao(x, y + deslocY);

            } else if (randdd == 1 && deslocX != 0 && x+deslocX >= 0 && x+deslocX <= 29 && obstaculos[x+deslocX][y] != 1) {

                novaLocalizacao = new Localizacao(x + deslocX, y);

            } else {

                int aux, auxX, auxY;
                do {
                    aux = rand.nextInt(4);
                    auxX = x;
                    auxY = y;

                    switch (aux) {
                        case 0 :
                            auxX = x+1;
                            break;
                        case 1 :
                            auxX = x-1;
                            break;
                        case 2 :
                            auxY = y+1;
                            break;
                        default :
                            auxY = y-1;
                            break;
                    }
                } while(auxX < 0 || auxY < 0 || auxX > 29 || auxY > 29 || obstaculos[auxX][auxY] == 1);
                novaLocalizacao = new Localizacao(auxX, auxY);

            }

            return novaLocalizacao;
        }
    }
    
    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Localizacao.
     * @return true: se a localizacao é igual.
     *         false: caso contrario.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }else if(!(obj instanceof Localizacao)){
            return false;
        }else{
            Localizacao outro = (Localizacao) obj;
            return x == outro.x && y == outro.y;
        }
    }
    
    /**
     * @return A representacao da localizacao.
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    public static void setObstaculos(ArrayList<Obstaculo> o) {
        for (Obstaculo obstaculo: o) {
            int x = obstaculo.getLocalizacaoAtual().getX();
            int y = obstaculo.getLocalizacaoAtual().getY();

            obstaculos[x][y] = 1;
        }
    }
}
