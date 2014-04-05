package ohtu.verkkokauppa;

import org.junit.After;
import static org.mockito.Mockito.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaslaakkonen
 */
public class KauppaTest {
    Kauppa kauppa;
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void tilisiirronKutsuminenToimiiOikein() {
        kauppa.aloitaAsiointi();
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        kauppa.lisaaKoriin(1);
        when(viite.uusi()).thenReturn(42);
        kauppa.tilimaksu("Pasi", "12345");

        verify(pankki, times(1)).tilisiirto("Pasi", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void tilisiirtoToimiiOikeinKahdellaEriTuotteella() {
        kauppa.aloitaAsiointi();
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "olut", 8));
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        when(viite.uusi()).thenReturn(20);
        kauppa.tilimaksu("Pasi", "12345");
        
        verify(pankki, times(1)).tilisiirto("Pasi", 20, "12345", "33333-44455", 13);
    }
    
    @Test
    public void tilisiirtoToimiiOikeinKahdellaSamallaTuotteella() {
        kauppa.aloitaAsiointi();
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        when(viite.uusi()).thenReturn(25);
        kauppa.tilimaksu("Pasi", "12345");
        
        verify(pankki, times(1)).tilisiirto("Pasi", 25, "12345", "33333-44455", 10);
    }
    
    @Test
    public void tilisiirtoToimiiOikeinKunLisataanKoriinMyosTuoteJokaOnLoppunut(){
        kauppa.aloitaAsiointi();
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "humppa", 5));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(0);
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        when(viite.uusi()).thenReturn(25);
        kauppa.tilimaksu("Pasi", "12345");
        
        verify(pankki, times(1)).tilisiirto("Pasi", 25, "12345", "33333-44455", 5);
    }
    
    @Test
    public void aloitaAsiointiNollaaAiemmanOstoskorin(){
        kauppa.aloitaAsiointi();
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(1);
        kauppa.lisaaKoriin(1);
        
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("Pasi", "12345");
        verify(pankki, times(1)).tilisiirto("Pasi", 0, "12345", "33333-44455", 0);       
    }
    
    @Test
    public void kauppaPyytaaAinaUudenViitenumeronUudelleTilitapahtumalle(){
        kauppa.aloitaAsiointi();
        when(viite.uusi()).
            thenReturn(2).
            thenReturn(3).
            thenReturn(4);
        
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("Sami", "12345");
        
        verify(pankki, times(1)).tilisiirto("Sami", 2,"12345", "33333-44455", 0);
        
        kauppa.tilimaksu("Sami", "12345");
        
        verify(pankki, times(1)).tilisiirto("Sami", 3,"12345", "33333-44455", 0);
        
        kauppa.tilimaksu("Sami", "12345");
        
        verify(pankki, times(1)).tilisiirto("Sami", 4,"12345", "33333-44455", 0);
    }
    
    @Test
    public void tuotteenPoistoKoristaToimiiOikein() {
        kauppa.aloitaAsiointi();
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "humppa", 5));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.saldo(2)).thenReturn(2);
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.poistaKorista(1);
        kauppa.lisaaKoriin(2);
        when(viite.uusi()).thenReturn(25);
        kauppa.tilimaksu("Pasi", "12345");
        
        verify(pankki, times(1)).tilisiirto("Pasi", 25, "12345", "33333-44455", 15);        
    }
}