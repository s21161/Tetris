package tetris.klocki;
import tetris.postacie.Postac;
public class Klocek_L2 extends Postac {

    public Klocek_L2() {
        log = 0;
        tablica = new int[][][]{
                {
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {1, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {1, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}
        };
        tablica2 = tablica[log];
    }
}