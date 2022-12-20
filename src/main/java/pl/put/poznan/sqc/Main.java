package pl.put.poznan.sqc;

import pl.put.poznan.sqc.licznikKrokow.licznikKrokow;
import pl.put.poznan.sqc.licznikDecyzyjnych.licznikDecyzyjnych;
import pl.put.poznan.sqc.bezAktora.bezAktora;
import pl.put.poznan.sqc.ponumerujKroki.PonumerujKroki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            Krok step4_2 = new Krok("Dozorca Pod-krok 1-2", null);
                Krok step4_1_2 = new Krok("IF-krok 1-2", null);
                Krok step4_1_1 = new Krok("IF-krok 1-1", null);
            Krok step4_1 = new Krok("ELSE Krok 2", Arrays.asList(step4_1_1,step4_1_2));
        Krok step4 = new Krok("IF Krok 1", Arrays.asList(step4_1,step4_2));
            Krok step3_2 = new Krok("Bibliotekarz Pod-krok 1-2", null);
            Krok step3_1 = new Krok("IF-krok 1-1", null);
        Krok step3 = new Krok("Krok 3", Arrays.asList(step3_1, step3_2));
        Krok step2 = new Krok("System ELSE Krok 2", null);
        Krok step1 = new Krok("IF Krok 1", null);
        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3,step4));

        licznikKrokow licznikKrokow = new licznikKrokow();

// Przekazujemy go do metody accept scenariusza
        scenariusz.akceptuj(licznikKrokow);

// Pobieramy liczbe krokow
        int count = licznikKrokow.getCount();

// Wypisujemy liczbe krokow na ekran
        System.out.println("Liczba krokow w scenariuszu: " + count + "\n");

        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();

        scenariusz.akceptuj(licznikDecyzyjnych);

        int count2 = licznikDecyzyjnych.getCount();

        System.out.println("Liczba krokow ze slowami kluczowymi w scenariuszu: " + count2 + "\n");

        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());

        scenariusz.akceptuj(bezAktora);

        List<String> wynik = new ArrayList<>();
        wynik = bezAktora.getWynik();

        System.out.println("Lista krokow nie zaczynajacych sie od aktora:");
        if(wynik.size()!=0) {
            for (int i = 0; i < wynik.size(); i++) {
                System.out.println(wynik.get(i));
            }
        }
        System.out.println("");

        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        wynik = ponumerujKroki.getWynik();
        System.out.println("Scenariusz z ponumerowana lista krokow:" + "\n");
        if(wynik.size()!=0) {
            for (int i = 0; i < wynik.size(); i++) {
                System.out.println(wynik.get(i));
            }
        }
    }
}
