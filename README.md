# Scenario Quality Checker (SQC)
Dla analityków dokumentujących wymagania funkcjonalne za pomocą scenariuszy nasza aplikacja SQC dostarczy informacji ilościowych oraz umożliwi wykrywanie problemów w wymaganiach funkcjonalnych zapisanych w postaci scenariuszy. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

Notacja scenariuszy do wrzucenia do Readme.md projektu:
• Scenariusz zawiera nagłówek określający jego tytuł i aktorów (zewnętrznych oraz system)
• Scenariusz składa się z kroków (każdy krok zawiera tekst)
• Kroki mogą zawierać pod-scenariusze (dowolny poziom zagłębień)
• Kroki mogą się zaczynać od słów kluczowych: IF, ELSE, FOR EACH

Przykład: <br />
Tytuł: Dodanie książki <br />
Aktorzy:  Bibliotekarz <br />
Aktor systemowy: System <br />

* Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
* Wyświetla się formularz.
* Bibliotekarz podaje dane książki.
* IF: Bibliotekarz pragnie dodać egzemplarze książki
    * Bibliotekarz wybiera opcję definiowania egzemplarzy
    * System prezentuje zdefiniowane egzemplarze
    * FOR EACH egzemplarz:
        * Bibliotekarz wybiera opcję dodania egzemplarza 
        * System prosi o podanie danych egzemplarza
        * Bibliotekarz podaje dane egzemplarza i zatwierdza.
        * System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
* Bibliotekarz zatwierdza dodanie książki.
* System informuje o poprawnym dodaniu książki.

# Budowanie z wykorzystaniem Maven'a
## Automatyczne budowanie (kompilacja, stworzenie paczki do dystrybucji - . jar)
`mvn --batch-mode --update-snapshots verify` <br>
<br>
paczka .jar generuje się w \target 
## Generowanie javadoc 12
`mvn javadoc:javadoc` <br>
<br>
javadoc generuje się w \target\site\apidocs

