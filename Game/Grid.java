import java.util.*;

public class Grid extends ArrayList {
    int lungime;
    int inaltime;
    Character caracterCurent;
    Cell celulaCurenta;

    static Grid init = new Grid();
    public static Grid Init(){

        return init;
    }
    private Grid(){}

    public void setareCelula(Cell celulaUrmatoare) {
        celulaCurenta = celulaUrmatoare;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    static List<Cell> grid = new ArrayList<>();
    public void deplasareEst() {
        if (caracterCurent.OxValue + 1 < lungime) {
            caracterCurent.OxValue++;
            celulaCurenta = ((ArrayList<Cell>)init.get(caracterCurent.OyValue)).get(caracterCurent.OxValue);
        }
        else {
            System.out.println("Nu se poate merge la est");
        }
    }
    public void deplasareVest() {
        if (caracterCurent.OxValue - 1 >= 0) {
            caracterCurent.OxValue--;
            celulaCurenta = ((ArrayList<Cell>)init.get(caracterCurent.OyValue)).get(caracterCurent.OxValue);
        }
        else {
            System.out.println("Nu se poate merge la vest");
        }
    }
    public void deplasareSud() {
        if (caracterCurent.OyValue + 1 < inaltime) {
            caracterCurent.OyValue++;
            celulaCurenta = ((ArrayList<Cell>)init.get(caracterCurent.OyValue)).get(caracterCurent.OxValue);
        }
        else {
            System.out.println("Nu se poate merge la sud");
        }
    }
    public void deplasareNord() {
        if (caracterCurent.OyValue - 1 >= 0) {
            caracterCurent.OyValue--;
            celulaCurenta = ((ArrayList<Cell>)init.get(caracterCurent.OyValue)).get(caracterCurent.OxValue);
        }
        else {
            System.out.println("Nu se poate merge la nord");
        }
    }

    public void setInit(Character currentPlayer){
        caracterCurent = currentPlayer;
        caracterCurent.OxValue = 0;
        caracterCurent.OyValue = 0;
        celulaCurenta.visitedCell = true;
        celulaCurenta = ((ArrayList<Cell>)init.get(0)).get(0);
    }
    public void afisareHarta() {
        System.out.println();
        for (int i = 0; i < inaltime; i++) {
            for (int j = 0; j < lungime; j++) {
                Cell celulaLoop = ((ArrayList<Cell>) init.get(i)).get(j);
                if (celulaLoop == celulaCurenta)
                    System.out.print("P    ");
                else if (celulaLoop.visitedCell) {
                    if (celulaLoop == ((ArrayList<Cell>) init.get(inaltime - 1)).get(lungime - 1))
                        System.out.print("F    ");
                    else if (celulaLoop.tipCelula == Cell.cellenum.ENEMY)
                        System.out.print("E    ");
                    else if (celulaLoop.tipCelula == Cell.cellenum.SHOP)
                        System.out.print("S    ");
                    else System.out.print("-    ");
                }
                else
                    System.out.print("?    ");
            }
            System.out.println();
        }
    }

    public void generareHartaStatica(int inal, int lung) {
        this.inaltime = inal;
        this.lungime = lung;
        for (int i = 0; i < inaltime; i++)
            init.add(new ArrayList<Cell>(lungime));
        Random random = new Random();
        int numarRandom = random.nextInt(3);
        ((ArrayList<Cell>) init.get(0)).add(0, new Cell(0, 0, new Enemy(0)));
        ((ArrayList<Cell>) init.get(0)).add(1, new Cell(0, 1, new Enemy(0)));
        ((ArrayList<Cell>) init.get(0)).add(2, new Cell(0, 2, new Enemy(0)));
        numarRandom = random.nextInt(3);
        switch (numarRandom) {
            case 0 -> ((ArrayList<Cell>) init.get(0)).add(3, new Cell(0, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10))));
            case 1 -> ((ArrayList<Cell>) init.get(0)).add(3, new Cell(0, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new HealthPotion(20, 60, 6))));
            case 2 -> ((ArrayList<Cell>) init.get(0)).add(3, new Cell(0, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new ManaPotion(20, 60, 6), new HealthPotion(30, 60, 6))));
        }
        ((ArrayList<Cell>) init.get(0)).add(4, new Cell(0, 4, new Enemy(0)));
        ((ArrayList<Cell>) init.get(1)).add(0, new Cell(1, 0, new Enemy(0)));
        ((ArrayList<Cell>) init.get(1)).add(1, new Cell(1, 1, new Enemy(0)));
        ((ArrayList<Cell>) init.get(1)).add(2, new Cell(1, 2, new Enemy(0)));
        numarRandom = random.nextInt(3);
        switch (numarRandom) {
            case 0 -> ((ArrayList<Cell>) init.get(1)).add(3, new Cell(1, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10))));
            case 1 -> ((ArrayList<Cell>) init.get(1)).add(3, new Cell(1, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new HealthPotion(20, 60, 6))));
            case 2 -> ((ArrayList<Cell>) init.get(1)).add(3, new Cell(1, 3, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new ManaPotion(20, 60, 6), new HealthPotion(30, 60, 6))));
        }
        ((ArrayList<Cell>) init.get(1)).add(4, new Cell(1, 4, new Enemy(0)));
        numarRandom = random.nextInt(3);
        switch (numarRandom) {
            case 0 -> ((ArrayList<Cell>) init.get(2)).add(0, new Cell(2, 0, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10))));
            case 1 -> ((ArrayList<Cell>) init.get(2)).add(0, new Cell(2, 0, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new HealthPotion(20, 60, 6))));
            case 2 -> ((ArrayList<Cell>) init.get(2)).add(0, new Cell(2, 0, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new ManaPotion(20, 60, 6), new HealthPotion(30, 60, 6))));
        }
        ((ArrayList<Cell>) init.get(2)).add(1, new Cell(2, 1, new Enemy(0)));
        ((ArrayList<Cell>) init.get(2)).add(2, new Cell(2, 2, new Enemy(0)));
        ((ArrayList<Cell>) init.get(2)).add(3, new Cell(2, 3, new Enemy(0)));
        ((ArrayList<Cell>) init.get(2)).add(4, new Cell(2, 4, new Enemy(0)));
        ((ArrayList<Cell>) init.get(3)).add(0, new Cell(3, 0, new Enemy(0)));
        ((ArrayList<Cell>) init.get(3)).add(1, new Cell(3, 1, new Enemy(0)));
        ((ArrayList<Cell>) init.get(3)).add(2, new Cell(3, 2, new Enemy(0)));
        ((ArrayList<Cell>) init.get(3)).add(3, new Cell(3, 3, new Enemy(0)));
        ((ArrayList<Cell>) init.get(3)).add(4, new Cell(3, 4, new Enemy()));
        ((ArrayList<Cell>) init.get(4)).add(0, new Cell(4, 0, new Enemy(0)));
        ((ArrayList<Cell>) init.get(4)).add(1, new Cell(4, 1, new Enemy(0)));
        ((ArrayList<Cell>) init.get(4)).add(2, new Cell(4, 2, new Enemy(0)));
        ((ArrayList<Cell>) init.get(4)).add(3, new Cell(4, 3, new Enemy(0)));
        ((ArrayList<Cell>) init.get(4)).add(4, new Cell(4, 4, new Enemy(0)));
    }
    public void generareHartaDinamica(int inal, int lung) {
        this.inaltime = inal;
        this.lungime = lung;
        Random random = new Random();
        for (int k = 0 ; k < lungime; k++)
            init.add(new ArrayList<>(lungime));
        for (int i = 0 ; i < lungime; i++)
            for(int j = 0 ; j < inaltime; j++) {
                if ( (i == 0 && j == 0) || (i == lungime - 1 && j == inaltime - 1))
                    ((ArrayList<Cell>)init.get(i)).add(j,new Cell(i,j, new Enemy(0)));
                else {

                    int tipCelula = random.nextInt(13);
                    if (tipCelula <= 6)
                        ((ArrayList<Cell>)init.get(i)).add(j, new Cell(i, j, new Enemy(0)));
                    else if (tipCelula < 10) {
                        int randomShop = random.nextInt(3);
                        switch (randomShop) {
                            case 0 -> ((ArrayList<Cell>)init.get(i)).add(j, new Cell(i, j, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10))));
                            case 1 -> ((ArrayList<Cell>)init.get(i)).add(j, new Cell(i, j, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new HealthPotion(20, 60, 6))));
                            case 2 -> ((ArrayList<Cell>)init.get(i)).add(j, new Cell(i, j, new Shop(new ManaPotion(40, 80, 10), new HealthPotion(30, 60, 10), new ManaPotion(20, 60, 6), new HealthPotion(30, 60, 6))));
                        }
                    }
                    else {
                        ((ArrayList<Cell>)init.get(i)).add(j, new Cell(i, j, new Enemy()));
                    }
                }
            }
    }


}

