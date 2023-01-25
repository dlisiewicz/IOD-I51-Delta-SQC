package pl.put.poznan.sqc.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.bezAktora.bezAktora;
import pl.put.poznan.sqc.licznikDecyzyjnych.licznikDecyzyjnych;
import pl.put.poznan.sqc.licznikKrokow.licznikKrokow;
import pl.put.poznan.sqc.ponumerujKroki.PonumerujKroki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {
    Scenariusz scenariusz;

    @RequestMapping(value = "/initiate",method = RequestMethod.GET, produces = "application/json")
        public void post(@RequestParam(value = "tytul") String tytul,
                @RequestParam(value = "aktorzy") List<String> aktorzy,
                @RequestParam(value = "systemowy") String systemowy,
                @RequestParam(value = "kroki_pom") List<Integer> kroki_pom,
                @RequestParam(value = "kroki") List<String> kroki){

        // log the parameters
        System.out.println(tytul);
        System.out.println(aktorzy);
        System.out.println(systemowy);
        System.out.println(kroki_pom);
        System.out.println(kroki);
        int n=kroki.size();
        List<Krok> kroki_final = new ArrayList<Krok>();
        List<Krok> pom_list = new ArrayList<Krok>();
        for(int i=0;i<n;i++)
        {
            Krok pom = new Krok(kroki.get(i),new ArrayList<Krok>());
            pom_list.add(pom);
        }
        for(int i=n-1;i>=0;i--)
        {
            int j=kroki_pom.get(i);
            if(j==-1){
                kroki_final.add(pom_list.get(i));
            }else
            {
                pom_list.get(j).dodajPodKrok(0,pom_list.get(i));
            }
        }
        scenariusz = new Scenariusz(tytul,aktorzy,systemowy,kroki_final);
    }

    @RequestMapping(value = "/ponumerujKroki",method = RequestMethod.GET, produces = "application/json")
    public String ponumeruj(){
        PonumerujKroki ponumerujKroki=new PonumerujKroki(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy(), scenariusz.getTytul());
        scenariusz.akceptuj(ponumerujKroki);
        List<String> wyniki =ponumerujKroki.getWynik();
        String odp=wyniki.get(0);
        for(int i=1;i<wyniki.size();i++)
        {
            odp+="\n"+wyniki.get(i);
        }
        System.out.println(odp);
        return odp;
    }
    @RequestMapping(value = "/bezAktora",method = RequestMethod.GET, produces = "application/json")
    public String bezAktora(){
        bezAktora bez_aktora=new bezAktora(scenariusz.getAktorzy(), scenariusz.getAktorSystemowy());
        scenariusz.akceptuj(bez_aktora);
        List<String> wyniki =bez_aktora.getWynik();
        String odp=wyniki.get(0);
        for(int i=1;i<wyniki.size();i++)
        {
            odp+="\n"+wyniki.get(i);
        }
        System.out.println(odp);
        return odp;
    }
    @RequestMapping(value = "/licznikDecyzyjnych",method = RequestMethod.GET, produces = "application/json")
    public Integer decyzyjne(){
        licznikDecyzyjnych licznik = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznik);
        return licznik.getCount();
    }

    @RequestMapping(value = "/licznikKrokow",method = RequestMethod.GET, produces = "application/json")
    public Integer licznik_Krokow() {
        licznikKrokow licznik = new licznikKrokow();
        scenariusz.akceptuj(licznik);
        return licznik.getCount();
    }
}
