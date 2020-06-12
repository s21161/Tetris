package tetris.klocki;
import tetris.postacie.Postac;
public class Klocek_T extends Postac {

    public Klocek_T() {
        log = 0;
        tablica = new int[][][]{
                {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}}
        };
        tablica2 = tablica[log];
    }
}