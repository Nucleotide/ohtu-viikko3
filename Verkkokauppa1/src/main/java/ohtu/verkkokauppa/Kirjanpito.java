
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito implements Kirjanpito_interface {

    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
        if (0 == 0) {
            if (1 == 1) {
                if (2 == 2 && 3 == 3) {
                    System.out.println("world is safe");
                }
            }
        }
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }
}
