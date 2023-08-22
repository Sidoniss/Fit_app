# Fit_app
Jest to aplikacja treningo-dietetyczna. Funkcje obsługujące logowanie i przechowywanie obrazów zakładają symulacje obsługi w chmurze. 

#Funkcjonalności aplikacji
Aplikacja posiada wirtualnego trenera który obsługuje kilka modeli treningu przekazywanego przez plik JSON.
Zaimplementowano też wyszukiwarkę przepisów z filtrami oraz wyszukiwarką tekstową. Przepisy są pobierane analogicznie do treningów z pliku JSON.
Zaimplementowano model logowania,rejestracji,odzyskiwania hasła z użyciem tokena przychodzącego mailowo(wymagany mailtrap),zmiana starego hasła na nowe,wyświetlenia informacji o koncie.
Aplikacja zapamiętuje logowanie użytkownika, można się wylogować by przejść na inne konto.

# JSON
przekazywanie do wewnętrznej bazy danych treningów i przepisów z pliku JSON odbywa się ze względu na łatwość aktualizacji. Przy aktualizacji aplikacji można przekazać plik z nowymi przepisami i treningami,
dzięki czemu nie ma potrzeby implementowania całego mechanizmu chmurowego do przekazywania ich na bieżąco. Poprawia to szybkość. Założono, że mechanizmy logowania oraz obrazy dotyczące etapów treningu i przepisów będą przechowywane w chmurze,
dlatego też wszystkie obrazy są pobierane z url. Dzięki temu ogranicza się zajmowanie miejsca na urządzeniu docelowym.

Dodawanie przepisu kulinarneg wymaga:
-tytułu
-czasu gotowania
-opisu
-składników które są zawarte w formie listy, każdy składnik musi mieć podane
  *nazwę
  *ilość

Dodawanie treningu wymaga:
-tytułu
-przybliżonego czasu wykonania w minutach (w formie informacyjnej)
-kategorii
  * 1 - ramiona
  * 2 - nogi
  * 3 - brzuch
  * 4 - kardio
-poziomu trudności
  * 1 - łatwy
  * 2 - średni
  * 3 - trudny
-listy etapów, każdy składa się z
  *opisu
  *url obrazka
  *czasu (jeśli to jest typ treningu czasowego np. zrób deskę przez 10 sekund, jeśli to nie jest taki tryb, można podać 0)
  *typu treningu gdzie:
    > 0 - trening czasowy który przechodzi dalej po minięciu określonego czasu
    > 1 - trening po którego wykonaniu trzeba kliknąć dalej by kontynuować

  Wszystkie pliki JSON znajdują się w folderze assets
