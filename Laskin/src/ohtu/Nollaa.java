/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author joonaslaakkonen
 */
public class Nollaa implements Komento{
    
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private int edellinen;

    Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        this.edellinen = sovellus.tulos();
        this.sovellus.nollaa();
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }

    @Override
    public void peru() {
        tuloskentta.setText("" + this.edellinen);
        this.sovellus.setTulos(edellinen);
    }
    
}
