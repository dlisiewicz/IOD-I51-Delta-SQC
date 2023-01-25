package pl.put.poznan.sqc.bezAktora;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.Wizytator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class bezAktoraTest {


    @Test
    public void testAllSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bezAktora);
        List<String> wynik;
        wynik = bezAktora.getWynik();

        assertEquals(Arrays.asList("IF Krok 3","ELSE Krok 3-1"), wynik);
    }
    @Test
    public void testAllActors() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System ELSE Krok 2", null);
        Krok step3_1 = new Krok("Bibliotekarz Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("System Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bezAktora);

        assertEquals(Arrays.asList().isEmpty(), true);
    }
    @Test
    public void testNoActors() {
        Krok step1 = new Krok("Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3_1 = new Krok("Krok 3-1", null);
        Krok step3_2 = new Krok("Pod-krok 3-2", null);
        Krok step3 = new Krok("Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bezAktora);
        List<String> wynik;
        wynik = bezAktora.getWynik();

        assertEquals(Arrays.asList("Krok 1","ELSE Krok 2","Krok 3","Krok 3-1","Pod-krok 3-2"), wynik);
    }
    @Test
    public void testNoSystemActors() {
        Krok step1 = new Krok("Krok 1", null);
        Krok step2 = new Krok("Bibliotekarz Krok 2", null);
        Krok step3_1 = new Krok("Krok 3-1", null);
        Krok step3_2 = new Krok("Bibliotekarz Pod-krok 3-2", null);
        Krok step3 = new Krok("Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bezAktora);
        List<String> wynik;
        wynik = bezAktora.getWynik();

        assertEquals(Arrays.asList("Krok 1","Krok 3","Krok 3-1"), wynik);
    }
    @Test
    public void testNoNormalActors() {
        Krok step1 = new Krok("Krok 1", null);
        Krok step2 = new Krok("System Krok 2", null);
        Krok step3_1 = new Krok("Krok 3-1", null);
        Krok step3_2 = new Krok("System Pod-krok 3-2", null);
        Krok step3 = new Krok("Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz"), "System", Arrays.asList(step1, step2, step3));
        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bezAktora);
        List<String> wynik;
        wynik = bezAktora.getWynik();

        assertEquals(Arrays.asList("Krok 1","Krok 3","Krok 3-1"), wynik);
    }

    @Test
    void testMocksGetResult() {
        Scenariusz mock = mock(Scenariusz.class);

        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        when(mock.getAktorzy()).thenReturn(Arrays.asList("Bibliotekarz","Dozorca"));
        when(mock.getAktorSystemowy()).thenReturn("System");

        bezAktora bezAktora = new bezAktora(mock.getAktorzy(), mock.getAktorSystemowy());

        assertEquals(Arrays.asList("Bibliotekarz","Dozorca"), bezAktora.getAktorzy());

    }

}