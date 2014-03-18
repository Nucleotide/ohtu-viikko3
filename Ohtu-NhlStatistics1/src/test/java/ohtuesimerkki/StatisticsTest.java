/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaslaakkonen
 */
public class StatisticsTest {
    
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    @Before
    public void setUp() {
        stats = new Statistics(this.readerStub);
    }

    @Test
    public void haePelaajaNimenPerusteella() {
        assertNotNull(this.stats.search("Semenko"));       
    }
    
    @Test
    public void haetaanPelaajaaJotaEiOle() {
        assertNull(this.stats.search("Selanne"));
    }
    
    @Test
    public void konstruktoriSaaListanReaderiltaJokaEiOleTyhja() {
        assertFalse(this.readerStub.getPlayers().isEmpty());
    }
    
    @Test
    public void pelaajaLisataanJoukkueenListaan() {
        List<Player> team = this.stats.team("PIT");
        assertFalse(team.isEmpty());
    }
    
    @Test
    public void joukkueenListaOnTyhjaJosPelaajienJoukkueetEivatVastaaJoukkueenNimea() {
        List<Player> team = this.stats.team("HON");
        assertTrue(team.isEmpty());
    }
    
    @Test
    public void saadaanTietoParhaastaPistemiehesta() {
        List<Player> points = this.stats.topScorers(0);
        Player leader = points.get(0);
        assertTrue(leader.getName().equals("Gretzky"));      
    }
    @Test
    public void saadaanHalutunKokoinenLista() {
        int monta = 3;
        List<Player> points = this.stats.topScorers(monta);
        assertEquals(points.size(), monta+1);
    }
    
}
