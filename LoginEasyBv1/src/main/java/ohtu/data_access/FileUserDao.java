package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author joonaslaakkonen
 */
public class FileUserDao implements UserDao{

    private Scanner tiedosto;
    private FileWriter kirjoittaja;
    private ArrayList<User> lista;
    private File uusi;
    private String polku;
    
    public FileUserDao(String polku) throws FileNotFoundException, IOException {
        this.uusi = new File(polku);
        this.polku = polku;
        this.tiedosto = new Scanner(uusi);
        
        this.lista = new ArrayList<User>();
        while (this.tiedosto.hasNext()) {
            String[] rivi = this.tiedosto.nextLine().split(";");
            String nimi = rivi[0];
            String salasana = rivi[1];
            this.lista.add(new User(nimi, salasana));
        }
    } 

    @Override
    public List<User> listAll() {
        return lista;
    }

    @Override
    public User findByName(String name) {
        for (User apu : this.lista) {
            if (apu.getUsername().equals(name)) {
                return apu;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            this.kirjoittaja = new FileWriter(new File(this.polku));
        } catch (IOException ex) {
            System.out.println("Vituiks meni");
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String uusiLista = "";
        this.lista.add(user);
        try {
            for (User apu : this.lista){
                uusiLista += apu.getUsername() + ";" + apu.getPassword() + "\n";
            }
            this.kirjoittaja.append(uusiLista);
        } catch (IOException ex) {
            System.out.println("Kirjoittaminen meni vituiks");
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
