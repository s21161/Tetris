package tetris.postacie;
public class Postac {
    public int[][] tablica2; // = new int[4][4];
    public int[][][] tablica; // kształt obrócony
    public int log; //kąt kształtu, 0-3

    public void eksportTablicy() {
        tablica2 = tablica[(log++) % 4];
    }
}