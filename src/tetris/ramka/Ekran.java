package tetris.ramka;

import tetris.postacie.Postac2;
import tetris.postacie.Postac;
import tetris.postacie.Epostac;
import tetris.klocki.Klocek_T;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Ekran extends JFrame {

    public boolean gramy = false; // Zmienna, którą rejestrujemy, niezależnie od tego, czy gra się gra
    public Timer mTimer = null; // zegar.
    public int mCzas = 500, kCzas = 500, przycisk = 0, punkty = 0, level = 1, st;
    public Postac2 mPostac;
    public Deska mDeska;
    public Postac postka = new Klocek_T();
    public Epostac fi, fi2;
    public JButton mButton;
    public JLabel lblPunkty, lblLevel;

    public Ekran() {
        super("Tetris");
        setBounds(0, 0, 450, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        mDeska = new Deska();
        add(mDeska);
        mPostac = new Postac2();
        add(mPostac);

        setVisible(true);

        /*
         * Punkty
         */

        lblPunkty = new JLabel("Punkty : 0");
        lblPunkty.setBounds(270, 200, 100, 30);
        add(lblPunkty);

        /*
         * Level
         */

        lblLevel = new JLabel("Level : 1");
        lblLevel.setBounds(270, 170, 100, 30);
        add(lblLevel);

        /*
         * Przycisk Graj
         */

        mButton = new JButton("Graj");
        mButton.setBounds(270, 250, 100, 30);
        add(mButton);
        mButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                switch (przycisk) {
                    // Graj
                    case 0:
                        fi = mDeska.losujk(); // pierwsza figura
                        fi2 = mDeska.losujk(); // nastepna figura
                        mPostac.setPostac(fi2);
                        mDeska.dodaj(fi);

                        mTimer.start();
                        mButton.setText("Pauza");
                        przycisk = 1;
                        break;
                    // Pauza/zatrzymaj
                    case 1:
                        mTimer.stop();
                        mButton.setText("Graj dalej");
                        przycisk = 2;
                        break;
                    // pauza
                    case 2:
                        mTimer.start();
                        mButton.setText("Pauza");
                        przycisk = 1;
                        break;
                }


                //mButton.setEnabled(false);
            }
        });

        mTimer = new Timer(mCzas, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (mDeska.dolkont()) {
                    mDeska.dol();
                } else {
                    fi = fi2;

                    st = mDeska.pelny();
                    if (st > 0) {
                        punkty += st * 100 + ((st - 1) * 10);
                        lblPunkty.setText("Punkty : " + punkty);

                        level = punkty / 1000;

                        lblLevel.setText("Level : " + level);
                        mCzas = kCzas - level * 70;
                        mTimer.setDelay(mCzas);
                    }
                    mDeska.dodaj(fi2);

                    fi2 = mDeska.losujk();
                    mPostac.setPostac(fi2);
                    if (mDeska.koniecGry()) {
                        mDeska.usunAkt();
                        JOptionPane.showMessageDialog(rootPane, "Koniec gry!");
                        mTimer.stop();
                    }
                }
            }
        });

        mButton.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (mDeska.prawokont()) {
                        mDeska.przesunPrawo();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (mDeska.lewokont()) {
                        mDeska.przesunLewo();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    mDeska.obrot();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mTimer.setDelay(50);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mTimer.setDelay(mCzas);
                }
            }
        });
    }
}