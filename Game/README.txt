NUME : Buzatu Dragos-Lucian 
GRUPA : 322 CC
GRAD DIFICULTATE : Mediu (Partea de terminal)
Timp alocat : ≅ 50 ore (80h daca luam in calcul primele 30ore gresite)


Implementare : 

Cele mai dificile parti ale temei au fost reprezentate de partea de inceput
(parsarea din json) si cea de sfarsit(apelarea claselor in Game, rezolvarea tuturor bug-urilor
si gasirea metodei de implementare a tuturor metodelor necesare din toate clasele). 

Legat de partea cu parsarea din json, am petrecut aproximativ 30ore parsand datele, dupa multe
tutoriale de pe youtube am reusit sa parsez toate datele , din pacate in string-uri si int-uri ,
ce s-au dovedit imposibil de utilizat, ulterior facand direct obiectele necesare in json. 

Cea mai dificila clasa de implementata fost Grid, unde am implementat 2 metode de generare pentru harti,
una pentru cea hardcodata iar alta dinamica , o metoda pentru afisarea hartii in 2 formate(vizitata/nevizitata)
precum si metodele de deplasare si setare celula.
 
Alte clase nu mi s-au parut prea dificil de implementat, intrucat din enuntul problemei reiesa exact ce trebuie
facut, cu anumite exceptii, de exemplu : metoda de utilizarePotiune ce se afla in interfata Potion mi se parea mult
mai firesc sa fie implementata de clasa Character deoarece acea clasa o utilizeaza.

In final, dupa ce am implementat toate metodele din restul claselor am inceput sa construiesc metoda run din Game. 
M-am folosit de Scanner si equals pentru a face toate verificarile din terminal, introduc datele parsate si in cazul
oricarei greseli(email inexistent/ parola gresita / caracter inexistent ) programul arunca o exceptie si se opreste.
Odata selectat caracterul corect, afisez harta nevizita. La absolut orice miscare(deplasare/cumparare shop/ atac ) 
exista si varianta P ce reprezinta varianta hardcodata ce trebuie implementata in acel moment. La fiecare celula 
nevizitata afisez povestea, verific tipul celulei si in functie de aceasta generez posibilitati de actiune. 
Shop-urile genereaza aleatoriu un numar de potiuni ( intre 2-4 , minim o potiune de mana si una de viata ) iar metoda 
hardcodata cumpara automat o potiune de mana si una viata, apoi lasand posibilitate jucatorului sa mai cumpere alta 
potiune(daca mai exista in shop) sau sa-l paraseasca. La batalia cu un inamic, varianta hardcodata foloseste cele 3
abilitati(am setat parametrii pentru mana si manaAbilitate special pentru a se putea utiliza toate 3) , cele 2 potiuni
cumparate iar apoi ataca normal. 
 
