package tetris.pudla;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
public class Pudlo extends JLabel {

    public Pudlo(int X, int Y) {
        setBounds(X, Y, 20, 20);
        setLayout(null);
    }


    public Color kolor = Color.GRAY;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;     //tworzy obiekt

        Rectangle2D rect = new Rectangle2D.Double(0, 0, 20, 20);    //rozmiary kwadracików na planszy i odleglosc

        g2.setColor(kolor); // pudelko
        g2.fill(rect);

        g2.setColor(Color.black); // kolor krawędzi
        g2.draw(rect);
    }
}