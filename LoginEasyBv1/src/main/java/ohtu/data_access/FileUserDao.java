//package ohtu.data_access;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import ohtu.domain.User;
//
///**
// *
// * @author joonaslaakkonen
// */
//public class FileUserDao implements UserDao{
//    
//    private List<User> users;
//    private Scanner tiedosto;
//    private FileWriter kirjoittaja;
//
//    public FileUserDao(File lista) throws FileNotFoundException, IOException {
//        this.tiedosto = new Scanner(lista);
//        this.kirjoittaja = new FileWriter(lista);
////        users = new ArrayList<User>();
////        users.add(new User("pekka", "akkep"));
//    } 
//
//    @Override
//    public List<User> listAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public User findByName(String name) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void add(User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
