package tetris.postacie;
import tetris.klocki.*;
import tetris.pudla.Pudlo;
import tetris.pudla.Spudlo;
import java.util.ArrayList;
import javax.swing.JLabel;
public class Postac2 extends JLabel {

    Pudlo gPudlo;
    Spudlo mKloc;
    public ArrayList<Spudlo> mListe = new ArrayList<Spudlo>();

    public Postac2() {
        setBounds(300, 45, 100, 100);       //kordy okienka
        setLayout(null);
        setVisible(true);
        okienko();
    }

    private void okienko()//W tej metodzie 5 pudeł o 5 długościach umieszcza się na naszej czerwonej etykiecie.
    // Zmniejszyłem go do 4, ponieważ kształty mają maksymalnie 4 jednostki
    {
        for (int i = 0; i < 4; i++) {                       //pentla tworzy okienko
            for (int j = 0; j < 4; j++) {
                gPudlo = new Pudlo(20 * i, 20 * j);     //rozmiar/odstepy kwadracików na planszy
                add(gPudlo);
            }
        }
    }

    /*
     * W tej metodzie pokazujemy przyszły kształt.
     */
    public void setPostac(Epostac s) {
        Postac kloc = null;
        switch (s) {
            case prostokat:
                kloc = new Klocek_pr();
                break;
            case kwadrat:
                kloc = new Klocek_kw();
                break;
            case klocek_t:
                kloc = new Klocek_T();
                break;
            case schodek:
                kloc = new Klocek_sh();
                break;
            case klocek_l:
                kloc = new Klocek_L();
                break;
            case klocek_l2:
                kloc = new Klocek_L2();
                break;
            case schodek2:
                kloc = new Klocek_sh2();
                break;
        }
        removeAll();
        repaint();
        validate();
        okienko();
        mListe.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kloc.tablica2[j][i] == 1) {
                    mKloc = new Spudlo(i * 20, j * 20);
                    add(mKloc, new Integer(1), 0);
                    mListe.add(mKloc);
                }
            }
        }

    }
}