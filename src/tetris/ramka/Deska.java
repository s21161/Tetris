package tetris.ramka;
import tetris.postacie.Postac;
import tetris.postacie.Epostac;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JLabel;
import tetris.klocki.*;
import tetris.pudla.Pudlo;
import tetris.pudla.Spudlo;
import java.util.ArrayList;
public class Deska extends JLabel {

    private int wiekszosc = 12, rozmiar = 20;
    Spudlo mKloc;
    Pudlo mPudlo;
    public ArrayList<Spudlo> mAktywny = new ArrayList<Spudlo>();
    public ArrayList<Spudlo> mPudelka= new ArrayList<Spudlo>();
    public Postac akPostac = new Postac();
    public Postac akPostac2 = new Postac();

    public Deska() {
        setBounds(0, 0, 300, 450);//rozmiar okienka
        setLayout(null);
        setVisible(true);
        plansza();
        this.requestFocus();
    }

    private void plansza()//w tej metodzie ustawiamy rozmiary kwadracików gdzie spadają klocki
    {
        for (int i = 0; i < wiekszosc; i++) {
            for (int j = 0; j < rozmiar; j++) {
                mPudlo = new Pudlo(20 * i, 20 * j);
                add(mPudlo);
            }
        }
    }

    /*
     * Dodaje nowy kształt do planszy
     */
    public void dodaj(Epostac s) {
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

        akPostac = kloc;
        mAktywny.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kloc.tablica2[j][i] == 1) {
                    mKloc = new Spudlo(i * 20 + 100, j * 20 - 20);
                    add(mKloc, new Integer(1), 0);
                    mAktywny.add(mKloc);
                }
            }
        }
    }

    /*
     * Dodaje nowy kształt do podanej pozycji planszy
     */
    public boolean dodaj(Postac s, int x, int y) {
        int x2, y2;
        usunAkt();
        mAktywny.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (s.tablica2[j][i] == 1) {
                    for (Spudlo pud : mPudelka) {
                        x2 = pud.getX();
                        y2 = pud.getY();
                        if (((i * 20 + x == x2) && (j * 20 + y == y2)) || (x >= wiekszosc * 20) || (x<0) || (y>= rozmiar * 20)) {
                            usunAkt();
                            mAktywny.clear();
                            return false;
                        }
                    }
                    mKloc = new Spudlo(i * 20 + x, j * 20 + y);
                    add(mKloc, new Integer(1), 0);
                    mAktywny.add(mKloc);
                }
            }
        }
        return true;
    }

    /*
     * Tworzy losowe kształty.
     */
    public Epostac losujk() {
        Epostac e;
        Random r = new Random();
        int i = r.nextInt(7);
        switch (i) {
            case 0:
                e = Epostac.prostokat;
                break;
            case 1:
                e = Epostac.schodek;
                break;
            case 2:
                e = Epostac.schodek2;
                break;
            case 3:
                e = Epostac.kwadrat;
                break;
            case 4:
                e = Epostac.klocek_l;
                break;
            case 5:
                e = Epostac.klocek_l2;
                break;
            case 6:
                e = Epostac.klocek_t;
                break;
            default:
                e = Epostac.kwadrat;
                break;
        }
        return e;
    }

    /*
     * usun jakis kształt
     */
    public void usunAkt() {
        for (Spudlo pud : mAktywny) {
            remove(pud);
        }

        mAktywny.clear();

        repaint();
        validate();
    }

    /*
     * Przewija klocek w dół
     */
    public void dol() {
        int x, y;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            pud.setLocation(x, y + 20);
        }
    }

    /*
     * Przesuwa klocek w prawo
     */
    public void przesunPrawo() {
        int x, y;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            pud.setLocation(x + 20, y);
        }
    }

    /*
     * Przesuwa klocek w lewo
     */
    public void przesunLewo() {
        int x, y;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            pud.setLocation(x - 20, y);
        }
    }

    /*
     * Sprawdza czy da się dalej w lewo
     */
    public boolean lewokont() {
        int x, y, x2, y2;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            if (x <= 0) {
                return false;
            }

            for (Spudlo pud2 : mPudelka) {
                x2 = pud2.getX();
                y2 = pud2.getY();

                if ((x == x2 + 20) && (y == y2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Prawa strona
     */
    public boolean prawokont() {
        int x, y, x2, y2;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            if (x >= (wiekszosc - 1) * 20) {
                return false;
            }

            for (Spudlo pud2 : mPudelka) {
                x2 = pud2.getX();
                y2 = pud2.getY();

                if ((x + 20 == x2) && (y == y2)) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * umozliwia poruszanie sie
     * sprawia ze kloce spadaja na podloge
     */
    public boolean dolkont() {
        int x, y, x1, y1;
        for (Spudlo pud : mAktywny) {
            x = pud.getX();
            y = pud.getY();

            if (y >= (rozmiar - 1) * 20) {
                mPudelka.addAll(mAktywny);
                mAktywny.clear();
                return false;
            }

            for (Spudlo k2 : mPudelka) {
                x1 = k2.getX();
                y1 = k2.getY();

                if ((y >= y1 - 20) && (x == x1)) {
                    mPudelka.addAll(mAktywny);
                    mAktywny.clear();
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Obraca klocek
     */
    public void obrot() {
        int x, y;


        //lokalizacja lewego górnego punktu
        x = mAktywny.get(0).getX();
        y = mAktywny.get(0).getY();

        akPostac2 = akPostac;
        akPostac.eksportTablicy();
        if (!dodaj(akPostac, x, y)) {
            dodaj(akPostac2, x, y);
        }

        //System.out.println(x + " - " + y);
    }

    /*
     * Metoda sprawdzenia, czy gra się skończyła
     */
    public boolean koniecGry() {
        for (Spudlo pud : mPudelka) {
            if (pud.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * konwertuje z Epostac do postac
     */
    public Postac Epostacpostac(Epostac s) {
        Postac kloc;
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
            default:
                kloc = new Klocek_kw();
                break;
        }
        return kloc;
    }

    /*
     * Sprawdza wszystkie wiersze, usuwa pełne wiersze
     */
    public int pelny() {
        int b = 0;
        int[] ost = new int[20];
        for (Spudlo pud : mPudelka) {
            if (pud.getY() > 0) {
                ost[pud.getY() / 20]++;
            }
        }

        for (int i = 0; i < 20; i++) {
            if (ost[i] == 12) {
                usun(i);
                b++;
            }
        }

        return b;
    }

    /*
     * Usuwa podaną linię, przewija tę powyżej.
     */
    public void usun(int i) {
        int x, y;

        for (Spudlo pud : mPudelka) {
            if (pud.getY() == i * 20) {
                remove(pud);
            }
        }

        for (Iterator it = mPudelka.iterator(); it.hasNext();) {
            Spudlo s = (Spudlo) it.next();
            if (s.getY() == i * 20) {
                it.remove();
            }
        }

        for (Spudlo k : mPudelka) {
            if (k.getY() < i * 20) {
                x = k.getX();
                y = k.getY();

                k.setLocation(x, y + 20);
            }
        }

        repaint();
        validate();
    }
}