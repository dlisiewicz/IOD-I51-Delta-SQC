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
        Krok step1 = new Krok("IF Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok subStep1 = new Krok("IF-krok 1-1", null);
        Krok subStep2 = new Krok("Pod-krok 1-2", null);
        Krok step3 = new Krok("Krok 3", Arrays.asList(subStep1, subStep2));
        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));

        licznikKrokow licznikKrokow = new licznikKrokow();

// Przekazujemy go do metody accept scenariusza
        scenariusz.akceptuj(licznikKrokow);

// Pobieramy liczbę kroków
        int count = licznikKrokow.getCount();

// Wypisujemy liczbę kroków na ekran
        System.out.println("Liczba kroków w scenariuszu: " + count + "\n");

        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();

        scenariusz.akceptuj(licznikDecyzyjnych);

        int count2 = licznikDecyzyjnych.getCount();

        System.out.println("Liczba kroków ze słowami kluczowymi w scenariuszu: " + count2 + "\n");

        bezAktora bezAktora = new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());

        scenariusz.akceptuj(bezAktora);

        List<String> wynik = new ArrayList<>();
        wynik = bezAktora.getWynik();

        System.out.println("Lista kroków nie zaczynających się od aktora:");
        if(wynik.size()!=0) {
            for (int i = 0; i < wynik.size(); i++) {
                System.out.println(wynik.get(i));
            }
        }
        System.out.println("");

        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz);
        scenariusz.akceptuj(ponumerujKroki);
        wynik = ponumerujKroki.getWynik();
        System.out.println("Scenariusz z ponumerowaną listą kroków:" + "\n");
        if(wynik.size()!=0) {
            for (int i = 0; i < wynik.size(); i++) {
                System.out.println(wynik.get(i));
            }
        }
    }
}
