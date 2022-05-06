import java.util.ArrayList;

public abstract class Character extends Entity{

    public int experience;
    public int level;
    Inventory inventar;
    int OxValue;
    int OyValue;
    int charisma;
    int dex;
    int strength;
    public String name;

    public Character(String name, int experience, int level){
        super.viata_curenta = 100;
        super.viata_maxima = 100;
        super.mana_curenta = 100;
        super.mana_maxima = 100;
        this.name = name;
        this.experience = experience;
        this.level = level;
        super.abilitati = new ArrayList<>(3);
        super.abilitati.add(new Fire());
        super.abilitati.add(new Ice());
        super.abilitati.add(new Earth());
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void cumparaPotiune(Potion potiune) {
            inventar.potiuni.add(potiune);
            inventar.monede  = inventar.monede - potiune.getPret();
    }
    public void utilizarePotiune(Potion potiuneCurenta) {
        if (potiuneCurenta instanceof HealthPotion)
            Grid.Init().caracterCurent.viata_curenta = Grid.Init().caracterCurent.viata_curenta + potiuneCurenta.getValoare();
        else
            Grid.Init().caracterCurent.mana_curenta = Grid.Init().caracterCurent.mana_curenta + potiuneCurenta.getValoare();
        Grid.Init().caracterCurent.inventar.potiuni.remove(potiuneCurenta);
    }

    public abstract void primestePaguba(int paguba);
    public abstract int atacaNormal();
}
