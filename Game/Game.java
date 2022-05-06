import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Game {

    public List<Account> accountList;
    public Map<Cell.cellenum,List<String>> storyMap;
    private static Game init = null;

    private Game(){
        accountList = new ArrayList<>();
        storyMap = new HashMap<>();
        List<String> storyEmpty = new ArrayList<>();
        List<String> storyEnemy = new ArrayList<>();
        List<String> storyShop = new ArrayList<>();
        List<String> storyFinish = new ArrayList<>();
        storyMap.put(Cell.cellenum.EMPTY, storyEmpty);
        storyMap.put(Cell.cellenum.ENEMY, storyEnemy);
        storyMap.put(Cell.cellenum.SHOP, storyShop);
        storyMap.put(Cell.cellenum.FINISH, storyFinish);
    }

    public static Game Init(){
        if (init == null)
            init = new Game();
        return init;

    }

    public void afisarePoveste(Cell celulaCurenta) {
        Random random = new Random();
        if (celulaCurenta.visitedCell == false) {
            if (celulaCurenta.tipCelula == Cell.cellenum.EMPTY) {
                int alegereEmpty = random.nextInt((Game.Init().storyMap.get(Cell.cellenum.EMPTY).size()));
                System.out.println(Game.Init().storyMap.get(Cell.cellenum.EMPTY).get(alegereEmpty));
                Game.Init().storyMap.get(Cell.cellenum.EMPTY).remove(alegereEmpty);
            }
            if (celulaCurenta.tipCelula == Cell.cellenum.ENEMY) {
                int alegereEnemy = random.nextInt(Game.Init().storyMap.get(Cell.cellenum.ENEMY).size());
                System.out.println(Game.Init().storyMap.get(Cell.cellenum.ENEMY).get(alegereEnemy));
                Game.Init().storyMap.get(Cell.cellenum.ENEMY).remove(alegereEnemy);
            }
            if (celulaCurenta.tipCelula == Cell.cellenum.SHOP) {
                int alegereShop = random.nextInt(Game.Init().storyMap.get(Cell.cellenum.SHOP).size());
                System.out.println(Game.Init().storyMap.get(Cell.cellenum.SHOP).get(alegereShop));
                Game.Init().storyMap.get(Cell.cellenum.SHOP).remove(alegereShop);
            }
            if (celulaCurenta.tipCelula == Cell.cellenum.FINISH) {
                int alegereFinish = random.nextInt(Game.Init().storyMap.get(Cell.cellenum.FINISH).size());
                System.out.println(Game.Init().storyMap.get(Cell.cellenum.FINISH).get(alegereFinish));
                Game.Init().storyMap.get(Cell.cellenum.FINISH).remove(alegereFinish);
            }
        }
    }
    public void afisareOptiuni(Cell celulaCurenta) {
        if (celulaCurenta.tipCelula == Cell.cellenum.ENEMY) {
            System.out.println("Alege actiunea: foloseste potiune (F), ataca cu abilitate (A), (P)");
        }
        if (celulaCurenta.tipCelula == Cell.cellenum.SHOP) {
            System.out.println("Alege actiunea: cumpara potiune (C), paraseste magazin (Exit), (P)");
        }
    }


    public void run() {
        Read.read();
        Grid tabla = Grid.init;
        try {
            System.out.println("Selecteaza modul de joc: Terminal / Grafic");
            Scanner scan = new Scanner(System.in);
            String text = scan.nextLine();
            if (text.equals("Terminal")) {
                System.out.println("Ai ales modul de joc in terminal");
                System.out.println("Introdu email-ul contului");
                String email = scan.nextLine();
                int gasitEmail = 0;
                for (Account contCurent : accountList) {
                    if (contCurent.playerInformation.playerLogin.getEmail().equals(email)) {
                        gasitEmail = 1;
                        System.out.println("Introdu parola contului");
                        String parola = scan.nextLine();
                        if (contCurent.playerInformation.playerLogin.getPassword().equals(parola)) {
                            System.out.println("Te-ai autentificat cu succes!");
                            System.out.println("Selecteaza caracterul:");
                            for (Character caracterCurent : contCurent.characters) {
                                System.out.println(caracterCurent.name);
                            }
                            int gasitCaracter = 0;
                            String alegereCaracter = scan.nextLine();
                            for (Character caracterAles : contCurent.characters) {
                                if (caracterAles.name.equals(alegereCaracter)) {
                                    gasitCaracter = 1;
                                    System.out.println("Ai ales caracterul " + caracterAles.name + "!");
                                    int tipRasa;
                                    if (caracterAles instanceof Warrior)
                                        tipRasa = 0;
                                    else if (caracterAles instanceof Rogue)
                                        tipRasa = 1;
                                    else
                                        tipRasa = 2;
                                    switch (tipRasa) {
                                        case 0 -> System.out.println("Acesta are nivelul -->  " + caracterAles.level + "   Experienta -->   " + caracterAles.experience + "    Rasa -->   Warrior");
                                        case 1 -> System.out.println("Acesta are nivelul -->  " + caracterAles.level + "   Experienta -->   " + caracterAles.experience + "    Rasa -->   Rogue");
                                        case 2 -> System.out.println("Acesta are nivelul -->  " + caracterAles.level + "   Experienta -->   " + caracterAles.experience + "    Rasa -->   Mage");
                                    }
                                    System.out.println("Jocul a inceput, mult succes!");
                                    Grid.Init().setareCelula(((ArrayList<Cell>)tabla.get(0)).get(0));
                                    Grid.init.caracterCurent = caracterAles;
                                    Grid.init.caracterCurent.OxValue = 0;
                                    Grid.init.caracterCurent.OyValue = 0;
                                    Character caracterJoc = Grid.Init().caracterCurent;
                                    caracterJoc.inventar.monede = caracterJoc.inventar.monede + 10;
                                    Grid.init.afisareHarta();
                                    afisarePoveste(Grid.init.celulaCurenta);
                                    boolean continuaJoc = true;
                                    while (continuaJoc) {
                                        System.out.println("Numarul de monede curent -> " + caracterJoc.inventar.monede);
                                        System.out.println("Alege directia: Est / Vest / Sud / Nord / P");
                                        String alegereDirectie = scan.nextLine();
                                        afisarePoveste(Grid.init.celulaCurenta);
                                        tabla.celulaCurenta.visitedCell = true;
                                        if (alegereDirectie.equals("Est"))
                                            Grid.Init().deplasareEst();
                                        else if (alegereDirectie.equals("Vest"))
                                            Grid.Init().deplasareVest();
                                        else if (alegereDirectie.equals("Sud"))
                                            Grid.Init().deplasareSud();
                                        else if (alegereDirectie.equals("Nord"))
                                            Grid.Init().deplasareNord();
                                        else if (alegereDirectie.equals("P")) {
                                            if (caracterJoc.OxValue <= 3)
                                                Grid.Init().deplasareEst();
                                            else
                                                Grid.Init().deplasareSud();
                                        }
                                        else throw new InvalidCommandException("Alegere invalida");
                                        Grid.Init().setareCelula(((ArrayList<Cell>)tabla.get(caracterJoc.OyValue)).get(caracterJoc.OxValue));
                                        if (tabla.celulaCurenta.visitedCell == false) {
                                            int sansaMonede = Grid.init.getRandomNumber(0, 4);
                                            if (sansaMonede == 0)
                                                caracterJoc.inventar.monede = caracterJoc.inventar.monede + 10;
                                            afisarePoveste(tabla.celulaCurenta);
                                            tabla.celulaCurenta.visitedCell = true;
                                            if (Grid.Init().celulaCurenta.tipCelula == Cell.cellenum.ENEMY) {
                                                Enemy inamicCurent = (Enemy) tabla.celulaCurenta.inamicMagazin;
                                                afisarePoveste(tabla.celulaCurenta);
                                                System.out.println("Ai intalnit un inamic cu urmatoarele atribute");
                                                System.out.println("Viata -> " + inamicCurent.viata_curenta + "    Mana -> " + inamicCurent.mana_curenta
                                                        + "    Protectii la: foc (" + inamicCurent.foc + "), gheata (" + inamicCurent.gheata + "), pamant (" + inamicCurent.pamant + ")");
                                                int indexHardcodare = 0;
                                                while (true) {
                                                    if (inamicCurent.viata_curenta <= 0) {
                                                        int sansaMonedeInamic = Grid.Init().getRandomNumber(0, 6);
                                                        if (sansaMonedeInamic < 5) {
                                                            caracterJoc.inventar.monede = caracterJoc.inventar.monede + 20;
                                                        }
                                                        caracterJoc.experience = caracterJoc.experience + 15;
                                                        if (caracterJoc.experience > 100) {
                                                            caracterJoc.level = caracterJoc.level + 1;
                                                            caracterJoc.experience = caracterJoc.experience - 100;
                                                        }
                                                        System.out.println("Ai invins inamicul");
                                                        break;
                                                    }
                                                    afisareOptiuni(Grid.Init().celulaCurenta);
                                                    String alegereEnemy = scan.nextLine();
                                                    if (alegereEnemy.equals("F")) {
                                                        int contor = 1;
                                                        while (true) {
                                                            for (Potion potiune : caracterJoc.inventar.potiuni) {
                                                                if (potiune instanceof HealthPotion)
                                                                    System.out.println(contor + ".  Potiune de viata cu valoarea de " + potiune.getValoare());
                                                                else if (potiune instanceof ManaPotion)
                                                                    System.out.println(contor + ".  Potiune de mana cu valoarea de " + potiune.getValoare());
                                                                contor++;
                                                            }
                                                            System.out.println("Viata curenta egala cu " + caracterJoc.viata_curenta + " si viata maxima este de " + caracterAles.viata_maxima);
                                                            System.out.println("Mana curenta egala cu " + caracterJoc.mana_curenta + " si mana maxima este de " + caracterAles.mana_maxima);
                                                            System.out.println("Alege potiunea dorita (introdu un numar)");
                                                            int alegeIndexPotiuneUtilizata = Integer.parseInt(scan.nextLine());
                                                            Potion potiuneAleasa = caracterJoc.inventar.potiuni.get(alegeIndexPotiuneUtilizata);
                                                            potiuneAleasa.utilizarePotiune(potiuneAleasa);
                                                            caracterJoc.inventar.potiuni.remove(potiuneAleasa);
                                                            System.out.println("Mai utilizezi alta potiune? (Da sau Nu)");
                                                            String alegere = scan.nextLine();
                                                            if (alegere.equals("Nu"))
                                                                break;
                                                            else if (alegere.equals("Da"))
                                                                continue;
                                                            else throw new InvalidCommandException("Alegere invalida");
                                                        }
                                                    }
                                                    else if (alegereEnemy.equals("A")) {
                                                        boolean manaSuficienta = false;
                                                        for (Spell abilitate : caracterJoc.abilitati) {
                                                            if (abilitate.manaAbilitate < caracterJoc.mana_curenta)
                                                                manaSuficienta = true;
                                                        }
                                                        if (manaSuficienta == false) {
                                                            System.out.println("Nu ai suficienta mana pentru nicio abilitate, vei ataca normal");
                                                            inamicCurent.primestePaguba(caracterJoc.atacaNormal());
                                                        }
                                                        else {
                                                            System.out.println("Alege o vraja: Foc (F) / Gheata (G) / Pamant (P) / Iesire(I)");
                                                            boolean vrajaSucces = false;
                                                            while (vrajaSucces == false) {
                                                                for (Spell vraja : caracterJoc.abilitati) {
                                                                    if (vraja instanceof Fire)
                                                                        System.out.println("Foc ---> daune " + vraja.paguba + " ---> mana " + vraja.manaAbilitate);
                                                                    if (vraja instanceof Ice)
                                                                        System.out.println("Gheata ---> daune " + vraja.paguba + " ---> mana " + vraja.manaAbilitate);
                                                                    if (vraja instanceof Earth)
                                                                        System.out.println("Pamant ---> daune " + vraja.paguba + " ---> mana " + vraja.manaAbilitate);

                                                                }
                                                                String alegereVraja = scan.nextLine();
                                                                if (alegereVraja.equals("F"))
                                                                    vrajaSucces = caracterJoc.folosireAbilitate(caracterAles.abilitati.get(0), (Enemy) Grid.Init().celulaCurenta.inamicMagazin);
                                                                else if (alegereVraja.equals("G"))
                                                                    vrajaSucces = caracterJoc.folosireAbilitate(caracterAles.abilitati.get(1), (Enemy) Grid.Init().celulaCurenta.inamicMagazin);
                                                                else if (alegereVraja.equals("P"))
                                                                    vrajaSucces = caracterJoc.folosireAbilitate(caracterAles.abilitati.get(2), (Enemy) Grid.Init().celulaCurenta.inamicMagazin);
                                                                else throw new InvalidCommandException("Alegere invalida");
                                                                if (!vrajaSucces)
                                                                    System.out.println("Nu ai suficienta mana pentru aceasta abilitate, incearca din nou");
                                                            }
                                                            int viataInitiala;
                                                            if (inamicCurent.viata_curenta <= 0) {
                                                                int sansaMonedeInamic = Grid.Init().getRandomNumber(0, 6);
                                                                if (sansaMonedeInamic < 5) {
                                                                    caracterJoc.inventar.monede = caracterJoc.inventar.monede + 20;
                                                                }
                                                                caracterJoc.experience = caracterJoc.experience + 15;
                                                                if (caracterJoc.experience > 100) {
                                                                    caracterJoc.level = caracterJoc.level + 1;
                                                                    caracterJoc.experience = caracterJoc.experience - 100;
                                                                }
                                                                System.out.println("Ai invins inamicul");
                                                                break;
                                                            }
                                                            else {
                                                                viataInitiala = caracterJoc.viata_curenta;
                                                                int sansaAbilitateInamic = Grid.Init().getRandomNumber(0, 4);
                                                                if (sansaAbilitateInamic <= 1) {
                                                                    int sansaIndexAbilitate = Grid.Init().getRandomNumber(0, inamicCurent.abilitati.size());
                                                                    if (inamicCurent.mana_curenta < inamicCurent.abilitati.get(sansaIndexAbilitate).manaAbilitate) {
                                                                        inamicCurent.folosireAbilitate(inamicCurent.abilitati.get(sansaIndexAbilitate), caracterJoc);
                                                                        inamicCurent.abilitati.remove(sansaIndexAbilitate);
                                                                    }
                                                                    else
                                                                        caracterJoc.primestePaguba(inamicCurent.atacaNormal());
                                                                }
                                                                else
                                                                    caracterJoc.primestePaguba(inamicCurent.atacaNormal());
                                                            }
                                                            if (caracterJoc.viata_curenta == 0) {
                                                                System.out.println("Ai murit, jocul s-a terminat");
                                                                break;
                                                            }
                                                            else {
                                                                System.out.println("Ai primit " + (viataInitiala - caracterJoc.viata_curenta) + " pagube");
                                                            }
                                                        }
                                                    }
                                                    else if (alegereEnemy.equals("P")) {
                                                        if (indexHardcodare >= 0 && indexHardcodare <= 2) {
                                                            caracterJoc.folosireAbilitate(caracterJoc.abilitati.get(indexHardcodare), inamicCurent);
                                                            Spell abilitateCurenta = caracterJoc.abilitati.get(indexHardcodare);
                                                            boolean manasufiecientaatac = true;
                                                            if (abilitateCurenta.manaAbilitate < caracterJoc.mana_curenta)
                                                                manasufiecientaatac = true;
                                                            if (manasufiecientaatac == true)
                                                                System.out.println("S-a folosit abilitatea " + abilitateCurenta.getClass().getSimpleName() + " cu paguba de " +  abilitateCurenta.paguba);
                                                            else
                                                                System.out.println("Nu ai suficienta mana pentru aceasta abilitate, incearca din nou");
                                                        }
                                                        else if (indexHardcodare == 3 || indexHardcodare == 4) {
                                                            if (caracterJoc.inventar.potiuni.get(0) instanceof HealthPotion)
                                                                System.out.println("Ai folosit o potiune de viata cu valoarea " + caracterJoc.inventar.potiuni.get(0).getValoare());
                                                            else
                                                                System.out.println("Ai folosit o potiune de mana cu valoarea " + caracterJoc.inventar.potiuni.get(0).getValoare());
                                                            caracterJoc.utilizarePotiune(caracterJoc.inventar.potiuni.get(0));
                                                        }
                                                        else {
                                                            int viataInamic = inamicCurent.viata_curenta;
                                                            inamicCurent.primestePaguba(caracterJoc.atacaNormal());
                                                            System.out.println("Ai atacat normal provocand " + (viataInamic - inamicCurent.viata_curenta) + " pagube");
                                                        }
                                                        indexHardcodare++;
                                                        int viataInitialaP = 0;
                                                        if (inamicCurent.viata_curenta <= 0 ) {
                                                            if (inamicCurent.viata_curenta <= 0) {
                                                                int sansaMonedeInamic = Grid.Init().getRandomNumber(0, 6);
                                                                if (sansaMonedeInamic < 5) {
                                                                    caracterJoc.inventar.monede = caracterJoc.inventar.monede + 20;
                                                                }
                                                                caracterJoc.experience = caracterJoc.experience + 15;
                                                                if (caracterJoc.experience > 100) {
                                                                    caracterJoc.level = caracterJoc.level + 1;
                                                                    caracterJoc.experience = caracterJoc.experience - 100;
                                                                }
                                                                System.out.println("Ai invins inamicul");
                                                                break;
                                                            }
                                                        }
                                                        else {
                                                            viataInitialaP = caracterJoc.viata_curenta;
                                                            int sansaAbilitateInamic = Grid.Init().getRandomNumber(0, 4);
                                                            if (sansaAbilitateInamic <=  1) {
                                                                int sansaIndexAbilitate = Grid.Init().getRandomNumber(0, inamicCurent.abilitati.size());
                                                                if (inamicCurent.mana_curenta < inamicCurent.abilitati.get(sansaIndexAbilitate).manaAbilitate) {
                                                                    inamicCurent.folosireAbilitate(inamicCurent.abilitati.get(sansaIndexAbilitate), caracterJoc);
                                                                    inamicCurent.abilitati.remove(sansaIndexAbilitate);
                                                                }
                                                                else
                                                                    caracterJoc.primestePaguba(inamicCurent.atacaNormal());
                                                            }
                                                            else
                                                                caracterJoc.primestePaguba(inamicCurent.atacaNormal());
                                                        }
                                                        if (caracterJoc.viata_curenta == 0) {
                                                            System.out.println("Ai murit, jocul s-a terminat");
                                                            return;
                                                        }
                                                        else {
                                                            System.out.println("Ai primit " + (viataInitialaP - caracterJoc.viata_curenta) + " pagube");
                                                        }
                                                    }
                                                    else throw new InvalidCommandException("Alegere invalida");
                                                    System.out.println("Situatie curanta caracter: viata -> " + caracterJoc.viata_curenta + "    mana -> " + caracterJoc.mana_curenta);
                                                    System.out.println("Situatie curanta inamic: viata -> " + inamicCurent.viata_curenta + "    mana -> " + inamicCurent.mana_curenta);
                                                }
                                            }
                                            else if (Grid.Init().celulaCurenta.tipCelula == Cell.cellenum.SHOP) {
                                                while (true) {
                                                    int contor = 1;
                                                    for (Potion potiune : ((Shop) tabla.celulaCurenta.inamicMagazin).listaPotiuniMagazin) {
                                                        if (potiune instanceof HealthPotion)
                                                            System.out.println(contor + ".  Potiune de viata cu valoarea " + potiune.getValoare() + "  ---> pretul " + potiune.getPret() + "  ---> greutatea " + potiune.getGreutate());
                                                        else if (potiune instanceof ManaPotion)
                                                            System.out.println(contor + ".  Potiune de mana  cu valoarea " + potiune.getValoare() + "  ---> pretul " + potiune.getPret() + "  ---> greutatea " + potiune.getGreutate());
                                                        contor++;
                                                    }
                                                    if (((Shop) tabla.celulaCurenta.inamicMagazin).listaPotiuniMagazin.size() == 0) {
                                                        System.out.println("Magazinul este gol. Te mai asteptam");
                                                        break;
                                                    }
                                                    afisareOptiuni(tabla.celulaCurenta);
                                                    String alegereMagazin = scan.nextLine();
                                                    if (alegereMagazin.equals("C")) {
                                                        System.out.println("Alege o potiune dupa index");
                                                        int alegereNumarPotiune = Integer.parseInt(scan.nextLine());
                                                        if (alegereNumarPotiune <= 0 || alegereNumarPotiune > ((Shop) tabla.celulaCurenta.inamicMagazin).listaPotiuniMagazin.size())
                                                            throw new InvalidCommandException("Alegere invalida");
                                                        else {
                                                            Potion potiuneAleasa = ((Shop) tabla.celulaCurenta.inamicMagazin).listaPotiuniMagazin.get(alegereNumarPotiune - 1);
                                                            if (potiuneAleasa.getGreutate() > caracterJoc.inventar.calculGreutateRamasa())
                                                                System.out.println("Nu este loc in inventar");
                                                            else if (potiuneAleasa.getPret() > caracterJoc.inventar.monede)
                                                                System.out.println("Nu sunt suficienti bani");
                                                            else
                                                                caracterJoc.cumparaPotiune(((Shop) tabla.celulaCurenta.inamicMagazin).stergePotiuneMagazin(alegereNumarPotiune - 1));
                                                        }
                                                    } else if (alegereMagazin.equals("Exit")) {
                                                        System.out.println("Te mai asteptam");
                                                        break;
                                                    } else if (alegereMagazin.equals("P")) {
                                                        caracterJoc.cumparaPotiune(((Shop) tabla.celulaCurenta.inamicMagazin).stergePotiuneMagazin(0));
                                                        caracterJoc.cumparaPotiune(((Shop) tabla.celulaCurenta.inamicMagazin).stergePotiuneMagazin(0));
                                                        break;
                                                    } else throw new InvalidCommandException("Alegere invalida");
                                                }

                                            } else if (Grid.Init().celulaCurenta.tipCelula == Cell.cellenum.FINISH) {
                                                System.out.println("Felicitari, ai castigat!");
                                                break;
                                            }
                                        }
                                        tabla.afisareHarta();
                                    }
                                }
                            }
                            if (gasitCaracter == 0)
                                throw new InvalidCommandException("Caracterul ales nu exista");
                        }
                        else throw new InvalidCommandException("Parola incorecta");
                    }
                }
                if (gasitEmail == 0)
                    throw new InvalidCommandException("Emailul nu exista");
            }
        }
        catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Alege tipul de harta: Static (S) / Dinamic (D)");
        Scanner sc = new Scanner(System.in);
        String alegereTipHarta = sc.nextLine();
        if (alegereTipHarta.equals("S"))
            Grid.Init().generareHartaStatica(5, 5);
        else
            Grid.Init().generareHartaDinamica(5, 5);
        Game.Init().run();
    }
}