package tetris.pudla;
import tetris.pudla.Pudlo;

import java.awt.Color;
public class Spudlo extends Pudlo {
    public Spudlo(int X, int Y) {
        super(X, Y);
        super.kolor = Color.BLUE;
    }

    public void remove()
    {
        remove(this);
        validate();
    }
}