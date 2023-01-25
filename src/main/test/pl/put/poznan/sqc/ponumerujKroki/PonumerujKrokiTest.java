package pl.put.poznan.sqc.ponumerujKroki;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.licznikKrokow.licznikKrokow;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PonumerujKrokiTest {
    @Test
    public void testAllSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wynik;
        wynik = ponumerujKroki.getWynik();

        assertEquals(Arrays.asList("Tytul: Scenariusz","Aktorzy: Bibliotekarz, Dozorca","Aktor systemowy: System", "1. Bibliotekarz Krok 1",
        "2. ELSE Krok 2", "3. IF Krok 3", "3.1. ELSE Krok 3-1", "3.2. Dozorca Pod-krok 3-2"), wynik);
    }
    @Test
    public void testNoSubsteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3 = new Krok("IF Krok 3", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wynik;
        wynik = ponumerujKroki.getWynik();

        assertEquals(Arrays.asList("Tytul: Scenariusz","Aktorzy: Bibliotekarz, Dozorca","Aktor systemowy: System", "1. Bibliotekarz Krok 1",
                "2. ELSE Krok 2", "3. IF Krok 3"), wynik);
    }
    @Test
    public void testSubsteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System Krok 2", null);
        Krok step3_1 = new Krok("FOR EACH Krok 3-1", null);
        Krok step3_2_1_1 = new Krok("ELSE Pod-krok 3-2", null);
        Krok step3_2_1 = new Krok("Dozorca Pod-krok 3-2", Arrays.asList(step3_2_1_1));
        Krok step3_2 = new Krok("IF Pod-krok 3-2", Arrays.asList(step3_2_1));
        Krok step3 = new Krok("System Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wynik;
        wynik = ponumerujKroki.getWynik();

        assertEquals(Arrays.asList("Tytul: Scenariusz","Aktorzy: Bibliotekarz, Dozorca","Aktor systemowy: System", "1. Bibliotekarz Krok 1",
                "2. System Krok 2", "3. System Krok 3", "3.1. FOR EACH Krok 3-1", "3.2. IF Pod-krok 3-2", "3.2.1. Dozorca Pod-krok 3-2", "3.2.1.1. ELSE Pod-krok 3-2"), wynik);
    }
    @Test
    public void testOneStep() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1));
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wynik;
        wynik = ponumerujKroki.getWynik();

        assertEquals(Arrays.asList("Tytul: Scenariusz","Aktorzy: Bibliotekarz, Dozorca","Aktor systemowy: System", "1. Bibliotekarz Krok 1"), wynik);
    }
    @Test
    public void testMoreActors() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca", "Dozorca1", "Dozorca2"), "System", Arrays.asList(step1));
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wynik;
        wynik = ponumerujKroki.getWynik();

        assertEquals(Arrays.asList("Tytul: Scenariusz","Aktorzy: Bibliotekarz, Dozorca, Dozorca1, Dozorca2","Aktor systemowy: System", "1. Bibliotekarz Krok 1"), wynik);
    }


}