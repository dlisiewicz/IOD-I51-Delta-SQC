package pl.put.poznan.sqc.ponumerujKroki;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.Wizytator;

import java.util.ArrayList;
import java.util.List;

/**
 * Numeruje kroki scenariuszy i podscenariuszy
 */

public class PonumerujKroki implements Wizytator {
    private List<String> wynik = new ArrayList<>();
    private List<Integer> indeksy = new ArrayList<>();
    private List<String> aktorzy;
    private String aktorSystemowy;
    private String tytul;

    /**
     * inicjuje klase, dodaje do wyniku naglowek scenariusza (tytul oraz aktorow)
     * @param scenariusz scenariusz, ktorego kroki maja zostac ponumerowane
     */
    public PonumerujKroki(Scenariusz scenariusz){
        wynik = new ArrayList<>();
        indeksy = new ArrayList<>();
        indeksy.add(0);
        aktorzy=scenariusz.getAktorzy();
        aktorSystemowy=scenariusz.getAktorSystemowy();
        tytul=scenariusz.getTytul();
        wynik.add("Tytul: "+tytul);
        String pom="Aktorzy:";
        for(String aktor : aktorzy){
            if(pom.contains(": "))
            {
                pom=pom+", "+aktor;
            }else {
                pom=pom+" "+aktor;
            }
        }
        wynik.add(pom);
        wynik.add("Aktor systemowy: "+aktorSystemowy);
    }

    /**
     *Dodaje dany krok do ponumerowanego scenariusza z jego obecnym indeksem w momencie wywolania procedury, oraz wywoluje sie dla wszystkich krokow podscenariusza, jezeli taki istnieje.
     * @param krok Krok scenariusza
     */
    @Override
    public void odwiedz(Krok krok) {
        indeksy.set(indeksy.size()-1,indeksy.get(indeksy.size()-1)+1);
        String pom="";
        for(Integer i :indeksy){
            pom+=i+".";
        }
        pom+=" ";
        wynik.add(pom+krok.getTresc());
        if (krok.podKrok != null) {
            indeksy.add(0);
            for (Krok podKrok : krok.podKrok) {
                podKrok.akceptuj(this);
            }
            indeksy.remove(indeksy.size()-1);
        }

    }

    /**
     *Zwraca ponumerowany scenariusz
     * @return Scenariusz w formie listy typu string
     */
    public List<String> getWynik() {
        return wynik;
    }
}
